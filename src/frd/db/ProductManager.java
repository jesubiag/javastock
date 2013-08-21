package frd.db;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import frd.model.Product;

public class ProductManager extends JDBCManager {
	
	public static void createDBProductTable() throws SQLException {
		String createTableSQL = "CREATE TABLE DBPRODUCT("
				+ "PROD_ID NUMERIC(5) NOT NULL, "
				+ "PROD_NAME VARCHAR(20) NOT NULL, "
				+ "PROD_DESC VARCHAR(120), "
				+ "PRIMARY KEY (PROD_ID) "
				+ ")";

		execute( createTableSQL );
	}

	public static void insertProduct(int prodId, String prodName, String prodDescription) throws SQLException{
		String insertTableSQL = "INSERT INTO DBPRODUCT"
			+ "(PROD_ID, PROD_NAME, PROD_DESC) " + "VALUES"
			+ "(" + prodId + ", '" + prodName + "', '" + prodDescription + "')";
		
		executeUpdate( insertTableSQL );
	}



	public static void updateProduct(int prodId, String prodName, String prodDescription) throws SQLException{
		String updateTableSQL = "UPDATE DBPRODUCT"
			+ " SET PROD_NAME = '" + prodName + "' "
			+ " ,PROD_DESC = '" + prodDescription + "' "
			+ " WHERE PROD_ID = "+ prodId;
		
		execute( updateTableSQL );
	}
	
	public static void deleteProduct(int prodId) throws SQLException{
		String deleteTableSQL = "DELETE FROM DBPRODUCT WHERE PROD_ID = " + prodId;
		
		execute( deleteTableSQL );
	}
	
	public static List<Product> getProducts() throws SQLException{
		List<Product> result = new ArrayList<Product>();
		
		String selectTableSQL = "SELECT * from DBPRODUCT";
		
		for( HashMap<String,Object> prodList : executeQuery( selectTableSQL ) ){
			//Creo el producto a partir de los datos obtenidos de la base
			Product product = new Product();

			if( prodList.containsKey("prod_id") )
				product.setId( ((BigDecimal) prodList.get("prod_id")).intValue() );
			
			if( prodList.containsKey("prod_name") )
				product.setName((String)prodList.get("prod_name"));
			
			if( prodList.containsKey("prod_desc") )
				product.setDescription((String)prodList.get("prod_desc"));

			result.add( product );
		}
		
		return result;
	}

}