package TrabajoFinal;


import java.sql.*;
import java.util.HashSet;

public class GestorBBDD {
	static Connection conexion;
	static String url = "jdbc:mysql://localhost:3306/trabajofinal_tpv";
	static String usuario = "root";
	static String clave = "";
	
	    static PreparedStatement ps=null;
	    static ResultSet rs=null;
    
    public static HashSet<Categoria> getCategorias() {
    	HashSet<Categoria> categorias= new HashSet<>();
        try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
        {

            ps=miConexion.prepareStatement("SELECT * FROM categoria");
            rs=ps.executeQuery();
		    		 while (rs.next()) {
		    			 int id_categoria = rs.getInt("ID_Categoria");
		    			 String nombre = rs.getString("Nombre");
		    	            Categoria categoria = new Categoria(id_categoria,nombre); 
		    	            categorias.add(categoria);
		    	        }
		    		  miConexion.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return categorias;
   }    
   public static boolean insertarCategoriaEnBBDD (Categoria nuevoCategoria){
        
       try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
       {

            ps=miConexion.prepareStatement("insert into categoria values (?)");
            
            ps.setString(1,nuevoCategoria.getNombre());

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
        }
    }    
   
    
   public static boolean editarCategoriaEnBBDD (Categoria nuevoCategoria){
        
       try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
       {
            ps=miConexion.prepareStatement("update categoria set Nombre=? where ID_Categoria=?");
            
            ps.setString(1,nuevoCategoria.getNombre());
            ps.setInt(2,nuevoCategoria.getId_categoria());

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
        }
    }  
   public static boolean eliminarCategoriaEnBBDD (Categoria nuevoCategoria){
       
       try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
       {
           ps=miConexion.prepareStatement("delete from categoria where Nombre=? and ID_Categoria=?");
           
           ps.setString(1,nuevoCategoria.getNombre());
           ps.setInt(2,nuevoCategoria.getId_categoria());

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
       }
   }
   
   public static HashSet<Producto> productosCategoriaEnBBDD() {
	   HashSet<Producto> productos= new HashSet<>();
       try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
       {

           ps=miConexion.prepareStatement("select * from producto where ID_Producto in (select ID_Producto from contiene_pro_cat  where ID_Categoria = ?);");
           rs=ps.executeQuery();
		    		 while (rs.next()) {
		    			 int id_producto = rs.getInt("ID_Producto");
		    			 String nombre = rs.getString("Nombre");
		    			 Double precio = rs.getDouble("Precio");
		    			 
		    	            Producto producto = new Producto(id_producto,nombre, precio); 
		    	            productos.add(producto);
		    	        }
		    		 
       }catch(Exception e){
         

           e.printStackTrace();
       }
       return productos;
  }    
 public static boolean insertarRelacionCatPro(Categoria categoria, Producto producto) {
     try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
     {
         ps=miConexion.prepareStatement("insert into contiene_pro_cat values (?,?)");
         
         ps.setInt(1,categoria.getId_categoria());
         ps.setInt(2,producto.getId_producto());

         int resultado = ps.executeUpdate();
         miConexion.close();
         if(resultado>0){ //Si insertó correctamente en la BBDD
             return true;
         }
         else{
             return false;
         }
         
     }catch(Exception e){
         e.printStackTrace();
         return false;
     }
 }
 
 public static boolean eliminarRelacionCatPro(Categoria categoria, Producto producto) {
     try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
     {
         ps=miConexion.prepareStatement("delete from contiene_pro_cat when Id_categoria=? and Id_producto=?");
         
         ps.setInt(1,categoria.getId_categoria());
         ps.setInt(2,producto.getId_producto());

         int resultado = ps.executeUpdate();
         miConexion.close();
         if(resultado>0){ //Si insertó correctamente en la BBDD
             return true;
         }
         else{
             return false;
         }
         
     }catch(Exception e){
         e.printStackTrace();
         return false;
     }
 }
 }

bobooobof

