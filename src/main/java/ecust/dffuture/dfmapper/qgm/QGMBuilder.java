package ecust.dffuture.dfmapper.qgm;

import ecust.dffuture.dfmapper.qgm.box.SelectBox;
import ecust.dffuture.dfmapper.qgm.type.QuantifierType;
import ecust.dffuture.dfmapper.visitor.ColumnFinder;
import ecust.dffuture.dfmapper.visitor.QueryVisitor;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.statement.select.*;

import java.util.Stack;

public class QGMBuilder extends QueryVisitor{
    private QGM qgm;
    private Stack<SelectBox> selectBoxStack = new Stack <>();
    // private ColumnFinder columnFinder = new ColumnFinder();

    private ConditionAnalyzer conditionAnalyzer = new ConditionAnalyzer();

    private void analyze(FromItem fromItem, QuantifierType type) {
        Quantifier quantifier = QGMFactory.createQuantifier(fromItem, type);
        selectBoxStack.peek().add(quantifier);
        qgm.put(quantifier.getName(), quantifier);
        if(quantifier.getBox() instanceof SelectBox) {
            selectBoxStack.push((SelectBox) quantifier.getBox());
            fromItem.accept(this);
            selectBoxStack.pop();
        }
    }

    @Override
    public void visit(Select select) {
        qgm = QGMFactory.createQGM(select);
        if (select.getWithItemsList() != null) {
            for (WithItem withItem : select.getWithItemsList()) {
                withItem.accept(this);
            }
        }
        SelectBox selectBox = QGMFactory.createBox(select.getSelectBody());
        qgm.add(selectBox);
        selectBoxStack.push(selectBox);
        select.getSelectBody().accept(this);
        selectBoxStack.pop();
    }

    @Override
    public void visit(PlainSelect plainSelect) {
        if(plainSelect.getFromItem() != null) {
            FromItem fromItem = plainSelect.getFromItem();
            analyze(fromItem, QuantifierType.FROM);
        }

        if(plainSelect.getSelectItems() != null) {
            for(SelectItem selectItem: plainSelect.getSelectItems()) {
                selectBoxStack.peek().add(selectItem);
            }
        }

        if(plainSelect.getWhere() != null) {
            plainSelect.getWhere().accept(this);
            conditionAnalyzer.setCurrentBox(selectBoxStack.peek());
            conditionAnalyzer.classifyCondition(plainSelect.getWhere());
        }
    }

    public void visit(SubSelect subSelect) {

        if (subSelect.getWithItemsList() != null) {
            for (WithItem item : subSelect.getWithItemsList()) {
                item.accept(this);
            }
        }
        subSelect.getSelectBody().accept(this);

        if (subSelect.getPivot() != null) {
            subSelect.getPivot().accept(this);
        }
    }

    public void visit(InExpression expr) {

        if(expr.getRightItemsList() instanceof SubSelect) {
            FromItem fromItem = (SubSelect)expr.getRightItemsList();
            analyze(fromItem, QuantifierType.IN);

        }else {
            expr.getRightItemsList().accept(this);
        }


        if (expr.getLeftExpression() != null) {

            expr.getLeftExpression().accept(this);
        } else if (expr.getLeftItemsList() != null) {
            expr.getLeftItemsList().accept(this);
        }


    }

}
