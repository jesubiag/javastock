package frd.app;

import java.sql.SQLException;
import java.util.Scanner;
import frd.db.ProductManager;
import frd.db.UserManager;
import frd.model.User;

public class ProductUI {

	public static void showAll() {
		System.out.println( "=================================" );
		System.err.println( "ID | NAME | CREATOR | DATE" );
		try {
			for( User u : UserManager.getUsers() ){
				System.out.println( u.getId() + " | " + u.getUsername() + " | " + u.getCreatedBy() + " | " + u.getCreateDate() );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println( "=================================" );
	}

	public static void add(Scanner scanIn) {
		System.out.println( "=>AGREGAR PRODUCTO" );
		System.out.println( ">Ingrese el Id:" );
		String id = scanIn.nextLine();
		System.out.println( ">Ingrese el Nombre:" );
		String name = scanIn.nextLine();
		System.out.println( ">Ingrese la Descripción:" );
		String description = scanIn.nextLine();
		try {
			ProductManager.insertProduct(Integer.parseInt(id), name, description);
			
		} catch (NumberFormatException e) {
			System.err.println("El ID ingresado es incorrecto!");
			
		} catch (SQLException e) {
			System.err.println("Error al guardar los datos en la base.");
		}
		System.out.println( "=================================" );
	}

	public static void del(Scanner scanIn) {
		System.out.println( "=>BORRAR PRODUCTO" );
		System.out.println( ">Ingrese el ID:" );
		String id = scanIn.nextLine();
		try {
			ProductManager.deleteProduct(Integer.parseInt(id));
	
		} catch (NumberFormatException e) {
			System.err.println("El ID ingresado es incorrecto!");
			
		} catch (SQLException e) {
			System.err.println("Error al eliminar el producto en la base.");
		}
		System.out.println( "=================================" );
	}

}

