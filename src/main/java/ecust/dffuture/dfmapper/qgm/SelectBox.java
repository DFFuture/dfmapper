package ecust.dffuture.dfmapper.qgm;

import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectItem;

import java.util.ArrayList;
import java.util.List;

public class SelectBox implements Box{

    private List<Item> output;

    private List<Quantifier> quantifiers;

    private SelectBody selectBody;

    private Quantifier reference;


    private void init() {
        output = new ArrayList <>();
        quantifiers = new ArrayList <>();
    }

    public SelectBox(SelectBody selectBody) {
        init();
        this.selectBody = selectBody;
    }

    public void add(Quantifier quantifier) {
        quantifiers.add(quantifier);
        quantifier.setContainer(this);
    }

    public void add(SelectItem selectItem) {
        output.add(new Item(selectItem));
    }

    public void setReference(Quantifier quantifier) {
        reference = quantifier;
    }

    public Quantifier getReference() {
        return reference;
    }

    public List<Quantifier> getQuantifiers() {
        return quantifiers;
    }

    public List<Item> getOutput() {
        return output;
    }

}
