package ecust.dffuture.dfmapper.visitor;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Column;

import java.util.ArrayList;
import java.util.List;

/**
 * 查找表达式中的列
 */
public class ColumnFinder extends ExprVisitor {

    private List<Column> columns;

    /**
     * 初始化清空columns
     */
    public void init() {
        columns = new ArrayList <>();
    }

    /**
     * 从表达式中获取包含的列
     * @param expression 表达式
     * @return
     */
    public List<Column> getColumns(Expression expression) {
        expression.accept(this);
        return columns;
    }

    public void visit(Column column) {
        // TODO 去重
        columns.add(column);
    }
}
