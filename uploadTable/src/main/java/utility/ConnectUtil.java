package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectUtil {
	private static Connection conn = null;// 声明连接对象
	
	
	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		ConnectUtil.conn = conn;
	}

	// 数据库连接
	public static Connection getConnection() {
		String user="root"; 
		String pass="123qwe";
		String driver = "com.mysql.jdbc.Driver";// 驱动程序类名
		String url = "jdbc:mysql://localhost:3306/test?" // 数据库URL
				+ "useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC";// 防止乱码
		try {
			Class.forName(driver);// 注册(加载)驱动程序
			conn = DriverManager.getConnection(url, user, pass);// 获取数据库连接
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
