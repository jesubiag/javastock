package frd.app;

import java.util.Scanner;

public class Main {
	private static Scanner scanIn = new Scanner(System.in);

	public static void main(String[] args){
		System.out.println("*********** Iniciando ***********");
		System.out.println("q:salir, \n u:listar usuarios, \n u-add:agregar usuario, \n u-del:borrar usuario, \n p:listar productos, \n p-add:agregar producto, \n p-del:eliminar producto");
		 
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
			
			if( "p".equalsIgnoreCase(response)){ //muestra listado de productos
				ProductUI.showAll();
			}
			
			if( "p-add".equalsIgnoreCase(response)){ //agrega un producto
				ProductUI.add(scanIn);
			}
			
			if( "p-del".equalsIgnoreCase(response)){ //elimina un producto
				ProductUI.del(scanIn);
			}
			
			System.out.println(">");
			response = scanIn.nextLine();
		}
		
        System.out.println("*********** Fin de app ***********");
	}
}
