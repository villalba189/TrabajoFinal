package TrabajoFinal;

import java.util.*;

public class Categoria {
	private int id_categoria;
	private String nombre;
	private HashSet<Producto> productos;
	
	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Categoria(int id_categoria, String nombre) {
		super();
		this.id_categoria = id_categoria;
		this.nombre = nombre;
	}

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_categoria, nombre, productos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return id_categoria == other.id_categoria && Objects.equals(nombre, other.nombre)
				&& Objects.equals(productos, other.productos);
	}

	@Override
	public String toString() {
		return "Categoria [id_categoria=" + id_categoria + ", nombre=" + nombre + ", productos=" + productos + "]";
	}
	
	public void agregarProducto(Producto producto) {
		productos.add(producto);

	}

	public void eliminarLinea(Producto producto) {
		for (Producto miproducto : productos) {
			if (miproducto.equals(producto)) {
				productos.remove(miproducto);
			}
		}
	}
	
}
