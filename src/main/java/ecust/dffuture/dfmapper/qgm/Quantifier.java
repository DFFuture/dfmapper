package ecust.dffuture.dfmapper.qgm;

import ecust.dffuture.dfmapper.qgm.box.Box;
import ecust.dffuture.dfmapper.qgm.type.IteratorType;
import ecust.dffuture.dfmapper.qgm.type.QuantifierType;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.FromItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Quantifier {

    /**
     * Quantifier所在的Box
     */
    private Box container;

    /**
     * Quantifier的名称
     */
    private String name;
    /**
     * Quantifier对应的Table（name作为表名）
     */
    private Table tName;
    /**
     * Quantifier连接的Box
     */
    private Box box;

    private IteratorType type;

    /**
     * Quantifier的类型
     */
    private QuantifierType quantifierType;

    /**
     * 与其他Quantifier的连接关系
     */
    private HashMap<Quantifier, Relation> relations = new HashMap <>();
    private List<Predicate> predicates = new ArrayList <>();

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

    public void addRelation(Quantifier quantifier, Predicate predicate) {

        if(relations.containsKey(quantifier)) {
            relations.get(quantifier).add(predicate);
        }else{
            relations.put(quantifier, new Relation(predicate));
        }

    }

    public void addCondition(Predicate predicate) {
        predicates.add(predicate);
    }

    public Table getTName() {
        return tName;
    }

    public FromItem getFromItem() {
        return fromItem;
    }

    public IteratorType type() {
        return type;
    }
}
