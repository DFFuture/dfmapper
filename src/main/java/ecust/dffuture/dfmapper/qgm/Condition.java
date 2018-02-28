package ecust.dffuture.dfmapper.qgm;

import net.sf.jsqlparser.expression.Expression;



public class Condition {
    private ConditionLocation location;
    private ConditionType type;


    private Expression expression ;

    public Condition(Expression expression) {
        this.expression = expression;
    }
}
