package ecust.dffuture.dfmapper.qgm;

import ecust.dffuture.dfmapper.qgm.type.ConditionLocation;
import ecust.dffuture.dfmapper.qgm.type.ConditionType;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;

import java.util.Stack;


public class Predicate {

    /**
     * 谓语所在位置：where、on或Having
     */
    private ConditionLocation location;

    /**
     * 条件类型：等值连接或非等值连接
     */
    private ConditionType type;

    /**
     * 查询树中对应的谓语表达式
     */
    private Expression predicate ;

    /**
     *
     * @param predicate 谓语表达式
     */
    public Predicate(Expression predicate, ConditionLocation location) {
        this.location = location;
        this.predicate = predicate;
    }

    public void setType(ConditionType type) {
        this.type = type;
    }

//    public Expression[] locate(Expression where) {
//        Stack<Expression> eStack = new Stack <>();
//        Expression expr = where;
//        eStack.push(expr);
//        while(!eStack.isEmpty()) {
//            if (expr instanceof AndExpression) {
//                expr = ((AndExpression) expr).getLeftExpression();
//                eStack.push(expr);
//            } else if(expr != predicate) {
//                expr = ((AndExpression) eStack.peek()).getRightExpression();
//            }else {
//                break;
//            }
//        }
//        return new Expression[]{};
//    }
}
