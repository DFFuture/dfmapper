package ecust.dffuture.dfmapper.qgm;

import java.util.ArrayList;
import java.util.List;

public class Relation {

    List<Condition> conditions = new ArrayList <>();

    Quantifier[] quantifiers = new Quantifier[2];

    public Relation(Condition condition) {
        conditions.add(condition);
    }

    public void add(Condition condition) {
        conditions.add(condition);
    }
}
