package ecust.dffuture.dfmapper.qgm;

import ecust.dffuture.dfmapper.qgm.box.Box;
import ecust.dffuture.dfmapper.qgm.box.SelectBox;
import net.sf.jsqlparser.statement.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QGM {

    private Statement statement;

    private List<Box> boxes = new ArrayList <>();

    private HashMap<String, Quantifier> quantifiers = new HashMap <>();

    public QGM(Statement statement, Box box) {
        this(statement);
        boxes.add(box);
    }

    public QGM(Statement statement) {
        this.statement = statement;
    }

    public void add(Box box) {
        this.boxes.add(box);
    }

    public void put(String qName, Quantifier quantifier) {
        quantifiers.put(qName, quantifier);
    }

}
