package frd.db;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import frd.model.Lot;

public class LotManager extends JDBCManager {
	
	public static String currentDate(Date someDate) {
		return "" + "to_date('"
			+ dateFormat.format(someDate.getTime()) + "', 'yyyy/mm/dd hh24:mi:ss')";
	}
	
	public static void createDbLotTable() throws SQLException{
		String createTableSQL = "CREATE TABLE DBLOT(" + 
			"LOT_ID NUMERIC(5) NOT NULL, " +
			"CREATE_DATE DATE NOT NULL, " +
			"DUE_DATE DATE NOT NULL, " +
			"INITIAL_AMOUNT NUMERIC(5), " +
			"CURRENT_AMOUNT NUMERIC(5), " +
			"PRIMARY KEY (LOT_ID)" +
			")";
		
		execute( createTableSQL );
	}
	
	public static void insertLot(int lotId, String dueDate, double initialAmount) throws SQLException{
		Date auxDate = new Date();
		try {
			auxDate = dateFormat.parse(dueDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String insertTableSQL = "INSERT INTO DBLOT" +
						"(LOT_ID, CREATE_DATE, DUE_DATE, INITIAL_AMOUNT, CURRENT_AMOUNT)" +
						"VALUES" +
						"("+lotId+","+currentDate(new Date())+","+auxDate+","+initialAmount+","+initialAmount+")";	
						// al principio, la cantidad actual es igual a la cantidad inicial
		
		executeUpdate( insertTableSQL );
		
	}
	
	public static void updateLot(int lotId, Date createDate, Date dueDate, double currentAmount) throws SQLException{
		String updateTableSQL = "UPDATE DBLOT" +
				" SET CURRENT_AMOUNT = '"+currentAmount+"' " +
				" WHERE LOT_ID = "+lotId;
		
		execute( updateTableSQL );
	}
	
	public static void deleteLot(int lotId) throws SQLException{
		String deleteTableSQL = "DELETE FROM DBLOT WHERE LOT_ID = "+lotId;
		
		execute( deleteTableSQL );
	}
	
	public static List<Lot> getLots() throws SQLException{
		List<Lot> result = new ArrayList<Lot>();
		
		String selectTableSQL = "SELECT * FROM DBLOT";
		
		for( HashMap<String,Object> register : executeQuery( selectTableSQL ) ) {
			
			Lot lot = new Lot();
			
			if( register.containsKey("lot_id") )
				lot.setId( ((BigDecimal) register.get("lot_id")).intValue() );
			
			if( register.containsKey("create_date") )
				lot.setCreateDate((Date) register.get("create_date") );
			
			if( register.containsKey("due_date") )
				lot.setDueDate((Date) register.get("due_date") );
			
			if( register.containsKey("initial_amount") )
				lot.setInitialAmount((double) register.get("initial_amount") );
			
			if( register.containsKey("current_amount") )
				lot.setCurrentAmount((double) register.get("current_amount") );
			
			result.add(lot);
		}
		
		return result;		
		
	}
}
