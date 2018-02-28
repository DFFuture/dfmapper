package ecust.dffuture.dfmapper.visitor;

import net.sf.jsqlparser.expression.AnalyticExpression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.statement.select.OrderByElement;

/**
 * 访问者模式遍历表达式
 */
public class ExprVisitor extends ExpressionVisitorAdapter {

    public void visit(AnalyticExpression expr) {
        if(expr.getExpression() != null)
            expr.getExpression().accept(this);
        if(expr.getDefaultValue() != null)
            expr.getDefaultValue().accept(this);
        if(expr.getOffset() != null)
            expr.getOffset().accept(this);
        if (expr.getKeep() != null) {
            expr.getKeep().accept(this);
        }
        if(expr.getOrderByElements() != null)
            for (OrderByElement element : expr.getOrderByElements()) {
                element.getExpression().accept(this);
            }
        if(expr.getWindowElement() != null) {
            if(expr.getWindowElement().getRange() != null) {
                if(expr.getWindowElement().getRange().getStart().getExpression() != null)
                    expr.getWindowElement().getRange().getStart().getExpression().accept(this);
                if(expr.getWindowElement().getRange().getEnd().getExpression() != null)
                    expr.getWindowElement().getRange().getEnd().getExpression().accept(this);
            }
            if(expr.getWindowElement().getOffset() !=null)
                expr.getWindowElement().getOffset().getExpression().accept(this);
        }

    }
}
