package test;

import java.sql.Connection;
import java.sql.DriverManager;

public class demo1 {
	private static Connection conn = null;
    public static Connection getConnection() {
	String driver = "com.mysql.cj.jdbc.Driver";  // ������������
	String url = "jdbc:mysql://localhost:3306/test?" // ���ݿ��ַ
			+"useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC"; //���ñ������� ��ֹ����                            
	String username = "root"; //�û���
	String password = "123qwe"; //δ��������
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

