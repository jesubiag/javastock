package frd.test;

import java.sql.SQLException;
import java.util.Date;
import frd.db.LotManager;
import frd.model.Lot;


public class TestDBLot {
	public static void main( String[] args){
		System.out.println("*********** Iniciando TEST DBLOT ***********");
		try{
			//creo la tabla DBLOT
			LotManager.createDbLotTable();
			System.out.println( "Tabla Lots Creada!" );
			
			//cargo dos lots
			LotManager.insertLot(1, "24/08/2013" , 10);
			LotManager.insertLot(2, "05/12/1965" , 37);
			System.out.println( "Dos Lots creados!" );
			
			//obtengo los lots de la bd
			System.out.println( "Listando lots:" );
			for( Lot lot : LotManager.getLots() ){
				System.out.println( lot );
			}
				
			//modificando usuario
			LotManager.updateLot(2, new Date(), new Date (), 56546.5646 );
			System.out.println( "Lot 2 modificado!" );
			
			//obtengo los lots de la bd
			System.out.println( "Listando lots:" );
			for( Lot lot : LotManager.getLots() ){
				System.out.println( lot );
			}
				
			//borrar usuario
			LotManager.deleteLot(1);
			System.out.println( "Lot 1 eliminado!" );
			
			//obtengo los lots de la bd
			System.out.println( "Listando lots:" );
			for( Lot lot : LotManager.getLots() ){
				System.out.println( lot );
			}
		}
		catch(SQLException ex){
			System.err.println( "ERROR: " + ex.getLocalizedMessage() );
		}
		System.out.println("*********** Fin TEST DBLOT ***********");
	}
}
