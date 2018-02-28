package ecust.dffuture.dfmapper;

import ecust.dffuture.dfmapper.qgm.QGMBuilder;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Demo {
    public static void main(String[] args) throws FileNotFoundException, JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(new FileReader("queries/query0.tpl.sql"));
        Select selectStatement = (Select) statement;
        QGMBuilder qgmBuilder = new QGMBuilder();
        qgmBuilder.visit(selectStatement);
        System.out.println(selectStatement);

    }
}
