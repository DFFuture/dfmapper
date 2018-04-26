package ecust.dffuture.dfmapper.qgm;

import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;

public class Item {

    /**
     * 输出的列名
     */
    private String name;
    /**
     * 选择项
     */
    private SelectItem selectItem;

    /**
     * 如果选择项有别名，则输出列名为该别名；
     * 如果选择项没有别名，则输出列名为其本身（转化为字符串）
     * @param selectItem 查询树上的选择项
     */
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
