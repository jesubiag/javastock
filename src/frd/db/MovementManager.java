package frd.db;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import frd.model.Movement;
import frd.model.User;

public class MovementManager extends JDBCManager {

	public static void createMovementTable() throws SQLException {
		String createTableSQL = "CREATE TABLE MOVEMENT("
				+ "MOVEMENT_ID NUMERIC(5) NOT NULL, "
				+ "CREATED_DATE VARCHAR(20) NOT NULL, "
				+ "CREATED_AMOUNT NUMERIC(5)NOT NULL, " 
				+ "PRIMARY KEY (MOVEMENT_ID) "
				+ ")";

		execute( createTableSQL );
	}
	


public static void insertMovement(int id , int amount) throws SQLException{
	Date creation = new Date();
	String insertTableSQL = "INSERT INTO DBUSER"
		+ "(MOVEMENT_ID, CREATED_DATE, CREATED_AMOUNT) " + "VALUES"
		+ "(" + id + ", '" + amount + "', to_date('"
		+ dateFormat.format(creation.getTime()) + "', 'yyyy/mm/dd hh24:mi:ss'))";
	
	executeUpdate( insertTableSQL );
}
	
	
	public static void updateUser(Date date, User user, int amount) throws SQLException{
		String updateTableSQL = "UPDATE MOVEMENT"
			+ " AMOUNT = '"+amount+"' ";
			
		
		execute( updateTableSQL );

	}
	
	
	public static void deleteUser(int movementId) throws SQLException{
		String deleteTableSQL = "DELETE FROM MOVEMENT WHERE MOVEMENT_ID = "+movementId;
		
		execute( deleteTableSQL );
	}
	
	public static List<Movement> getUsers() throws SQLException{
		List<Movement> result = new ArrayList<Movement>();
		
		String selectTableSQL = "SELECT * from ";
		
		for( HashMap<String,Object> register : executeQuery( selectTableSQL ) ){
			//Creo el usuario a partir de los datos obtenidos de la base
			Movement movement = new Movement();

			if( register.containsKey("movement_id") )
				movement.setId( ((BigDecimal) register.get("movement_id")).intValue() );
			
			if( register.containsKey("amount") )
				movement.setAmount( ((int) register.get("amount")));

			result.add( movement );
		}
		
		return result;
	}
}

	
