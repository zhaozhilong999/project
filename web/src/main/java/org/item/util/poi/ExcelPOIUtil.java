package org.item.util.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.item.util.poi.entity.ExcelEntity;
import org.item.util.poi.entity.SheetEntity;

/**
 * POI操作Excel工具类
 * 
 * @author ruansj
 */
public class ExcelPOIUtil {

	//private static final Logger logger = Logger.getLogger(ExcelPOIUtil.class);
//	/**
//	 * 格式化 number String
//	 */
//	private static DecimalFormat df = new DecimalFormat("0");// 
//	/**
//	 *  格式化日期字符串
//	 */
//	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
//	/**
//	 * 格式化数字
//	 */
//	private static DecimalFormat nf = new DecimalFormat("0.00");// 
	/**
	 * 2003后缀
	 */
	private static String MS_EXCEL_2003_SUFFIX = ".xls";
	/**
	 * 2007后缀
	 */
	private static String MS_EXCEL_2007_SUFFIX = ".xlsx";
	
	/**
	 * 逐行读取
	 * @param filePath 文件完整路径
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static ExcelEntity readExcel2Line(String filePath) throws FileNotFoundException,IOException {
		File file = new File(filePath);
		return readExcel2Line(file);
	}
	
	/**
	 * 逐行读取
	 * @param file Excel 文件
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static ExcelEntity readExcel2Line(File file) throws FileNotFoundException,IOException {
		if(null==file || !file.exists()){
			throw new FileNotFoundException();
		}
		if(file.getName().endsWith(MS_EXCEL_2003_SUFFIX)){
			return read2003Excel(file,null,true);
		}else if(file.getName().endsWith(MS_EXCEL_2007_SUFFIX)){
			return read2007Excel(file,null,true);
		}else{
			throw new RuntimeException("Error,file is not a valid excel.");
		}
	}
	/**
	 * 逐行读取
	 * @param 文件流方式读取
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static ExcelEntity readExcel2Line(InputStream inputStream,String fileName) throws FileNotFoundException,IOException {
		if(null==inputStream){
			throw new FileNotFoundException();
		}
		if(fileName.endsWith(MS_EXCEL_2003_SUFFIX)){
			return read2003Excel(null,inputStream,false);
		}else if(fileName.endsWith(MS_EXCEL_2007_SUFFIX)){
			return read2007Excel(null,inputStream,false);
		}else{
			throw new RuntimeException("Error,file is not a valid excel.");
		}
	}
	/**
	 * 读取 office 2003 excel
	 * @param file excel文件
	 * @return
	 * @throws IOException
	 */
	private static ExcelEntity read2003Excel(File file,InputStream inputStream,boolean b) throws IOException {
		InputStream is=null;
		if(b){
			is=new FileInputStream(file);
		}else{
			is=inputStream;
		}
		HSSFWorkbook hwb = new HSSFWorkbook(is);
		HSSFSheet sheet = hwb.getSheetAt(0);
		HSSFRow row = null;
		HSSFCell cell = null;
		//logger.debug("read office 2003 excel.");
		ExcelEntity excelBean = new ExcelEntity();
		List<SheetEntity> sheetBeanList = new LinkedList<SheetEntity>();
		for(int i=0;i<hwb.getNumberOfSheets();i++){
			sheet = hwb.getSheetAt(i);
			SheetEntity sheetBean = pareseExcelContent(sheet, row, cell);
			sheetBeanList.add(sheetBean);
		}
		if(b){
			excelBean.setFilePath(file.getAbsolutePath());
		}else{
			excelBean.setFilePath("");
		}
		excelBean.setSheets(sheetBeanList);
		return excelBean;
	}

	/**
	 * 读取Office 2007 excel
	 * @param file excel文件
	 * @return
	 * @throws IOException
	 */
	private static ExcelEntity read2007Excel(File file,InputStream inputStream,boolean b) throws IOException {
		//logger.debug("read office 2007 excel.");
		InputStream is=null;
		if(b){
			is=new FileInputStream(file);
		}else{
			is=inputStream;
		}
		XSSFWorkbook xwb = new XSSFWorkbook(is);
		XSSFSheet sheet = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		
		ExcelEntity excelBean = new ExcelEntity();
		List<SheetEntity> sheetBeanList = new LinkedList<SheetEntity>();
		for(int i=0;i<xwb.getNumberOfSheets();i++){
			sheet = xwb.getSheetAt(i);
			SheetEntity sheetBean = pareseExcelContent(sheet, row, cell);
			if(sheetBean==null){
				continue;
			}
			sheetBeanList.add(sheetBean);
		}
		if(b){
			excelBean.setFilePath(file.getAbsolutePath());
		}else{
			excelBean.setFilePath("");
		}
		excelBean.setSheets(sheetBeanList);
		return excelBean;
	}
	
	/**
	 * 解析Excel内容
	 * @param sheet
	 * @param row
	 * @param cell
	 * @return
	 */
	private static SheetEntity pareseExcelContent(Sheet sheet,Row row,Cell cell){
		if(sheet.getLastRowNum() == 0 && sheet.getPhysicalNumberOfRows() == 0){
			return null;
		}
		Object value = null;
		SheetEntity sheetBean = new SheetEntity();
		sheetBean.setSheetName(sheet.getSheetName());
		List<List<Object>> contentList = new LinkedList<List<Object>>();
		
		for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> line = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					line.add("");
					continue;
				}
				//System.out.println("----"+cell.getCellStyle().getDataFormatString());
				//System.out.println("----"+cell.getCellType());
				switch (cell.getCellType()) {
					case XSSFCell.CELL_TYPE_STRING:
						value = String.valueOf(cell.getStringCellValue());
						break;
					case XSSFCell.CELL_TYPE_NUMERIC:
						value = String.valueOf(cell.getNumericCellValue());
						/*
						0 General General 18 Time h:mm AM/PM
						1 Decimal 0 19 Time h:mm:ss AM/PM
						2 Decimal 0.00 20 Time h:mm
						3 Decimal #,##0 21 Time h:mm:ss
						4 Decimal #,##0.00 2232 Date/Time M/D/YY h:mm
						531 Currency "$"#,##0_);("$"#,##0) 37 Account. _(#,##0_);(#,##0)
						631 Currency "$"#,##0_);[Red]("$"#,##0) 38 Account. _(#,##0_);[Red](#,##0)
						731 Currency "$"#,##0.00_);("$"#,##0.00) 39 Account. _(#,##0.00_);(#,##0.00)
						831 Currency "$"#,##0.00_);[Red]("$"#,##0.00) 40 Account. _(#,##0.00_);[Red](#,##0.00)
						9 Percent 0% 4131 Currency _("$"* #,##0_);_("$"* (#,##0);_("$"* "-"_);_(@_)
						10 Percent 0.00% 4231 33 Currency _(* #,##0_);_(* (#,##0);_(* "-"_);_(@_)
						11 Scientific 0.00E+00 4331 Currency _("$"* #,##0.00_);_("$"* (#,##0.00);_("$"* "-"??_);_(@_)
						12 Fraction # ?/? 4431 33 Currency _(* #,##0.00_);_(* (#,##0.00);_(* "-"??_);_(@_)
						13 Fraction # ??/?? 45 Time mm:ss
						1432 Date M/D/YY 46 Time [h]:mm:ss
						15 Date D-MMM-YY 47 Time mm:ss.0
						16 Date D-MMM 48 Scientific ##0.0E+0
						17 Date MMM-YY 49 Text @
						* */
//						if(cell.getCellStyle().getDataFormatString().startsWith("\"$\"#")){
//							value = cell.getRichStringCellValue();
//						}else if("0%".equals(cell.getCellStyle().getDataFormatString())){
//							value = cell.getStringCellValue();
//						}else if("0.00%".equals(cell.getCellStyle().getDataFormatString())){
//							value = cell.getNumericCellValue();
//						}else if("0.00E+00".equals(cell.getCellStyle().getDataFormatString())){
//							value = cell.getNumericCellValue();
//						}else if ("@".equals(cell.getCellStyle().getDataFormatString())) {
//							value = df.format(cell.getNumericCellValue());
//						} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
//							value = df.format(cell.getNumericCellValue());
//						}else if ("0.00_".equals(cell.getCellStyle().getDataFormatString())) {
//							value = nf.format(cell.getNumericCellValue());
//						} else {
//							value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
//						}
						break;
					//如果是Excel公式则将公式首先转换为数字，如果转换异常则转换为字符串
					case XSSFCell.CELL_TYPE_FORMULA:
						try{
							value = String.valueOf(cell.getRichStringCellValue());
						}catch (Exception e) {
							value = String.valueOf(cell.getNumericCellValue());
						}
						
						break;
					case XSSFCell.CELL_TYPE_BOOLEAN:
						value = String.valueOf(cell.getBooleanCellValue());
						break;
					case XSSFCell.CELL_TYPE_BLANK:
						value = "";
						break;
					default:
						value = cell.toString();
				}
				if (value == null) {
					value = "";
				}
				line.add(value);
			}
//			System.out.println(line.size());
//			System.out.println(line.toString());
			contentList.add(line);
		}
		sheetBean.setContentList(contentList);
		
		return sheetBean;
	}
	
	/**
	 * 创建Excel文件
	 * @param excelEntity Excel封装对象（仅支持.xls和.xlsx）
	 * @return
	 * @throws Exception
	 */
	public static boolean createExcelFile(ExcelEntity excelEntity) throws Exception {
		boolean flag = false;
        Workbook workbook = null;
		if(excelEntity.getFilePath().endsWith(MS_EXCEL_2003_SUFFIX)){
			workbook = new HSSFWorkbook();
		}else if(excelEntity.getFilePath().endsWith(MS_EXCEL_2007_SUFFIX)){
			workbook = new XSSFWorkbook();
		}else{
			throw new RuntimeException("Error,it is not a valid ms excel suffix.");
		}
        if(workbook != null) {
        	List<SheetEntity> list = excelEntity.getSheets();
        	for(SheetEntity sheetEntity : list){
        		 Sheet sheet = workbook.createSheet(sheetEntity.getSheetName());
                 createSheet(workbook, sheet, sheetEntity.getTitleList(), sheetEntity.getContentList());
        	}
            OutputStream fos = new FileOutputStream(excelEntity.getFilePath());
            workbook.write(fos);
            fos.flush();
            fos.close();
            flag = true;
        }
        return flag;
    }
	
	private static void createSheet(Workbook workbook,Sheet sheet,List<String> titleList,List<List<Object>> contentList){
		Row row0 = sheet.createRow(0);
        int index = 0;
        if(null!=titleList && titleList.size()>0){
        	index+=1;
        	for(int i = 0; i < titleList.size(); i++) {
                Cell cell_1 = row0.createCell(i, Cell.CELL_TYPE_STRING);
                CellStyle style = getStyle(workbook);
                cell_1.setCellStyle(style);
                cell_1.setCellValue(titleList.get(i));
                sheet.autoSizeColumn(i);
            }
        }
        
        if(null!=contentList && contentList.size()>0){
        	for (int rowNum = 0; rowNum <contentList.size(); rowNum++) {
                 Row row = sheet.createRow(index+rowNum);
				List<Object> list = contentList.get(rowNum);
                 for(int i = 0; i < list.size(); i++) {
                     Cell cell = row.createCell(i, Cell.CELL_TYPE_STRING);
                     cell.setCellValue((String)list.get(i));
                 }
            }
        }
	}
	
    private static CellStyle getStyle(Workbook workbook){
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER); 
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        // 设置单元格字体
        Font headerFont = workbook.createFont(); // 字体
        headerFont.setFontHeightInPoints((short)14);
        headerFont.setColor(HSSFColor.BLACK.index);
        headerFont.setFontName("宋体");
        style.setFont(headerFont);
        style.setWrapText(true);
        // 设置单元格边框及颜色
        style.setBorderBottom((short)1);
        style.setBorderLeft((short)1);
        style.setBorderRight((short)1);
        style.setBorderTop((short)1);
        style.setWrapText(true);
        return style;
    }
    public static void main(String[] args) throws Exception {
    	//    	ExcelEntity line = readExcel2Line("C:\\Users\\Administrator\\Desktop\\temp\\支付对账.xls");
//    	System.out.println(JSON.toJSON(line));
    	ExcelEntity ex=new ExcelEntity();
    	ex.setFilePath("C:\\Users\\Administrator\\Desktop\\temp\\Excel.xls");
    	List<SheetEntity> sheets=new ArrayList<>();
    	SheetEntity sheet=new SheetEntity();
    	sheet.setSheetName("测试生成Excel");
    	List<String> t=new ArrayList<>();
    	t.add("姓名");
    	t.add("年龄");
    	t.add("性别");
    	t.add("爱好");
		sheet.setTitleList(t);
		List<List<Object>> c=new ArrayList<>();
		List<Object> o=new ArrayList<>();
		o.add("赵志龙");
		o.add("26");
		o.add("男");
		o.add("很多");
		c.add(o);
		List<Object> o2=new ArrayList<>();
		o2.add("李小龙");
		o2.add("26");
		o2.add("男");
		o2.add("看书");
		c.add(o2);
		sheet.setContentList(c);
		sheets.add(sheet);
		ex.setSheets(sheets);
    	createExcelFile(ex);
	}
}
