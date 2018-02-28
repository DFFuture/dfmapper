package ecust.dffuture.dfmapper.qgm;

import net.sf.jsqlparser.statement.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QGM {

    private Statement statement;

    private List<SelectBox> selectBoxes = new ArrayList <>();

    private HashMap<String, Quantifier> quantifiers = new HashMap <>();

    public QGM(Statement statement, SelectBox box) {
        this(statement);
        selectBoxes.add(box);
    }

    public QGM(Statement statement) {
        this.statement = statement;
    }

    public void add(SelectBox selectBox) {
        this.selectBoxes.add(selectBox);
    }

    public void put(String qName, Quantifier quantifier) {
        quantifiers.put(qName, quantifier);
    }

}
