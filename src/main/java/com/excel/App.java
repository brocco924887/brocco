package com.excel;

import java.io.FileNotFoundException;
import java.io.IOException;

public class App {

	public static void main(String[] args) {
//		Excel代码练习：
//		a.实现输出全部Excel内容到控制台。
//		b.只输出第一行内容。
//		c.用代码，修改内容，并且保存。
		try {
			
			// ExcelUtil.readExcelToTxt("d:\\test.xlsx", "d:\\test_a.txt");//a.实现输出全部Excel内容到控制台。b.只输出第一行内容。
			// ExcelUtil.readExcelToExcel("d:\\test02.xlsx");
			// ExcelUtil.readExcelsToExcel(new String[] { "d:\\test.xlsx", "d:\\test01.xlsx", "d:\\test02.xlsx"
			//		}, "d:\\testTotal180912.xlsx");
			// ExcelUtil.modifyExcel("d:\\test.xlsx", 1, 1, "我们改变了单元格里面的内容！"); //c.用代码，修改内容，并且保存。
			 ExcelUtil.hasName(new String[] { "d:\\test.xlsx", "d:\\test01.xlsx", "d:\\test02.xlsx"
					}, "d:\\test.txt");
			// ExcelUtil.copyImage();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
