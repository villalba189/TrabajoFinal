package TrabajoFinal;


import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class GestorBBDD {
	static Connection conexion;
	static String url = "jdbc:mysql://localhost:3306/trabajofinal_tpv";
	static String usuario = "root";
	static String clave = "";

	static PreparedStatement ps=null;
	static ResultSet rs=null;


	//------------------------------CATEGORIA---------------------------------------------------


	public static ArrayList<Categoria> getCategorias() {
		ArrayList<Categoria> categorias= new ArrayList<Categoria>();
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

			ps=miConexion.prepareStatement("insert into categoria (Nombre) values (?)",Statement.RETURN_GENERATED_KEYS);

			ps.setString(1,nuevoCategoria.getNombre());

			int resultado = ps.executeUpdate();
			if(resultado>0){ //Si insertó correctamente en la BBDD
				ResultSet generatedKeys = ps.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int idCategoriaGenerado = generatedKeys.getInt(1);
	                nuevoCategoria.setId_categoria(idCategoriaGenerado);
	            }
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

	public static ArrayList<Producto> productosCategoriaEnBBDD(Categoria nuevoCategoria) {
		ArrayList<Producto> productos= new ArrayList<Producto>();
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

	public static Optional<Categoria> buscarPorNombreCategoria(String nombreCategoria) {
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

	public static Optional<Categoria> buscarPorIDCategoria(int idCategoria) {
	    Optional<Categoria> opcionalCategoria = Optional.empty();
	    try (Connection miConexion = DriverManager.getConnection(url, usuario, clave)) {
	        ps = miConexion.prepareStatement("SELECT * FROM categoria WHERE ID_Categoria = ?");
	        ps.setInt(1, idCategoria);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            int id_categoria = rs.getInt("ID_Categoria");
	            String nombre = rs.getString("Nombre");
	            Categoria categoria = new Categoria(id_categoria, nombre);
	            opcionalCategoria = Optional.of(categoria);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return opcionalCategoria;
	}

	//--------------------------RELACION---PRODUCTO---CATEGORIA----------------------------------------

	public static boolean insertarRelacionCatPro(Categoria categoria, Producto producto) {
	    try (Connection miConexion = DriverManager.getConnection(url, usuario, clave)) {
	        ps = miConexion.prepareStatement("INSERT INTO contiene_pro_cat (ID_Categoria, ID_Producto) VALUES (?,?)");
	        ps.setInt(1, categoria.getId_categoria());
	        ps.setInt(2, producto.getId_producto());

	        int resultado = ps.executeUpdate();
	        if (resultado > 0) {
	        	
	            return true; // Si se insertó correctamente en la BBDD
	            
	        } else {
	            return false; // Si hubo un error al insertar
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false; // Si hubo una excepción
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


	//--------------------------------PRODUCTO-------------------------------------------------


	public static ArrayList<Producto> getProductos() {
		ArrayList<Producto> productos= new ArrayList<Producto>();
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

			ps=miConexion.prepareStatement("insert into producto (Nombre,Precio) values (?,?)",Statement.RETURN_GENERATED_KEYS);

			ps.setString(1,nuevoProducto.getNombre());
			ps.setDouble(2,nuevoProducto.getPrecio());

			int resultado = ps.executeUpdate();
			
			if(resultado>0){ //Si insertó correctamente en la BBDD
				ResultSet generatedKeys = ps.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int idProductoGenerado = generatedKeys.getInt(1);
	                nuevoProducto.setId_producto(idProductoGenerado);
	            }
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

	public static ArrayList<Categoria> categoriasProductoEnBBDD(Producto producto) {
	    ArrayList<Categoria> categorias = new ArrayList<>();
	    try (Connection miConexion = DriverManager.getConnection(url, usuario, clave)) {
	        ps = miConexion.prepareStatement("SELECT ID_Categoria, Nombre FROM categoria WHERE ID_Categoria IN (SELECT ID_Categoria FROM contiene_pro_cat WHERE ID_Producto = ?)");
	        ps.setInt(1, producto.getId_producto());
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            int id_categoria = rs.getInt("ID_Categoria");
	            String nombre = rs.getString("Nombre");

	            Categoria categoria = new Categoria(id_categoria, nombre);
	            categorias.add(categoria);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return categorias;
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
	
	public static Optional<Producto>  buscarPorIdProducto (int idProducto) {
		Optional<Producto> opcionalProducto = Optional.empty();
		try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
		{

			ps=miConexion.prepareStatement("SELECT * FROM producto where ID_Producto=?");
			ps.setInt(1, idProducto);
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

	
	//----------------------------CLIENTES--------------------------------
	

	public static ArrayList<Cliente> getClientes() {
		ArrayList<Cliente> clientes= new ArrayList<Cliente>();
		try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
		{

			ps=miConexion.prepareStatement("SELECT * FROM cliente");
			rs=ps.executeQuery();
			while (rs.next()) {
				int id_cliente = rs.getInt("ID_Cliente");
				String nombre = rs.getString("Nombre");
				String apellido = rs.getString("Apellido");
				int telefono = rs.getInt("Telefono");
				String correo= rs.getString("Correo");
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

			ps=miConexion.prepareStatement("insert into cliente (nombre,apellido,telefono,correo) values (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

			ps.setString(1,nuevoCliente.getNombre());
			ps.setString(2,nuevoCliente.getApellido());
			ps.setInt(3, nuevoCliente.getTelefono());
			ps.setString(4,nuevoCliente.getCorreo());
			

			int resultado = ps.executeUpdate();
			if(resultado>0){ //Si insertó correctamente en la BBDD
				ResultSet generatedKeys = ps.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int idClienteGenerado = generatedKeys.getInt(1);
	                nuevoCliente.setID_Usuario(idClienteGenerado);
	            }
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
			ps=miConexion.prepareStatement("delete from cliente where Nombre=? and ID_Cliente=?");

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

	public static ArrayList<Nota> notasClientesEnBBDD(Cliente nuevoCliente) {
		ArrayList<Nota> notas= new ArrayList<Nota>();
		try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
		{

			ps=miConexion.prepareStatement("select * from nota where ID_Cliente = ?);");
			ps.setInt(1,nuevoCliente.getID_Usuario());
			rs=ps.executeQuery();
			while (rs.next()) {
				int id_nota = rs.getInt("ID_Nota");
				String titulo = rs.getString("Titulo");
				Date fecha = rs.getDate("Fecha");
				String descripcion = rs.getString("Descripcion");
				
				Nota nota = new Nota(id_nota,titulo, fecha,descripcion); 
				notas.add(nota);
			}

		}catch(Exception e){


			e.printStackTrace();
		}
		return notas;
		
	}    
	
	public static ArrayList<Factura> facturasClientesEnBBDD(Cliente nuevoCliente) {
		ArrayList<Factura> facturas= new ArrayList<Factura>();
		try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
		{

			ps=miConexion.prepareStatement("select * from factura where ID_Cliente = ?);");
			ps.setInt(1,nuevoCliente.getID_Usuario());
			rs=ps.executeQuery();
			while (rs.next()) {
				int id_factura = rs.getInt("ID_Factura");
				Date fecha = rs.getDate("Fecha");
				boolean pagado = rs.getBoolean("Pagado");
				Double total = rs.getDouble("Total");
				
				Factura factura = new Factura(id_factura,nuevoCliente,fecha, pagado,total);
				
				facturas.add(factura);
			}

		}catch(Exception e){

			e.printStackTrace();
		}
		return facturas;
	}    	
	
	public static Optional<Cliente>  buscarPorNombreCliente (String nombreCliente) {
		Optional<Cliente> opcionalCliente = Optional.empty();
		try( Connection miConexion = DriverManager.getConnection(url,usuario,clave) )
		{

			ps=miConexion.prepareStatement("SELECT * FROM cliente where Nombre=?");
			ps.setString(1, nombreCliente);
			rs=ps.executeQuery();
			while (rs.next()) {
				int id_cliente = rs.getInt("ID_Cliente");
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
	
	public static Optional<Cliente> buscarPorIDCliente(int idCliente) {
	    Optional<Cliente> opcionalCliente = Optional.empty();
	    try (Connection miConexion = DriverManager.getConnection(url, usuario, clave)) {
	        ps = miConexion.prepareStatement("SELECT * FROM cliente WHERE ID_Cliente = ?");
	        ps.setInt(1, idCliente);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            int id_cliente = rs.getInt("ID_Cliente");
	            String nombre = rs.getString("Nombre");
	            String apellido = rs.getString("Apellido");
	            int telefono = rs.getInt("Telefono");
	            String correo = rs.getString("Corrio");
	            Cliente cliente = new Cliente(id_cliente, nombre, apellido, telefono, correo);
	            opcionalCliente = Optional.of(cliente);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return opcionalCliente;
	}

	
	
	//-----------------------------Nota--------------------------------
	
	
	
	public static boolean insertarNotaEnBBDD(Nota nuevaNota) {
        try (Connection miConexion = DriverManager.getConnection(url, usuario, clave)) {

            ps = miConexion.prepareStatement("INSERT INTO Nota (Titulo, Fecha, Descripcion) VALUES (?, ?, ?)",Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, nuevaNota.getTitulo());
            ps.setDate(2, new java.sql.Date(nuevaNota.getFecha().getTime()));
            ps.setString(3, nuevaNota.getDescripcion());

            int resultado = ps.executeUpdate();
            if (resultado > 0) {
            	ResultSet generatedKeys = ps.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int idNotaGenerado = generatedKeys.getInt(1);
	                nuevaNota.setId_nota(idNotaGenerado);
	            }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean actualizarNotaEnBBDD(Nota notaActualizada) {
        try (Connection miConexion = DriverManager.getConnection(url, usuario, clave)) {

            ps = miConexion.prepareStatement("UPDATE Nota SET Titulo=?, Fecha=?, Descripcion=? WHERE ID_Nota=?");

            ps.setString(1, notaActualizada.getTitulo());
            ps.setDate(2, new java.sql.Date(notaActualizada.getFecha().getTime()));
            ps.setString(3, notaActualizada.getDescripcion());
            ps.setInt(4, notaActualizada.getId_nota());

            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarNotaEnBBDD(Nota nota) {
        try (Connection miConexion = DriverManager.getConnection(url, usuario, clave)) {

            ps = miConexion.prepareStatement("DELETE FROM Nota WHERE ID_Nota=?");

            ps.setInt(1, nota.getId_nota());

            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Optional<Nota> buscarPorIdNota(int idNota) {
        Optional<Nota> opcionalNota = Optional.empty();
        try (Connection miConexion = DriverManager.getConnection(url, usuario, clave)) {

            ps = miConexion.prepareStatement("SELECT * FROM Nota WHERE ID_Nota=?");
            ps.setInt(1, idNota);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idNotaResult = rs.getInt("ID_Nota");
                String titulo = rs.getString("Titulo");
                Date fecha = rs.getDate("Fecha");
                String descripcion = rs.getString("Descripcion");
                Nota nota = new Nota(idNotaResult, titulo, fecha, descripcion);
                opcionalNota = Optional.of(nota);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return opcionalNota;
    }

    
    
	//-----------------------------Linea--------------------------------
	
	
	public static boolean insertarLineaEnBBDD(Linea nuevaLinea, int IdFactura) {
	    try (Connection miConexion = DriverManager.getConnection(url, usuario, clave)) {

	        ps = miConexion.prepareStatement("INSERT INTO Linea (ID_Factura, ID_Producto, Cantidad, Sub_Total) VALUES (?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);

	        ps.setInt(1, IdFactura);
	        ps.setInt(2, nuevaLinea.getProducto().getId_producto());
	        ps.setInt(3, nuevaLinea.getCantidad());
	        ps.setDouble(4, nuevaLinea.getSub_total());

	        int resultado = ps.executeUpdate();
	        if (resultado > 0) {
	        	ResultSet generatedKeys = ps.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int IdLineaGenerado = generatedKeys.getInt(1);
	                nuevaLinea.setId_Linea(IdLineaGenerado);
	            }
	            return true;
	        } else {
	            return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public static boolean editarLineaEnBBDD(Linea linea , int IdFactura) {
	    try (Connection miConexion = DriverManager.getConnection(url, usuario, clave)) {
	        ps = miConexion.prepareStatement("UPDATE Linea SET ID_Factura=?, ID_Producto=?, Cantidad=?, Sub_Total=? WHERE ID_Linea=?");

	        ps.setInt(1,IdFactura);
	        ps.setInt(2, linea.getProducto().getId_producto());
	        ps.setInt(3, linea.getCantidad());
	        ps.setDouble(4, linea.getSub_total());
	        ps.setInt(5, linea.getId_Linea());

	        int resultado = ps.executeUpdate();
	        if (resultado > 0) {
	            return true;
	        } else {
	            return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public static boolean eliminarLineaEnBBDD(Linea linea) {
	    try (Connection miConexion = DriverManager.getConnection(url, usuario, clave)) {
	        ps = miConexion.prepareStatement("DELETE FROM Linea WHERE ID_Linea=?");

	        ps.setInt(1, linea.getId_Linea());

	        int resultado = ps.executeUpdate();
	        if (resultado > 0) {
	            return true;
	        } else {
	            return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	
	//-----------------------------Factura-----------------------------------
	
	public static int insertarFacturaEnBBDD(Factura nuevaFactura) {
	    try (Connection miConexion = DriverManager.getConnection(url, usuario, clave)) {
	        ps = miConexion.prepareStatement("INSERT INTO Factura (Fecha, Pagado, ID_Cliente) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

	        ps.setDate(1, new java.sql.Date(nuevaFactura.getFecha().getTime()));
	        ps.setBoolean(2, nuevaFactura.getPagado());
	        ps.setInt(3, nuevaFactura.getCliente().getID_Usuario());

	        int resultado = ps.executeUpdate();

	        if (resultado > 0) {
	            ResultSet generatedKeys = ps.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int idFacturaGenerado = generatedKeys.getInt(1);
	                nuevaFactura.setId_compra(idFacturaGenerado);
	                return idFacturaGenerado;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return -1; // Retornar un valor inválido en caso de error
	}

	public static boolean actualizarFacturaEnBBDD(Factura factura) {
	    try (Connection miConexion = DriverManager.getConnection(url, usuario, clave)) {
	        ps = miConexion.prepareStatement("UPDATE Factura SET Fecha=?, Pagado=?, ID_Cliente=? WHERE ID_Factura=?");

	        ps.setDate(1, new java.sql.Date(factura.getFecha().getTime()));
	        ps.setBoolean(2, factura.getPagado());
	        ps.setInt(3, factura.getCliente().getID_Usuario());
	        ps.setInt(4, factura.getId_compra());

	        int resultado = ps.executeUpdate();
	        return resultado > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public static ArrayList<Linea> getLineasDeFactura(Factura miFactura) {
		ArrayList<Linea> lineas = new ArrayList<>();
	    try (Connection miConexion = DriverManager.getConnection(url, usuario, clave)) {

	        ps = miConexion.prepareStatement("SELECT * FROM Linea where ID_Factura=?");
	        ps.setInt(1, miFactura.getId_compra());
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            int id_linea = rs.getInt("ID_Linea");
	            int id_producto = rs.getInt("ID_Producto");
	            int cantidad = rs.getInt("Cantidad");
	            double sub_total = rs.getDouble("Sub_Total");
	            
	            Optional<Producto> optProducto = buscarPorIdProducto(id_producto);
	            
	            Linea linea = new Linea(id_linea, cantidad, sub_total, optProducto.get());
	            lineas.add(linea);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return lineas;
	    
	}

	
	
	

}
