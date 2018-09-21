package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import test.demo1;

public class demo {	
//	public static String[] addImage(Connection conn,String sql) throws SQLException
//	, IOException {
//		conn = demo1.getConnection();
//		for(int q = 0; q <= 99; ++q) {
//			String str = "insert into photo values(null, null)";
//			PreparedStatement ps = conn.prepareStatement(str);
//			String picname = copyImage()[q];
//			File pic = new File(picname);
//			InputStream is = new FileInputStream(pic);
//			ps.setBinaryStream(q, is, pic.length());
//			ps.executeUpdate();	
//		}		
//		return null;
//	}
	
	public static String[] copyImage() 
			throws FileNotFoundException, IOException{	
		String[] sourcePaths = new String[100];
		String[] picbos = new String[100];
		for(int i = 1; i<=100; ++i) {
			String index = String.valueOf(i);
			String Paths = "d:\\test_"+index+".xlsx";
			sourcePaths[i-1] = Paths;
		}
		for(String setOfPaths: sourcePaths) {
			FileInputStream inm = new FileInputStream(setOfPaths);
			XSSFWorkbook readwb = new XSSFWorkbook(inm);
			File file = new File(setOfPaths);
			String fileName = file.getName();
			List lst = readwb.getAllPictures();
			int t = 0;
			for (Iterator It = lst.iterator(); It.hasNext();) {
				
				PictureData pic = (PictureData)It.next();
				t = t++;
				byte[] data = pic.getData();
				int inn = lst.indexOf(pic);
				String ind = fileName + String.valueOf(inn);
				String stg = "c:\\Users\\"
						+ "Administrator\\Desktop\\setpic"+"\\"+ind+".jpg";
				picbos[t] = stg;
				
				try(FileOutputStream fileOut = new FileOutputStream(stg)){
					fileOut.write(data);
				}
			}
		}
		System.out.println(picbos[0]);
		return null;
	}
}
