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
        Row row = null;
        Cell cell = null;
        for (Project project: analyzer.getProjects()){
            j = 0;
            if (sheet.getRow(j) == null) {
                sheet.createRow(j).createCell(i + 2).setCellValue(project.getProjectName());
            } else {
                sheet.getRow(j).createCell(i + 2).setCellValue(project.getProjectName());
            }
            for (User user: analyzer.getUsers()){
                if (user.getWorklog().containsKey(project)) {
                    j++;
                    if (sheet.getRow(j) == null) {
                        sheet.createRow(j).createCell(i + 1).setCellValue(user.getUsername());
                        sheet.getRow(j).createCell(i + 2).setCellValue(user.getWorklog().get(project));
                    } else {
                        sheet.getRow(j).createCell(i + 1).setCellValue(user.getUsername());
                        sheet.getRow(j).createCell(i + 2).setCellValue(user.getWorklog().get(project));
                    }
                }
            }
            i += 3;
        }
        logger.debug("Report created successfully");
    }
}
