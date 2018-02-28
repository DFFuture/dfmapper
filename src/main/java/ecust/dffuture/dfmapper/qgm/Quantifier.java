package ecust.dffuture.dfmapper.qgm;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.FromItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Quantifier {

    private Box container;

    private String name;
    private Table tName;
    private Box box;
    private QuantifierType quantifierType;

    private HashMap<Quantifier, Condition> relations = new HashMap <>();
    private List<Condition> conditions = new ArrayList <>();

    private String originalName;

    private FromItem fromItem;

    public Quantifier(String name,FromItem fromItem, Box box, QuantifierType quantifierType) {
        this.name = name;
        this.tName = new Table(name);
        this.fromItem = fromItem;
        this.box = box;
        box.setReference(this);
        this.quantifierType = quantifierType;
        if(fromItem.getAlias() != null) {
            originalName = fromItem.getAlias().getName();
        }else if(fromItem instanceof Table){
            originalName = ((Table) fromItem).getName();
        }else {
            originalName = null;
        }
        if(quantifierType == QuantifierType.FROM) {
            fromItem.setAlias(new Alias(name));
        }
    }

    public String getOriginalName() {
        return originalName;
    }

    public Box getBox() {
        return box;
    }

    public String getName() {
        return name;
    }

    public void setContainer(Box box) {
        container = box;
    }

    public Box getContainer() {
        return container;
    }

    public void addRelation(Quantifier quantifier, Condition condition) {

        relations.put(quantifier, condition);

    }

    public void addCondition(Condition condition) {
        conditions.add(condition);
    }

    public Table getTName() {
        return tName;
    }
}
