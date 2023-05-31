package TrabajoFinal;

import java.sql.*;
import java.util.Scanner;

public class MainTrabajoFinal {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {


		String url = "jdbc:mysql://localhost:3307/trabajofinal_tpv";
		String usuario = "Villalba";
		String clave = "Villalba";
		int cont=1;
		int contP=1;
		int eleccion;
		try (Connection conexion = DriverManager.getConnection(url, usuario, clave)) {
			 String buscarCategorias = "SELECT * FROM categoria";
		      PreparedStatement stmtbuscarCategorias = conexion.prepareStatement(buscarCategorias);
		      ResultSet rsbuscarCategorias = stmtbuscarCategorias.executeQuery();
		      
		      System.out.println("Elige una categoria");
		     do {
		    	 while (rsbuscarCategorias.next()) {
			    	 
			    	 System.out.println(cont + " " + rsbuscarCategorias.getString("Nombre"));
			    	 cont++;
				};
		    	 
			     eleccion = sc.nextInt();
			     String mostrarProducto = "select * from producto where ID_Producto in (select ID_Producto from contiene_pro_cat c where ID_Categoria = ?);";
			     PreparedStatement stmtMostrarProducto = conexion.prepareStatement(mostrarProducto);
			     stmtMostrarProducto.setInt(1, eleccion);
			      ResultSet rsMostrarProducto = stmtMostrarProducto.executeQuery();
			      while (rsMostrarProducto.next()) {
					      System.out.println(contP + " " + rsMostrarProducto.getString("Nombre"));
				    	 contP++;
					};
		     }while(eleccion!=0);
		} catch (SQLException e) {

		}

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
		        	menuPrincipal();
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

	
	
	public class ClienteBBDD extends ConexionBBDD{
	    ConexionBBDD conexionBBDD = new ConexionBBDD ();
	    Connection miConexion;
	    
	    PreparedStatement ps=null;
	    ResultSet rs=null;
	          
	    /**
	     * Constructor sin parámetros
	     */
	    public ClienteBBDD() {
	    }
	    
	    /**
	     * Método que recibe los datos del nuevo cliente y lo ingresa a la base de datos
	     * @see controlador.ControladorConsultaEmpleados#insertarCliente() 
	     * @param nuevoCliente Cliente para ingresar a la base de datos
	     * @return true si lo ingresó correctamente a la base de datos
	     */
	    
	    public boolean insertarClienteEnBBDD (Cliente nuevoCliente){
	        
	        try{
	            miConexion=conexionBBDD.conectar();
	            ps=miConexion.prepareStatement("insert into Cliente values (?,?,?,?,?)");
	            ps.setInt(1, nuevoCliente.getID_Usario());
	            ps.setString(2,nuevoCliente.getNombre());
	            ps.setString(3,nuevoCliente.getApellido());
	            ps.setInt(4, nuevoCliente.getTelefono());
	            ps.setString(5, nuevoCliente.getCorreo());

	            int resultado = ps.executeUpdate();
	            if(resultado>0){ //Si insertó correctamente en la BBDD
	                return true;
	            }
	            else{
	                return false;
	            }
	        }catch(Exception e){
	            e.printStackTrace();
	            return false;
	        }finally{ //El finally se ejecuta siempre. Asegura que cierre la conexión a BBDD
	            try{
	                miConexion.close();
	            }catch(Exception e){
	                e.printStackTrace();
	            }
	        }
	    }    
	   
	    /**
	     * Método que recibe los datos del cliente a editar y lo actualiza en la 
	     * base de datos
	     * @param clienteEditado Cliente para actualizar a la base de datos
	     * @return true si lo actualizó correctamente en la base de datos
	     * @see controlador.ControladorConsultaEmpleados#editarCliente() 
	     */
	    public boolean editarClienteEnBBDD (Cliente clienteEditado){
	        
	        try{
	            miConexion=conexionBBDD.conectar();
	            ps=miConexion.prepareStatement("update Cliente set Nombre=?,"
	                    + "Apellido=?,Telefono=?, Correo=? where ID_Cliente=?");

	            
	            ps.setString(1,clienteEditado.getNombre());
	            ps.setString(2,clienteEditado.getApellido());
	            ps.setInt(3, clienteEditado.getTelefono());
	            ps.setString(4, clienteEditado.getCorreo());
	            ps.setInt(5, clienteEditado.getID_Usario());

	            int resultado = ps.executeUpdate();
	            if(resultado>0){ //Si modificó correctamente en la BBDD
	                return true;
	            }
	            else{
	                return false;
	            }
	        }catch(Exception e){
	            e.printStackTrace();
	            return false;
	        }finally{ //El finally se ejecuta siempre. Asegura que cierre la conexión a BBDD
	            try{
	                miConexion.close();
	            }catch(Exception e){
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    
	    /**
	     * Método que recibe los datos del cliente a eliminar y lo elimina de la 
	     * base de datos
	     * @param clienteEliminar Cliente para eliminar de la base de datos
	     * @return true si lo eliminó correctamente de la base de datos
	     * @see controlador.ControladorConsultaEmpleados#eliminarCliente() 
	     */
	    public boolean eliminarClienteDeBBDD (Cliente clienteEliminar){
	        
	        try{
	            miConexion=conexionBBDD.conectar();
	            ps=miConexion.prepareStatement("delete from Cliente where "
	                    + "(ID_Cliente=? and Nombre=? and Apellido=? and Telefono=? and Correo=?)");

	            
	            ps.setInt(1, clienteEliminar.getID_Usario());
	            ps.setString(2,clienteEliminar.getNombre());
	            ps.setString(3,clienteEliminar.getApellido());
	            ps.setInt(4, clienteEliminar.getTelefono());
	            ps.setString(5, clienteEliminar.getCorreo());


	            int resultado = ps.executeUpdate();
	            if(resultado>0){ //Si eliminó correctamente en la BBDD
	                return true;
	            }
	            else{
	                return false;
	            }
	        }catch(Exception e){
	            e.printStackTrace();
	            return false;
	        }finally{ //El finally se ejecuta siempre. Asegura que cierre la conexión a BBDD
	            try{
	                miConexion.close();
	            }catch(Exception e){
	                e.printStackTrace();
	            }
	        }
	    }
	}
}


