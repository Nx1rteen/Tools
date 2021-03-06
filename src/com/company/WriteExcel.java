import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by macos on 2018/8/9.
 */
public class WriteExcel {

    public WriteExcel() {
    }

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    private static Workbook getWorkbook(InputStream in, File file) {
        Workbook workbook = null;
        try {
            if (file.getName().endsWith(EXCEL_XLS)) {  //Excel 2003
                workbook = new HSSFWorkbook(in);
            } else if (file.getName().endsWith(EXCEL_XLSX)) {  // Excel 2007/2010
                workbook = new XSSFWorkbook(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }

    private static void checkExcelVaild(File file) throws Exception {
        if (!file.exists()) {
            throw new Exception("文件不存在！");
        }
        if (!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))) {
            throw new Exception("不是Excel类型文件");
        }

    }

    public Sheet write2Excel(String fileName, String sheetName, List list) throws Exception {
        File file = new File(fileName);
        checkExcelVaild(file);
        InputStream inputStream = new FileInputStream(file);
        Workbook workbook = WriteExcel.getWorkbook(inputStream, file);
        Sheet sheet = workbook.getSheet(sheetName);
        return sheet;
    }
}

