package frd.app;

import java.util.Scanner;

public class Main {
	private static Scanner scanIn = new Scanner(System.in);

	public static void main(String[] args){
		System.out.println("*********** Iniciando ***********");
		System.out.println("q:salir, u:listar usuarios, u-add:agregar usuario, u-del:borrar usuario");
		 
		String response = scanIn.nextLine();
		while( !"q".equalsIgnoreCase(response) ){

			if( "u".equalsIgnoreCase(response) ){
				UserUI.showAll();
			}
			
			if( "u-add".equalsIgnoreCase(response) ){
				UserUI.add( scanIn );
			}
			
			if( "u-del".equalsIgnoreCase(response) ){
				UserUI.del( scanIn );
			}
			
			if( "p".equalsIgnoreCase(response)){
				ProductUI.showAll();
			}
			
			if( "p-add".equalsIgnoreCase(response)){
				ProductUI.add(scanIn);
			}
			
			if( "p-del".equalsIgnoreCase(response)){
				ProductUI.del(scanIn);
			}
			
			System.out.println(">");
			response = scanIn.nextLine();
		}
		
        System.out.println("*********** Fin de app ***********");
	}
}
