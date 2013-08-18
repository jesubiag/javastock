package frd.app;

import java.sql.SQLException;
import java.util.Scanner;

import frd.db.LotManager;
import frd.model.Lot;

public class LotUI {
	
	public static void showAll() {
		System.out.println( "=================================" );
		System.err.println( "LOT_ID | CREATE_DATE | DUE_DATE | INITIAL_AMOUNT | CURRENT_AMOUNT" );
		try {
			for( Lot l : LotManager.getLots() ){
				System.out.println( l.getId() + " | " + l.getCreateDate() + " | " + l.getDueDate() + " | " + l.getInitialAmount() + " | " + l.getCurrentAmount() );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println( "=================================" );
	}
	
	public static void add(Scanner scanIn) {
		System.out.println( "=>AGREGAR LOTE" );
		System.out.println( ">Ingrese el ID:" );
		String id = scanIn.nextLine();
		System.out.println( ">Ingrese la FECHA DE CADUCIDAD:" );
		String dueDate = scanIn.nextLine();
		System.out.println( ">Ingrese la CANTIDAD INICIAL:" );
		String initialAmount = scanIn.nextLine();
		try {
			LotManager.insertLot( Integer.parseInt(id), dueDate, Double.parseDouble(initialAmount) );
		} catch (NumberFormatException e) {
			System.err.println("El ID ingresado es incorrecto!");
		} catch (SQLException e) {
			System.err.println("Error al guardar los datos en la base.");
		}
		System.out.println( "=================================" );
	}
	
	public static void del(Scanner scanIn) {
		System.out.println( "=>BORRAR LOTE" );
		System.out.println( ">Ingrese el ID:" );
		String id = scanIn.nextLine();
		try {
			LotManager.deleteLot( Integer.parseInt(id) );
		} catch (NumberFormatException e) {
			System.err.println("El ID ingresado es incorrecto!");
		} catch (SQLException e) {
			System.err.println("Error al eliminar el lote en la base.");
		}
		System.out.println( "=================================" );
	}
	
}
