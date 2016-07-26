package classes.application;

import classes.instances.Analyzer;
import classes.instances.ExcelFileReader;
import classes.instances.WorkLogWorkbook;

import java.io.File;

/**
 * Created by Max Nichipor on 26.07.2016.
 */
public class runApplication {
    public static void main(String[] args) {
        WorkLogWorkbook workbook = new WorkLogWorkbook(ExcelFileReader.readWorkbook(new File("input.xls")));
        Analyzer analyzer = new Analyzer(workbook);
        analyzer.analizeWorklogs();
        System.out.println(analyzer.getUser("User1"));
    }
}
