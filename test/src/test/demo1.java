package test;

import java.sql.Connection;
import java.sql.DriverManager;

public class demo1 {
	private static Connection conn = null;
    public static Connection getConnection() {
	String driver = "com.mysql.cj.jdbc.Driver";  // 驱动程序类名
	String url = "jdbc:mysql://localhost:3306/test?" // 数据库地址
			+"useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC"; //设置编码类型 防止乱码                            
	String username = "root"; //用户名
	String password = "123qwe"; //未设置密码
	try {
		Class.forName(driver);
		conn = DriverManager.getConnection(url,username,password);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
	return conn;
    }
}

