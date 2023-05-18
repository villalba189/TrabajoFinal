package trabajoFinal;

import java.util.*;

public class Producto {
	private int id_producto;
	private final Double IVA = 0.21;
	private String nombre;
	private Double precio;
	private HashSet<Categoria> categorias;
	
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Producto(int id_producto, String nombre, Double precio) {
		super();
		this.id_producto = id_producto;
		this.nombre = nombre;
		this.precio = precio;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getIVA() {
		return IVA;
	}

	@Override
	public int hashCode() {
		return Objects.hash(IVA, categorias, id_producto, nombre, precio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(IVA, other.IVA) && Objects.equals(categorias, other.categorias)
				&& id_producto == other.id_producto && Objects.equals(nombre, other.nombre)
				&& Objects.equals(precio, other.precio);
	}

	@Override
	public String toString() {
		return "Producto [id_producto=" + id_producto + ", IVA=" + IVA + ", nombre=" + nombre + ", precio=" + precio
				+ ", categorias=" + categorias + "]";
	}
	
	public void agregarCategoria(Categoria categoria) {
		categorias.add(categoria);

	}

	public void eliminarLinea(Categoria categoria) {
		for (Categoria miCategoria : categorias) {
			if (miCategoria.equals(categoria)) {
				categorias.remove(miCategoria);
			}
		}
	}
	
}
