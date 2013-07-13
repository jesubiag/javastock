package frd.db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JDBCManager {

	// 4 POSGRESS
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://127.0.0.1:5433/postgres";

	// 4 MYSQL
	//private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	//private static final String DB_CONNECTION = "jdbc:mysql://127.0.0.1:5432/dbname";
	
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "postgres";
	
	protected static final DateFormat dateFormat = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );

	private static Connection getDBConnection() {
		System.out.println("-------- PostgreSQL JDBC Connection Testing ------------");
 
		try {
			Class.forName( DB_DRIVER );
		} catch (ClassNotFoundException e) {
			System.out.println("Descargar el driver PostgreSQL JDBC - http://jdbc.postgresql.org/download.html - e incluirlo en el library path!");
			e.printStackTrace();
		}
 
		System.out.println("PostgreSQL JDBC Driver Registrado!");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection( DB_CONNECTION, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			System.out.println("Falla de coneccion! Ver detalles en consola...");
			e.printStackTrace();
		}
 
		if (connection != null) {
			System.out.println("Hecho, ahora se tiene el control de la base de datos!");
		} else {
			System.out.println("La coneccion ha fallado!");
		}
		
		return connection;
	}

	protected static void execute(String sql) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println( "RUNNING >> " + sql );
			statement.execute( sql );
			System.out.println("Query corrida exitosamente!");

		} catch (SQLException e) {
 			System.err.println(e.getMessage());
 		} finally {
 			if (statement != null) statement.close();
 			if (dbConnection != null) dbConnection.close();
 		}
	}

	protected static void executeUpdate(String sql) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
 
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
 
			System.out.println( "RUNNING >> " + sql );
			statement.executeUpdate(sql);
			System.out.println("Query corrida exitosamente!");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
 			if (statement != null) statement.close();
 			if (dbConnection != null) dbConnection.close();
		}
 	}
	
	protected static List<HashMap<String,Object>> executeQuery( String sql ) throws SQLException{
		List<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
		Connection dbConnection = null;
		Statement statement = null;
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
 
			System.out.println( "RUNNING >> " + sql );
			ResultSet rs = statement.executeQuery( sql );
			
			while (rs.next()) {
				ResultSetMetaData metaData = rs.getMetaData();
				HashMap<String,Object> register = new HashMap<String,Object>();
				
				int count = metaData.getColumnCount();
				for (int i = 1; i <= count; i++) {
					String columnName = metaData.getColumnName(i);
					register.put( columnName, rs.getObject(columnName) );
				}

				//Lo agrego a la lista de resultado
				result.add( register );
			}

			System.out.println("Query corrida exitosamente!");

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
 			if (statement != null) statement.close();
 			if (dbConnection != null) dbConnection.close();
		}
		
		return result;

	}
}
