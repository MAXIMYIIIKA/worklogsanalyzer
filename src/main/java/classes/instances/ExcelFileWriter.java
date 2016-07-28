package classes.instances;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Max Nichipor on 26.07.2016.
 */
public class ExcelFileWriter {

    private static Logger logger = Logger.getLogger(ExcelFileWriter.class);

    public static void write(Workbook workbook){
        File outputfile = new File("output.xlsx");
        FileOutputStream out = null;
        try{
            out = new FileOutputStream(outputfile);
            workbook.write(out);
        } catch (FileNotFoundException e){
            logger.error(e);
        } catch (IOException e){
            logger.error(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e){
                logger.error(e);
            }
        }
    }
}
