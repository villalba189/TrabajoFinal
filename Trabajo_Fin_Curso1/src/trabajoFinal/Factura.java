package trabajoFinal;

import java.util.*;

public class Factura {
	private int id_compra;
	private Cliente cliente;
	private Date fecha;
	private Boolean pagado;
	private float total;
	private	HashSet<Linea> lineas;
	public Factura() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Factura(int id_compra, Cliente cliente, Date fecha, Boolean pagado, float total) {
		super();
		this.id_compra = id_compra;
		this.cliente = cliente;
		this.fecha = fecha;
		this.pagado = pagado;
		this.total = total;
	}
	public int getId_compra() {
		return id_compra;
	}
	public void setId_compra(int id_compra) {
		this.id_compra = id_compra;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Boolean getPagado() {
		return pagado;
	}
	public void setPagado(Boolean pagado) {
		this.pagado = pagado;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cliente, fecha, id_compra, pagado, total);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(fecha, other.fecha)
				&& id_compra == other.id_compra && Objects.equals(pagado, other.pagado)
				&& Float.floatToIntBits(total) == Float.floatToIntBits(other.total);
	}
	@Override
	public String toString() {
		return "Factura [id_compra=" + id_compra + ", cliente=" + cliente + ", fecha=" + fecha + ", pagado=" + pagado
				+ ", total=" + total + "]";
	}
	
	
}
