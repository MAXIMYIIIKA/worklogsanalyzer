package classes.instances;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Iterator;

/**
 * Created by Max Nichipor on 26.07.2016.
 */
public class ReportGenerator {

    private static Logger logger = Logger.getLogger(ReportGenerator.class);

    private Analyzer analyzer;
    private Workbook reportWorkbook;

    public ReportGenerator(Analyzer analyzer){
        this.analyzer = analyzer;
        reportWorkbook = new XSSFWorkbook();
    }

    public Analyzer getAnalyzer() {
        return analyzer;
    }

    public Workbook getReportWorkbook() {
        return reportWorkbook;
    }

    public void doReport(){
        logger.debug("Doing report...");
        Sheet sheet = this.reportWorkbook.createSheet();
        int i = -1;
        int j = 0;
        Iterator<Row> rowIterator = null;
        Row row = null;
        Cell cell = null;
        for (Project project: analyzer.getProjects()){
            rowIterator = sheet.rowIterator();
            if (!rowIterator.hasNext()){
                sheet.createRow(j);
                j++;
            }
            row = rowIterator.next();
            row.createCell(i + 2).setCellValue(project.getProjectName());
            for (User user: analyzer.getUsers()){
                if (!rowIterator.hasNext()){
                    sheet.createRow(j);
                    j++;
                }
                row = rowIterator.next();
                row.createCell(i + 1).setCellValue(user.getUsername());
                row.createCell(i + 2).setCellValue(user.getWorklog().get(project));
            }
            i += 2;
        }
        logger.debug("Report created successfully");
    }
}
