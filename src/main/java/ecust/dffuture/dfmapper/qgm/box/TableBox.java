package ecust.dffuture.dfmapper.qgm.box;

import ecust.dffuture.dfmapper.qgm.Item;
import ecust.dffuture.dfmapper.qgm.Quantifier;
import net.sf.jsqlparser.schema.Table;

import java.util.ArrayList;
import java.util.List;

public class TableBox implements Box {
    private Quantifier reference;
    private List<Item> output = new ArrayList <>();

    private Table table;

    public TableBox(Table table) {
        this.table = table;
    }

    public void setReference(Quantifier quantifier) {
        reference = quantifier;
    }

    public Quantifier getReference() {
        return reference;
    }

    public List<Item> getOutput() {
        return output;
    }
}
