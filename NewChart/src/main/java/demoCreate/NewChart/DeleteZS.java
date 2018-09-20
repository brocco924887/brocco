package demoCreate.NewChart;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteZS extends GetConnection{

	public static String deleteZS(Connection conn,String sql) {
		Statement st = null;
		conn = getConnection();
		String deleted = "deleted";
		
		try {
			sql = "delete from Module_Ballot where Student_Name = '张三'";
			
			st = conn.createStatement();
			st.executeUpdate(sql);
        }catch(SQLException e){
			e.printStackTrace();	
		}catch(Exception e) {
			e.printStackTrace();
		
		}finally {
			if (st != null) {
				try {
					st.close();
					
				}catch(SQLException e){
					e.printStackTrace();
				}
				
			}
		}
		
		return deleted;
	}
	
}
