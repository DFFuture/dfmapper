package ecust.dffuture.dfmapper.qgm.box;

import ecust.dffuture.dfmapper.qgm.type.DistinctType;
import ecust.dffuture.dfmapper.qgm.Item;
import ecust.dffuture.dfmapper.qgm.Quantifier;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectItem;

import java.util.ArrayList;
import java.util.List;

public class SelectBox implements Box {

    /**
     * 输出表的列
     */
    private List<Item> output = new ArrayList <>();

    private List<Quantifier> quantifiers = new ArrayList <>();

    /**
     * 对应查询树中的SelectBody
     */
    private SelectBody selectBody;

    private DistinctType distinct;

    private Quantifier reference;

    public Quantifier getReference() {
        return reference;
    }

    public SelectBox(SelectBody selectBody) {
        this.selectBody = selectBody;
    }

    public void add(Quantifier quantifier) {
        quantifiers.add(quantifier);
        quantifier.setContainer(this);
    }

    public void add(SelectItem selectItem) {
        output.add(new Item(selectItem));
    }

    public List<Quantifier> getQuantifiers() {
        return quantifiers;
    }

    public List<Item> getOutput() {
        return output;
    }

    public void setReference(Quantifier quantifier) {
        reference = quantifier;
    }

}
