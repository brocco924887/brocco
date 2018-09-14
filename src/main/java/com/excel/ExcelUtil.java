package com.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;

public class ExcelUtil {

	/**
	 * 读excel内容
	 * 
	 * @param totalPath
	 *            excel的文件全路径
	 * @return 所有sheet页的字符串内容
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public static String readExcelToString(String totalPath) throws EncryptedDocumentException, IOException {
		InputStream inp = new FileInputStream(totalPath);
		XSSFWorkbook wb = (XSSFWorkbook) XSSFWorkbookFactory.create(inp);
		StringBuilder allContentStr = new StringBuilder();
		for (int sheetIx = 0; sheetIx < wb.getNumberOfSheets(); sheetIx++) {
			XSSFSheet sheet = (XSSFSheet) wb.getSheetAt(sheetIx);
			allContentStr.append("\n" + "<sheet页" + (sheetIx + 1) + ">内容如下：" + "\n");
			allContentStr.append(readSheet(sheet, true, true, 1, 1));
		}
		return allContentStr.toString();
	}

	/**
	 * 读excel内容
	 * 
	 * @param totalPath
	 *            excel的文件全路径
	 * @return 工作簿
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public static XSSFWorkbook readExcelToWorkbook(String totalPath) throws EncryptedDocumentException, IOException {
		InputStream inp = new FileInputStream(totalPath);
		XSSFWorkbook wb = (XSSFWorkbook) XSSFWorkbookFactory.create(inp);
		return wb;
	}

	/**
	 * 读excel内容的sheet页
	 * 
	 * @param xssfSheet
	 *            sheet页
	 * @param allCols
	 *            是否全列
	 * @param allRows
	 *            是否全行
	 * @param cols
	 *            读几列
	 * @param rows
	 *            读几行
	 * @return excel内容
	 */
	private static String readSheet(XSSFSheet xssfSheet, Boolean allCols, Boolean allRows, int cols, int rows) {
		if (allRows) {
			rows = xssfSheet.getLastRowNum();// 总行数
		}
		StringBuffer sBuffer = new StringBuffer();
		if (rows > 0) {
			// 循环sheet中的所有行
			for (int rowNum = 0; rowNum < rows+1; rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if(allCols) {
					cols = xssfRow.getLastCellNum();
				}
				if (xssfRow != null) {
					// 循环一行中的所有列
					for (int j = 0; j < cols; j++) {
						XSSFCell cell1 = xssfRow.getCell(j);
						sBuffer.append(cell1);
						sBuffer.append(" ");
					}
					// 一行结束后换行
					sBuffer.append("\n");
				}
//				else {
//					System.out.println("表格内容不能为空");
//					break;
//				}
			}
			System.out.println(sBuffer.toString());
		}
		return sBuffer.toString();
	}

	/**
	 * 读excel内容到txt格式文件里
	 * 
	 * @param totalPath
	 *            excel的文件全路径
	 * @param txtPath
	 *            txt的文件全路径
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void readExcelToTxt(String totalPath, String txtPath) throws FileNotFoundException, IOException {
		try (OutputStream fileOut = new FileOutputStream(txtPath)) {
			fileOut.write(readExcelToString(totalPath).getBytes());
		}
	}

	/**
	 * 读excel内容到excel格式文件里（目标可以是源文件）
	 * 
	 * @param sourcePath
	 * @param goalPath
	 * @return excel内容
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void readExcelToExcel(String sourcePath, String goalPath) throws FileNotFoundException, IOException {
		try (OutputStream fileOut = new FileOutputStream(goalPath)) {
			readExcelToWorkbook(sourcePath).write(fileOut);
		}
	}

	@SuppressWarnings("resource")
	public static void readExcelsToExcel(String[] sourcePaths, String goalPath)
			throws FileNotFoundException, IOException {
		try(FileOutputStream fileOut = new FileOutputStream(goalPath)){
		// 将所有类型的尽调excel文件合并成一个excel文件
		XSSFWorkbook newExcelCreat = new XSSFWorkbook();
		for (String fromExcelName : sourcePaths) {// 遍历每个源excel文件，fileNameList为源文件的名称集合
			InputStream in = new FileInputStream(fromExcelName);
			XSSFWorkbook fromExcel = new XSSFWorkbook(in);
			File file = new File(fromExcelName);
			String fileName = file.getName();
			for (int i = 0; i < fromExcel.getNumberOfSheets(); i++) {// 遍历每个sheet
				XSSFSheet oldSheet = fromExcel.getSheetAt(i);
				String sheetName = oldSheet.getSheetName();
				String skr = fileName+"_"+sheetName;
				XSSFSheet newSheet = newExcelCreat.createSheet(skr);
				copySheet(newExcelCreat, oldSheet, newSheet);
			}
		}
		newExcelCreat.write(fileOut);
		}
	}

	/**
	 * 修改excel某一个单元格内容
	 * 
	 * @param totalPath
	 *            excel文件全路径
	 * @param colnum
	 *            第几列
	 * @param rownum
	 *            第几行
	 * @param content
	 *            修改的内容
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void modifyExcel(String totalPath, int colnum, int rownum, String content)
			throws FileNotFoundException, IOException {
		try (InputStream inp = new FileInputStream(totalPath)) {
			Workbook wb = WorkbookFactory.create(inp);
			XSSFSheet sheet = (XSSFSheet) wb.getSheetAt(0);
			Row row = sheet.getRow(rownum - 1);
			Cell cell = row.createCell(colnum - 1);
			CellStyle cs = wb.createCellStyle();
			Font f = wb.createFont();
			// make it red
			f.setColor((short) Font.COLOR_RED);
			cs.setFont(f);
			cell.setCellStyle(cs);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(content);
			// Write the output to a file
			try (OutputStream fileOut = new FileOutputStream(totalPath)) {
				wb.write(fileOut);
			}
		}
	}
	
	
	public static void hasName(String[] sourcePaths, String txtPath)
			throws FileNotFoundException, IOException{
		for(String setOfPaths: sourcePaths) {
			FileInputStream inputFile = new FileInputStream(setOfPaths);
			XSSFWorkbook inputwb = new XSSFWorkbook(inputFile);
			File file = new File(setOfPaths);
			String fileName = file.getName();
			for (int index = 0; index < inputwb.getNumberOfSheets(); index++) {
				XSSFSheet sheet = inputwb.getSheetAt(index);
				String sheetName = sheet.getSheetName();
				for (int ind = 0; ind < sheet.getLastRowNum(); ind++) {
					XSSFRow rows = sheet.getRow(ind);
					if(rows != null) {
						for(int indices = 0; indices < rows.getLastCellNum(); indices++) {
							Cell cell = rows.getCell(indices);
							String str = "戴纪帆";
							switch(cell.getCellType()) {
								case NUMERIC:
									break;
								case BLANK:
									break;
								case STRING:
									if(cell.getStringCellValue().equals(str)) {
										CellAddress address = cell.getAddress();
										String celladdress = address.toString();
										System.out.println("文件名>"+fileName+"Sheet位置>"+sheetName+"单元格地址>"+celladdress);
									}
									break;
							default:
								break;
								
									
							
							}
							
							
						}
					}
					
					
				}
				
				
			}
		try(FileOutputStream outputstream = new FileOutputStream(txtPath)){
			inputwb.write(outputstream);
		}
			
		}
	
	}
	
	/**
	 * 读取excel中的图片并复制到指定路径
	 * @param sourcePaths
	 * @param goalPath
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void copyImage(String[] sourcePaths) 
			throws FileNotFoundException, IOException{	
		for(String setOfPaths: sourcePaths) {
			FileInputStream inm = new FileInputStream(setOfPaths);
			XSSFWorkbook readwb = new XSSFWorkbook(inm);
			File file = new File(setOfPaths);
			String fileName = file.getName();
			List lst = readwb.getAllPictures();
			for (Iterator It = lst.iterator(); It.hasNext();) {
				PictureData pic = (PictureData)It.next();
				byte[] data = pic.getData();
				int inn = lst.indexOf(pic);
				String ind = fileName + String.valueOf(inn);
				try(FileOutputStream fileOut = new FileOutputStream("c:\\Users\\Administrator\\Desktop\\setsOfPics"+"\\"+ind+".jpg")){
					fileOut.write(data);
				}
			}
			
		}
	}

	/**
	 * 拷贝单元格式
	 * 
	 * @param fromStyle
	 * @param toStyle
	 */
	private static void copyCellStyle(XSSFCellStyle fromStyle, XSSFCellStyle toStyle) {
		toStyle.cloneStyleFrom(fromStyle);// 此一行代码搞定
	}

	/**
	 * 合并sheet页
	 * 
	 * @param fromSheet
	 * @param toSheet
	 */
	private static void mergeSheetAllRegion(XSSFSheet fromSheet, XSSFSheet toSheet) {
		int num = fromSheet.getNumMergedRegions();
		CellRangeAddress cellR = null;
		for (int i = 0; i < num; i++) {
			cellR = fromSheet.getMergedRegion(i);
			toSheet.addMergedRegion(cellR);
		}
	}

	/**
	 * 拷贝单元格
	 * 
	 * @param wb
	 * @param fromCell
	 * @param toCell
	 */
	private static void copyCell(XSSFWorkbook wb, XSSFCell fromCell, XSSFCell toCell) {
		XSSFCellStyle newstyle = wb.createCellStyle();
		copyCellStyle(fromCell.getCellStyle(), newstyle);
		// toCell.setEncoding(fromCell.getEncoding());
		// 样式
		toCell.setCellStyle(newstyle);
		if (fromCell.getCellComment() != null) {
			toCell.setCellComment(fromCell.getCellComment());
		}
		// 不同数据类型处理
		CellType fromCellType = fromCell.getCellType();
		toCell.setCellType(fromCellType);
		if (fromCellType == CellType.NUMERIC) {
			if (DateUtil.isCellDateFormatted(fromCell)) {
				toCell.setCellValue(fromCell.getDateCellValue());
			} else {
				toCell.setCellValue(fromCell.getNumericCellValue());
			}
		} else if (fromCellType == CellType.STRING) {
			toCell.setCellValue(fromCell.getRichStringCellValue());
		} else if (fromCellType == CellType.BLANK) {
			// nothing21
		} else if (fromCellType == CellType.BOOLEAN) {
			toCell.setCellValue(fromCell.getBooleanCellValue());
		} else if (fromCellType == CellType.ERROR) {
			toCell.setCellErrorValue(fromCell.getErrorCellValue());
		} else if (fromCellType == CellType.FORMULA) {
			toCell.setCellFormula(fromCell.getCellFormula());
		} else { // nothing29
		}

	}

	/**
	 * 拷贝行
	 * 
	 * @param wb
	 * @param oldRow
	 * @param toRow
	 */
	private static void copyRow(XSSFWorkbook wb, XSSFRow oldRow, XSSFRow toRow) {
		toRow.setHeight(oldRow.getHeight());
		for (@SuppressWarnings("rawtypes")
		Iterator cellIt = oldRow.cellIterator(); cellIt.hasNext();) {
			XSSFCell tmpCell = (XSSFCell) cellIt.next();
			XSSFCell newCell = toRow.createCell(tmpCell.getColumnIndex());
			copyCell(wb, tmpCell, newCell);
		}
	}

	/**
	 * 拷贝sheet页
	 * 
	 * @param wb
	 * @param fromSheet
	 * @param toSheet
	 */
	private static void copySheet(XSSFWorkbook wb, XSSFSheet fromSheet, XSSFSheet toSheet) {
		mergeSheetAllRegion(fromSheet, toSheet);
		// 设置列宽
		XSSFRow indicate = fromSheet.getRow(0);
		if(indicate!=null) {
			for (int i = 0; i <= fromSheet.getRow(fromSheet.getFirstRowNum()).getLastCellNum(); i++) {
				toSheet.setColumnWidth(i, fromSheet.getColumnWidth(i));
			}	
		}
		else {
			System.out.println("工作表不能为空");
		}
		for (@SuppressWarnings("rawtypes")
		Iterator rowIt = fromSheet.rowIterator(); rowIt.hasNext();) {
			XSSFRow oldRow = (XSSFRow) rowIt.next();
			XSSFRow newRow = toSheet.createRow(oldRow.getRowNum());
			copyRow(wb, oldRow, newRow);
		}
	}
	
}
