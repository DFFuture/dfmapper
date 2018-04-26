package ecust.dffuture.dfmapper.qgm;

import ecust.dffuture.dfmapper.qgm.box.Box;
import ecust.dffuture.dfmapper.qgm.box.SelectBox;
import ecust.dffuture.dfmapper.qgm.box.TableBox;
import ecust.dffuture.dfmapper.qgm.type.ConditionLocation;
import ecust.dffuture.dfmapper.qgm.type.QuantifierType;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SubSelect;

/**
 * QGM工厂类，生成各种结构
 */
public class QGMFactory {

    private static int qCounter = 0;
    private final static String PREFIX = "Q";

    private static String getQName() {
        return PREFIX + qCounter++;
    }

    /**
     * 根据语句初始生成QGM
     * @param statement 语句
     * @return
     */
    public static QGM createQGM(Statement statement) {
        return new QGM(statement);
    }

    public static SelectBox createBox(SelectBody selectBody) {
        return new SelectBox(selectBody);
    }

    public static Box createBox(FromItem fromItem) {
        if(fromItem instanceof Table) {
            return new TableBox((Table)fromItem);
        }else if(fromItem instanceof SubSelect){
            return new SelectBox(((SubSelect) fromItem).getSelectBody());
        }
        return null;
    }


    public static Quantifier createQuantifier(FromItem fromItem, QuantifierType quantifierType) {
        Box box = createBox(fromItem);
        String qName = getQName();

        return new Quantifier(qName, fromItem, box, quantifierType);
    }

    public static Predicate createPredicate(Expression expression, ConditionLocation locate) {
        return new Predicate(expression, locate);
    }

}
