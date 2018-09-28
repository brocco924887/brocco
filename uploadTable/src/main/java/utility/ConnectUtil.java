package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectUtil {
	private static Connection conn = null;// �������Ӷ���
	
	
	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		ConnectUtil.conn = conn;
	}

	// ���ݿ�����
	public static Connection getConnection() {
		String user="root"; 
		String pass="123qwe";
		String driver = "com.mysql.jdbc.Driver";// ������������
		String url = "jdbc:mysql://localhost:3306/test?" // ���ݿ�URL
				+ "useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC";// ��ֹ����
		try {
			Class.forName(driver);// ע��(����)��������
			conn = DriverManager.getConnection(url, user, pass);// ��ȡ���ݿ�����
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void CreateTable() throws SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		String sql = "CREATE TABLE webdata (\n" +
				"	NAME VARCHAR ( 10 ) DEFAULT NULL,\n" +
				"	Sex VARCHAR ( 10 ) DEFAULT NULL,\n" +
				"	Phone VARCHAR ( 11 ) DEFAULT NULL,\n" +
				"	Address VARCHAR ( 100 ) DEFAULT NULL\n" +
				"	)";
		stmt.executeUpdate(sql);
	}
}
