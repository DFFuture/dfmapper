package ecust.dffuture.dfmapper.visitor;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Column;

import java.util.ArrayList;
import java.util.List;

public class ColumnFinder extends ExprVisitor {

    private List<Column> columns;

    public void init() {
        columns = new ArrayList <>();
    }

    public List<Column> getColumns(Expression expression) {
        expression.accept(this);
        return columns;
    }

    public void visit(Column column) {
        columns.add(column);
    }
}
