package ecust.dffuture.dfmapper.qgm;

import ecust.dffuture.dfmapper.qgm.box.SelectBox;
import ecust.dffuture.dfmapper.qgm.type.IteratorType;
import ecust.dffuture.dfmapper.qgm.type.QuantifierType;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;

/**
 * 列分析，分析列与Quantifier之间的关系
 */
public class ColumnAnalyzer {

    private SelectBox currentBox;

    public void setCurrentBox(SelectBox box) {
        currentBox = box;
    }

    /**
     * 分析column，判断该column属于哪个Quantifier
     * @param column 查询树上的列
     * @return Quantifier
     */
    public Quantifier analyze(Column column) {
        Quantifier quantifier;

        if(column.getTable().getName() != null) {
            // 根据列的表引用来判断
            String tableReference = column.getTable().getName();
            quantifier = search(tableReference);

        }else {
            quantifier = searchTable(column.getColumnName());
        }
        column.setTable(new Table(quantifier.getName()));
        return quantifier;
    }

    /**
     * 查找表引用对应的Quantifier
     * @param tableReference column上的表引用
     * @return Quantifier
     */
    public Quantifier search(String tableReference) {
        SelectBox box = currentBox;
        while(box != null) {
            for(Quantifier quantifier: box.getQuantifiers()) {
                if(tableReference.equals(quantifier.getOriginalName())) {
                    return quantifier;
                }
            }
            // 向上查找
            box = (SelectBox) box.getReference().getContainer();
        }
        return null;
    }

    /**
     * 根据连接的表输出的列名来查找列对应的表
     * @param ColumnName 列名
     * @return
     */
    public Quantifier searchTable(String ColumnName) {
        SelectBox box = currentBox;
        while(box != null) {
            for(Quantifier quantifier: box.getQuantifiers()) {
                if(quantifier.type() == IteratorType.SETFORMER) {

                }
            }
            // 向上查找
            box = (SelectBox) box.getReference().getContainer();
        }
        return null;
    }
}
