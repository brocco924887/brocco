package utility;

import java.sql.Connection;
import java.sql.DriverManager;

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
}
