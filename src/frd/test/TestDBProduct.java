package frd.test;

import java.sql.SQLException;

import frd.db.ProductManager;
import frd.model.Product;

public class TestDBProduct {


	public static void main(String[] args) {

		System.out.println("----------- Inicio Test DBPRODUCT ------");
		
		try{
			//creo tabla
			ProductManager.createDBProductTable();
			System.out.println("-Tabla creada.");
			
			//agrego productos
			ProductManager.insertProduct(1, "Producto 1", "Descripción del Producto 1");
			ProductManager.insertProduct(2, "Producto 2", "Descripción del Producto 2");
			System.out.println("-Dos productos agregados.");
			
			//muestro tabla
			System.out.println("-Listando productos:");
			for(Product product : ProductManager.getProducts()){
				System.out.println(product);
			}
			
			//modifico un producto
			ProductManager.updateProduct(1, "Producto 1 Mod", "Descripción del Producto 1 modificada");
			
			//muestro tabla
			System.out.println("-Listando productos:");
			for(Product product : ProductManager.getProducts()){
				System.out.println(product);
			}
			
			//elimino un producto
			ProductManager.deleteProduct(2);
			System.out.println("-Producto eliminado.");
			
			//muestro tabla
			System.out.println("-Listando productos:");
			for(Product product : ProductManager.getProducts()){
				System.out.println(product);
			}
			
			
			
		}catch(SQLException ex){
			System.err.println( "ERROR: " + ex.getLocalizedMessage() );
		}
		System.out.println("------ Fin TEST DBPRODUCT ------");

	}

}
