package frd.db;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import frd.model.User;

public class UserManager extends JDBCManager {
	public static void createDbUserTable() throws SQLException {
		String createTableSQL = "CREATE TABLE DBUSER("
				+ "USER_ID NUMERIC(5) NOT NULL, "
				+ "USERNAME VARCHAR(20) NOT NULL, "
				+ "CREATED_BY VARCHAR(20) NOT NULL, "
				+ "CREATED_DATE DATE NOT NULL, " + "PRIMARY KEY (USER_ID) "
				+ ")";

		execute( createTableSQL );
	}
	
	public static void insertUser(int userId, String username, String creator, Date creation) throws SQLException{
		String insertTableSQL = "INSERT INTO DBUSER"
			+ "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES"
			+ "("+userId+", '"+username+"', '"+creator+"', " + "to_date('"
			+ dateFormat.format(creation.getTime()) + "', 'yyyy/mm/dd hh24:mi:ss'))";
		
		executeUpdate( insertTableSQL );
	}
 
	public static void updateUser(int userId, String username, String creator, Date creation) throws SQLException{
		String updateTableSQL = "UPDATE DBUSER"
			+ " SET USERNAME = '"+username+"' "
			+ " WHERE USER_ID = "+userId;
		
		execute( updateTableSQL );

	}
	
	public static void deleteUser(int userId) throws SQLException{
		String deleteTableSQL = "DELETE FROM DBUSER WHERE USER_ID = "+userId;
		
		execute( deleteTableSQL );
	}
	
	public static List<User> getUsers() throws SQLException{
		List<User> result = new ArrayList<User>();
		
		String selectTableSQL = "SELECT * from DBUSER";
		
		for( HashMap<String,Object> register : executeQuery( selectTableSQL ) ){
			//Creo el usuario a partir de los datos obtenidos de la base
			User usr = new User();

			if( register.containsKey("user_id") )
				usr.setId( ((BigDecimal) register.get("user_id")).intValue() );
			
			if( register.containsKey("username") )
				usr.setUsername((String) register.get("username") );
			
			if( register.containsKey("created_by") )
				usr.setCreatedBy((String) register.get("created_by") );
			
			if( register.containsKey("create_date") )
				usr.setCreateDate((Date) register.get("create_date") );

			result.add( usr );
		}
		
		return result;
	}
}
