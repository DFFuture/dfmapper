package ecust.dffuture.dfmapper.qgm;

import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;

public class ColumnAnalyzer {

    private SelectBox currentBox;

    public void setCurrentBox(SelectBox box) {
        currentBox = box;
    }

    public Quantifier analyze(Column column) {
        Quantifier quantifier;
        if(column.getTable().getName() != null) {
            String tableReference = column.getTable().getName();
            quantifier = search(tableReference);

        }else {
            quantifier = searchTable(column.getColumnName());
        }
        column.setTable(new Table(quantifier.getName()));
        return quantifier;
    }

    public Quantifier search(String tableReference) {
        SelectBox box = currentBox;
        while(box != null) {
            for(Quantifier quantifier: currentBox.getQuantifiers()) {
                if(tableReference.equals(quantifier.getOriginalName())) {
                    return quantifier;
                }
                box = (SelectBox) box.getReference().getContainer();
            }
        }
        return null;
    }

    public Quantifier searchTable(String Column) {
        return null;
    }
}
