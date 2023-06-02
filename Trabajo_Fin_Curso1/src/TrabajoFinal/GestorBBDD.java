package TrabajoFinal;


import java.sql.*;

import java.util.HashSet;
import java.util.Optional;

public class GestorBBDD {
	static Connection conexion;
	static String url = "jdbc:mysql://localhost:3306/trabajofinal_tpv";
	static String usuario = "root";
	static String clave = "";

	static PreparedStatement ps=null;
	static ResultSet rs=null;


	//-----------------------CATEGORIA-----------------------------------------------------------------


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

	public static HashSet<Producto> productosCategoriaEnBBDD(Categoria nuevoCategoria) {
		HashSet<Producto> productos= new HashSet<>();
		try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
		{

			ps=miConexion.prepareStatement("select * from producto where ID_Producto in (select ID_Producto from contiene_pro_cat  where ID_Categoria = ?);");
			ps.setInt(1,nuevoCategoria.getId_categoria());
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

	public static Optional<Categoria>  buscarPorNombreCategoria (String nombreCategoria) {
		Optional<Categoria> opcionalCategoria = Optional.empty();
		try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
		{

			ps=miConexion.prepareStatement("SELECT * FROM categoria where Nombre=?");
			ps.setString(1, nombreCategoria);
			rs=ps.executeQuery();
			while (rs.next()) {
				int id_categoria = rs.getInt("ID_Categoria");
				String nombre = rs.getString("Nombre");
				Categoria categoria = new Categoria(id_categoria,nombre); 
				opcionalCategoria=Optional.of(categoria);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return opcionalCategoria;
	}


	//--------------------RELACION---PRODUCTO---CATEGORIA----------------------------------------

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


	//------------------------PRODUCTO-------------------------------------------------


	public static HashSet<Producto> getProductos() {
		HashSet<Producto> productos= new HashSet<>();
		try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
		{

			ps=miConexion.prepareStatement("SELECT * FROM producto");
			rs=ps.executeQuery();
			while (rs.next()) {
				int id_producto = rs.getInt("ID_Producto");
				String nombre = rs.getString("Nombre");
				Double precio = rs.getDouble("Precio");
				Producto producto = new Producto(id_producto,nombre,precio); 
				productos.add(producto);
			}
			miConexion.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		return productos;
	}    
	public static boolean insertarProductoEnBBDD (Producto nuevoProducto){

		try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
		{

			ps=miConexion.prepareStatement("insert into producto (Nombre,Precio) values (?,?)");

			ps.setString(1,nuevoProducto.getNombre());
			ps.setDouble(2,nuevoProducto.getPrecio());

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


	public static boolean editarProductoEnBBDD (Producto nuevoProducto){

		try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
		{
			ps=miConexion.prepareStatement("update producto set Nombre=?, Precio=? where ID_Producto=?");

			ps.setString(1,nuevoProducto.getNombre());
			ps.setDouble(2,nuevoProducto.getPrecio());
			ps.setInt(3,nuevoProducto.getId_producto());

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
	public static boolean eliminarProductoEnBBDD (Producto nuevoProducto){

		try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
		{
			ps=miConexion.prepareStatement("delete from producto where Nombre=? and ID_Producto=?");

			ps.setString(1,nuevoProducto.getNombre());
			ps.setInt(2,nuevoProducto.getId_producto());

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

	public static HashSet<Producto> categoriasProductoEnBBDD(Producto nuevoProducto) {
		HashSet<Producto> productos= new HashSet<>();
		try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
		{

			ps=miConexion.prepareStatement("select * from categoria where ID_Categoria in (select ID_Categoria from contiene_pro_cat  where ID_Producto = ?);");
			ps.setInt(1,nuevoProducto.getId_producto());
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

	public static Optional<Producto>  buscarPorNombreProducto (String nombreProducto) {
		Optional<Producto> opcionalProducto = Optional.empty();
		try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
		{

			ps=miConexion.prepareStatement("SELECT * FROM producto where Nombre=?");
			ps.setString(1, nombreProducto);
			rs=ps.executeQuery();
			while (rs.next()) {
				int id_producto = rs.getInt("ID_Producto");
				String nombre = rs.getString("Nombre");
				Double precio = rs.getDouble("Precio");
				Producto producto = new Producto(id_producto,nombre,precio); 
				opcionalProducto=Optional.of(producto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return opcionalProducto;
	}

	
	//----------------------CLIENTES--------------------------------
	

	public static HashSet<Cliente> getClientes() {
		HashSet<Cliente> clientes= new HashSet<>();
		try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
		{

			ps=miConexion.prepareStatement("SELECT * FROM cliente");
			rs=ps.executeQuery();
			while (rs.next()) {
				int id_cliente = rs.getInt("ID_Usuario");
				String nombre = rs.getString("Nombre");
				String apellido = rs.getString("Apellido");
				int telefono = rs.getInt("Telefono");
				String correo= rs.getString("Corrio");
				Cliente cliente = new Cliente(id_cliente,nombre,apellido,telefono,correo);
				clientes.add(cliente);
			}
			miConexion.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		return clientes;
	}    
	public static boolean insertaClienteEnBBDD (Cliente nuevoCliente){

		try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
		{

			ps=miConexion.prepareStatement("insert into cliente (nombre,apellido,telefono,correo) values (?,?,?,?)");

			ps.setString(1,nuevoCliente.getNombre());
			ps.setString(2,nuevoCliente.getApellido());
			ps.setInt(3, nuevoCliente.getTelefono());
			ps.setString(4,nuevoCliente.getCorreo());
			

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


	public static boolean editarClienteEnBBDD (Cliente nuevoCliente){

		try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
		{
			ps=miConexion.prepareStatement("update cliente set Nombre=?, Apellido=?, Telefono=?, Correo=?  where ID_Usuario=?");

			ps.setString(1,nuevoCliente.getNombre());
			ps.setString(2,nuevoCliente.getApellido());
			ps.setInt(3, nuevoCliente.getTelefono());
			ps.setString(4,nuevoCliente.getCorreo());
			ps.setInt(5,nuevoCliente.getID_Usuario());

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
	public static boolean eliminarClienteEnBBDD (Cliente nuevoCliente){

		try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
		{
			ps=miConexion.prepareStatement("delete from cliente where Nombre=? and ID_Usuario=?");

			ps.setString(1,nuevoCliente.getNombre());
			ps.setInt(2,nuevoCliente.getID_Usuario());

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

	public static HashSet<Producto> categoriasProductoEnBBDD(Producto nuevoProducto) {
		HashSet<Producto> productos= new HashSet<>();
		try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
		{

			ps=miConexion.prepareStatement("select * from categoria where ID_Categoria in (select ID_Categoria from contiene_pro_cat  where ID_Producto = ?);");
			ps.setInt(1,nuevoProducto.getId_producto());
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

	public static Optional<Cliente>  buscarPorNombreCliente (String nombreCliente) {
		Optional<Cliente> opcionalCliente = Optional.empty();
		try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
		{

			ps=miConexion.prepareStatement("SELECT * FROM cliente where Nombre=?");
			ps.setString(1, nombreCliente);
			rs=ps.executeQuery();
			while (rs.next()) {
				int id_cliente = rs.getInt("ID_Usuario");
				String nombre = rs.getString("Nombre");
				String apellido = rs.getString("Apellido");
				int telefono = rs.getInt("Telefono");
				String correo= rs.getString("Corrio");
				Cliente cliente = new Cliente(id_cliente,nombre,apellido,telefono,correo);
				opcionalCliente=Optional.of(cliente);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return opcionalCliente;
	}
	
	
	

}
