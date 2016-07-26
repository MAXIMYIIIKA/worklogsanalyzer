package classes.instances;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by Max Nichipor on 26.07.2016.
 */
public class ExcelFileReader {

    private static Logger logger = Logger.getLogger(ExcelFileReader.class);

    public static Workbook readWorkbook(File inputFile){
        if (inputFile != null) {
            Workbook workbook = null;
            try {
                workbook = WorkbookFactory.create(inputFile);
            } catch (IOException e) {
                logger.error(e);
            } catch (InvalidFormatException e){
                logger.error(e);
            }
            return workbook;
        }
        return null;
    }
}
