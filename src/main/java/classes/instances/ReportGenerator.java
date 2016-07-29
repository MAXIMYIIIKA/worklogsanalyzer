package classes.instances;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
        int currentCell = -1;
        for (Project project: analyzer.getProjects()){
            int currentRow = 0;
            if (sheet.getRow(currentRow) == null) {
                sheet.createRow(currentRow).createCell(currentCell + 2).setCellValue(project.getProjectName());
            } else {
                sheet.getRow(currentRow).createCell(currentCell + 2).setCellValue(project.getProjectName());
            }
            for (User user: analyzer.getUsers()){
                if (user.getWorklog().containsKey(project)) {
                    currentRow++;
                    if (sheet.getRow(currentRow) == null) {
                        sheet.createRow(currentRow).createCell(currentCell + 1).setCellValue(user.getUsername());
                        sheet.getRow(currentRow).createCell(currentCell + 2).setCellValue(user.getWorklog().get(project));
                    } else {
                        sheet.getRow(currentRow).createCell(currentCell + 1).setCellValue(user.getUsername());
                        sheet.getRow(currentRow).createCell(currentCell + 2).setCellValue(user.getWorklog().get(project));
                    }
                }
            }
            currentCell += 3;
        }
        logger.debug("Report created successfully");
    }
}
