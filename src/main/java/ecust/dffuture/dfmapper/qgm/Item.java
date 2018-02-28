package ecust.dffuture.dfmapper.qgm;

import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;

public class Item {

    private String name;

    private SelectItem selectItem;


    public Item(SelectItem selectItem) {
        this.selectItem = selectItem;
        if(selectItem instanceof SelectExpressionItem) {
            if(((SelectExpressionItem) selectItem).getAlias() != null) {
                name = ((SelectExpressionItem) selectItem).getAlias().getName();
            }else if(((SelectExpressionItem) selectItem).getExpression() instanceof Column){
                name = ((Column) ((SelectExpressionItem) selectItem).getExpression()).getColumnName();
            }else {
                name = selectItem.toString();
            }
        }
    }

    public String getName() {
        return name;
    }
}
