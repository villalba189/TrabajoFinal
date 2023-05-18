package trabajoFinal;

import java.util.*;

public class Linea {
	private int id_Linea;
	private int cantidad;
	private double sub_total;
	private Producto producto;
	
	public Linea() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Linea(int id_Linea, int cantidad, double sub_total, Producto producto) {
		super();
		this.id_Linea = id_Linea;
		this.cantidad = cantidad;
		this.sub_total = sub_total;
		this.producto = producto;
	}

	public int getId_Linea() {
		return id_Linea;
	}

	public void setId_Linea(int id_Linea) {
		this.id_Linea = id_Linea;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getSub_total() {
		return sub_total;
	}

	public void setSub_total(double sub_total) {
		this.sub_total = sub_total;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cantidad, id_Linea, sub_total);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Linea other = (Linea) obj;
		return cantidad == other.cantidad && id_Linea == other.id_Linea
				&& Double.doubleToLongBits(sub_total) == Double.doubleToLongBits(other.sub_total);
	}

	@Override
	public String toString() {
		return "Linea [id_Linea=" + id_Linea + ", cantidad=" + cantidad + ", sub_total=" + sub_total + "]";
	}
	
	public void calcularSub_Total(Producto producto) {
		double subTotal = 0.0;
		double IVA = 0.0;
		IVA=producto.getPrecio()*producto.getIVA();
		subTotal=(producto.getPrecio()+IVA)*cantidad;
		sub_total=subTotal;
	}
	
}
