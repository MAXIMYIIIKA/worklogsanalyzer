package classes.instances;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Max Nichipor on 26.07.2016.
 */
public class WorkLogWorkbook {

    private static Logger logger = Logger.getLogger(WorkLogWorkbook.class);

    private Workbook workbook;
    private int worklogsUsernameColumnIndex;
    private int worklogsProjectKeyColumnIndex;
    private int worklogsBilledHoursColumnIndex;
    private int peopleUsernameColumnIndex;
    private int peopleWorkedColumnIndex;
    private int peopleBilledColumnIndex;

    public WorkLogWorkbook(){}

    public WorkLogWorkbook(Workbook workbook){
        this.workbook = workbook;
        this.cellsInit();
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        if (this.workbook == null) {
            this.workbook = workbook;
            this.cellsInit();
        }
    }

    public int getWorklogsUsernameColumnIndex() {
        return worklogsUsernameColumnIndex;
    }

    public int getWorklogsProjectKeyColumnIndex() {
        return worklogsProjectKeyColumnIndex;
    }

    public int getWorklogsBilledHoursColumnIndex() {
        return worklogsBilledHoursColumnIndex;
    }

    public int getPeopleUsernameColumnIndex() {
        return peopleUsernameColumnIndex;
    }

    public int getPeopleWorkedColumnIndex() {
        return peopleWorkedColumnIndex;
    }

    public int getPeopleBilledColumnIndex() {
        return peopleBilledColumnIndex;
    }

    public Sheet getWorklogsSheet(){
        return this.workbook.getSheet("Worklogs");
    }

    public Sheet getPeopleSheet(){
        return this.workbook.getSheet("People");
    }

    private void cellsInit(){
        for(int i = 0; i < this.workbook.getNumberOfSheets(); i++) {
            Sheet sheet = this.workbook.getSheetAt(i);
            Row row = sheet.getRow(0);
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getStringCellValue().trim().toLowerCase()) {
                    case "username":
                        if (sheet.equals(this.getPeopleSheet())){
                            this.peopleUsernameColumnIndex = cell.getColumnIndex();
                            logger.debug("peopleUsernameColumnIndex is set to " + cell.getColumnIndex());
                            break;
                        } else {
                            this.worklogsUsernameColumnIndex = cell.getColumnIndex();
                            logger.debug("worklogsUsernameColumnIndex is set to " + cell.getColumnIndex());
                            break;
                        }
                    case "project key":
                        this.worklogsProjectKeyColumnIndex = cell.getColumnIndex();
                        logger.debug("worklogsProjectKeyColumnIndex is set to " + cell.getColumnIndex());
                        break;
                    case "billed hours":
                        this.worklogsBilledHoursColumnIndex = cell.getColumnIndex();
                        logger.debug("worklogsBilledHoursColumnIndex is set to " + cell.getColumnIndex());
                        break;
                    case "worked":
                        this.peopleWorkedColumnIndex = cell.getColumnIndex();
                        logger.debug("peopleWorkedColumnIndex is set to " + cell.getColumnIndex());
                        break;
                    case "billed":
                        this.peopleBilledColumnIndex = cell.getColumnIndex();
                        logger.debug("peopleBilledColumnIndex is set to " + cell.getColumnIndex());
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
