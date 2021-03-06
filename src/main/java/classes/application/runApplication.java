package classes.application;

import classes.instances.*;

import java.io.File;

/**
 * Created by Max Nichipor on 26.07.2016.
 */
public class RunApplication {
    public static void main(String[] args) {
        WorkLogWorkbook workbook = new WorkLogWorkbook(ExcelFileReader.readWorkbook(new File(args[0])));
        Analyzer analyzer = new Analyzer(workbook);
        analyzer.analizeWorklogs();
        ReportGenerator reportGenerator = new ReportGenerator(analyzer);
        reportGenerator.doReport();
        ExcelFileWriter.write(reportGenerator.getReportWorkbook());
    }
}
