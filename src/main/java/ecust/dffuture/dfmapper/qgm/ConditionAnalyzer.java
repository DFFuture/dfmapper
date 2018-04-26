package ecust.dffuture.dfmapper.qgm;

import ecust.dffuture.dfmapper.qgm.box.SelectBox;
import ecust.dffuture.dfmapper.qgm.type.ConditionLocation;
import ecust.dffuture.dfmapper.visitor.ColumnFinder;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.SubSelect;

import java.util.ArrayList;
import java.util.List;

public class ConditionAnalyzer {

    private SelectBox currentBox;

    private ColumnFinder columnFinder = new ColumnFinder();

    private ColumnAnalyzer columnAnalyzer = new ColumnAnalyzer();

    public void setCurrentBox(SelectBox box) {
        currentBox = box;
        columnAnalyzer.setCurrentBox(box);
    }

    public void classifyCondition(Expression expression) {
        if(expression instanceof AndExpression) {
            classifyCondition(((AndExpression) expression).getLeftExpression());
            classifyCondition(((AndExpression) expression).getRightExpression());
        } else {
            analyzeCondition(expression);
        }
    }

    /**
     * 分析谓语与Quantifier之间的关系
     * @param predicate 谓语
     */
    public void analyzeCondition(Expression predicate) {

        if(predicate instanceof InExpression) {

        }else if(predicate instanceof ExistsExpression) {

        }else if(predicate instanceof OrExpression){

        }else {
            Predicate condition  = new Predicate(predicate, ConditionLocation.WHERE);
            columnFinder.init();
            // 获取包含的列
            List<Column> columns = columnFinder.getColumns(predicate);
            List<Quantifier> quantifiers = new ArrayList <>();
            for(Column column:columns) {
                quantifiers.add(columnAnalyzer.analyze(column));
            }
            if(columns.size() < 2) {
                quantifiers.get(0).addCondition(condition);
            }else if(columns.size() == 2){
                quantifiers.get(0).addRelation(quantifiers.get(1), condition);
                quantifiers.get(1).addRelation(quantifiers.get(0), condition);
            }else {
                //TODO 谓语包含三个或三个以上列的情况
            }
        }
    }

    public Quantifier search(Expression expression) {
        if(expression instanceof InExpression) {
            ItemsList itemsList = ((InExpression) expression).getRightItemsList();
            if(itemsList instanceof SubSelect) {
                for(Quantifier quantifier: currentBox.getQuantifiers()) {
                    if(quantifier.getFromItem() == itemsList) {
                        return quantifier;
                    }
                }
            }
        }
        return null;
    }

    public void analyzeIn(InExpression inExpression) {
        Quantifier inQuantifier = search(inExpression);
        if(inExpression.getLeftExpression() instanceof Column) {
            Column left = (Column) inExpression.getLeftExpression();
            Quantifier quantifier = columnAnalyzer.analyze(left);
            if(inQuantifier != null) {
                EqualsTo expression = new EqualsTo();
                expression.setLeftExpression(left);
                expression.setRightExpression(new Column(inQuantifier.getTName(),
                        inQuantifier.getBox().getOutput().get(0).getName()));
                Predicate predicate = new Predicate(expression, ConditionLocation.WHERE);
                quantifier.addRelation(inQuantifier, predicate);
                inQuantifier.addRelation(quantifier, predicate);
            } else {
                quantifier.addCondition(new Predicate(inExpression, ConditionLocation.WHERE));
            }

        }
    }

    public void analyze(ExistsExpression expression) {

    }
}