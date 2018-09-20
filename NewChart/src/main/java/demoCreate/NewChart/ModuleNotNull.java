package demoCreate.NewChart;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ModuleNotNull extends GetConnection{
	
	// 创建视图
	public static String moduleNotNull(Connection conn, String sql) {
		Statement st = null;
		conn = getConnection();
		String created = "created new chart";  //定义返回值
		
		try {
		sql = "CREATE VIEW V AS SELECT "+
				"a.Module_Name, "+
				"b.Module_ID " +
				"FROM Module_Ballot AS b "+
				"INNER JOIN Module_List AS a ON b.Module_ID = a.Module_No "+ 
				"WHERE Module_ID IS NOT NULL";
		
		st = conn.createStatement();
		st.executeUpdate(sql);  // 执行更改
		
		}catch(SQLException e){  //错误捕捉
			e.printStackTrace();	
		}catch(Exception e) {
			e.printStackTrace();
		
		}finally {               //关闭方法
			if (st != null) { 
				try {
					st.close();
					
				}catch(SQLException e){
					e.printStackTrace();
				}
				
			}
		}
		return created;	
	}

}
