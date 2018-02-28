package ecust.dffuture.dfmapper.visitor;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;

/**
 * 访问者模式遍历整个查询语句
 */
public class QueryVisitor extends ExprVisitor implements SelectVisitor, FromItemVisitor, OrderByVisitor {

    /**
     * 访问SELECT语句
     * @param select Select SELECT语句结构
     */
    public void visit(Select select) {
        if (select.getWithItemsList() != null) {
            for (WithItem withItem : select.getWithItemsList()) {
                withItem.accept(this);
            }
        }
        select.getSelectBody().accept(this);
    }

    public void visit(PlainSelect plainSelect) {
        if (plainSelect.getSelectItems() != null) {
            for (SelectItem item : plainSelect.getSelectItems()) {
                item.accept(this);
            }
        }

        if (plainSelect.getFromItem() != null) {
            plainSelect.getFromItem().accept(this);
        }

        if (plainSelect.getJoins() != null) {
            for (Join join : plainSelect.getJoins()) {
                join.getRightItem().accept(this);
            }
        }
        if (plainSelect.getWhere() != null) {
            plainSelect.getWhere().accept(this);
        }
        if (plainSelect.getOracleHierarchical() != null) {
            plainSelect.getOracleHierarchical().accept(this);
        }
    }

    public void visit(SetOperationList setOpList) {

    }

    public void visit(WithItem withItem) {
        withItem.getSelectBody().accept(this);
    }

    public void visit(Table table) {

    }

    public void visit(SubJoin subjoin) {

    }

    public void visit(LateralSubSelect lateralSubSelect) {

    }

    public void visit(ValuesList valuesList) {

    }

    public void visit(TableFunction valuesList) {

    }

    public void visit(OrderByElement orderBy) {

    }


}
