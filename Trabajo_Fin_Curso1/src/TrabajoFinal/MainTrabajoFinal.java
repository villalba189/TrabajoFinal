package TrabajoFinal;

import java.sql.*;
import java.util.Scanner;

public class MainTrabajoFinal {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
	
	}

	private static void menuPrincipal() {
		int eleccion;
		 do {
		        System.out.println("Introduzca la opción que desea realizar: "+
		        		"\n1. Menú Categorias.\n" +
		                "2. Menú Productos.\n" +
		                "3. Menú Clientes.\n" +
		                "0. Salir.\n");
		        eleccion = sc.nextInt();
		       
		        switch (eleccion) {
		            case 0:
		            	System.out.println("Bye Bye");
		                break;
		            case 1:
		            	menuCategorias();
		                break;
		                
		            case 2:
		            	menuProductos();
		                break;
		                
		            case 3:
		            	menuClientes();
		                break;
		                
		            default:
		                break;
		        }
		    }while (eleccion!=0);
	}
	
	

	private static void menuCategorias() {
		int eleccion;
		do {
		    System.out.println("Menú categorias: "+
		    		"\n1. Mostrar categorias.\n" +
		            "2. Añadir categorias.\n" +
		            "3. Mostrar productos de la categoria.\n" +
		            "4. Añadir producto a la categoria .\n" +
		            "0. Volver al menú principal.\n");
		    eleccion = sc.nextInt();
		   
		    switch (eleccion) {
		        case 0:
		        	
		            break;
		        case 1:
		        	verCategorias();
		            break;
		        case 2:
		        	crearCategorias();
		            break;
		            
		        case 3:
				verProductosCategoria();
		            break;
		        case 4:
		        	
				anadirProductoCategoria();
		            break;
		        default:
		            break;
		    }
		}while (eleccion!=0);
	}
	
 private static void crearCategorias( ) {
		// TODO Auto-generated method stub
	 Categoria nuevaCategoria = new Categoria();
		System.out.println("dame le nombre de la categoria:");
		String nombreCategoria = sc.nextLine();
		if (nombreCategoria.isEmpty()) {
            System.out.println("Error: No has puesto el nombre.");
        } else {
        	nuevaCategoria.setNombre(nombreCategoria);
        	
        }
	}

private static void verCategorias() {
		// TODO Auto-generated method stub
	 
	try {
		String buscarCategorias = "SELECT * FROM categoria";
		PreparedStatement stmtbuscarCategorias;
		stmtbuscarCategorias = conexion.prepareStatement(buscarCategorias);
		ResultSet rsbuscarCategorias = stmtbuscarCategorias.executeQuery();
		
	    	 while (rsbuscarCategorias.next()) {
		    	 
		    	 System.out.println(cont + " " + rsbuscarCategorias.getString("Nombre"));
		    	 cont++;
		    	 }
	} catch (SQLException e) {
		System.out.println("Error en la operación: " + e.getMessage());
	}
    
}
	


private static void menuClientes() {
	// TODO Auto-generated method stub
	
}

private static void menuProductos() {
	// TODO Auto-generated method stub
	
}
}
