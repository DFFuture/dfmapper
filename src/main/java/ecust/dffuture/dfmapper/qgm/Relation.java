package ecust.dffuture.dfmapper.qgm;

import java.util.ArrayList;
import java.util.List;

/**
 * 两个Quantifier之间的关系
 */
public class Relation {

    /**
     * Quantifier之间的连接条件
     */
    List<Predicate> predicates = new ArrayList <>();

    /**
     * 两个Quantifier
     */
    Quantifier[] quantifiers = new Quantifier[2];

    public Relation(Predicate predicate) {
        predicates.add(predicate);
    }

    /**
     * 添加连接条件
     * @param predicate 连接条件
     */
    public void add(Predicate predicate) {
        predicates.add(predicate);
    }

    public List <Predicate> getPredicates() {
        return predicates;
    }

    public void setPredicates(List <Predicate> predicates) {
        this.predicates = predicates;
    }

    public Quantifier[] getQuantifiers() {
        return quantifiers;
    }

    public void setQuantifiers(Quantifier[] quantifiers) {
        this.quantifiers = quantifiers;
    }

}
