package TrabajoFinal;

import java.sql.Timestamp;
import java.util.*;

public class Factura implements Comparable<Factura>{
	Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
	
	private int id_compra;
	private Cliente cliente;
	private Timestamp fecha = fechaActual;
	private Boolean pagado = false;
	private double total;
	private HashSet<Linea> lineas;

	public Factura() {
        super();
        this.lineas = new HashSet<>();
    }

	public Factura(int id_compra, Cliente cliente, Timestamp fecha, Boolean pagado, double total) {
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

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Boolean getPagado() {
		return pagado;
	}

	public void setPagado(Boolean pagado) {
		this.pagado = pagado;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, fecha, id_compra, lineas, pagado, total);
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
				&& id_compra == other.id_compra && Objects.equals(lineas, other.lineas)
				&& Objects.equals(pagado, other.pagado)
				&& Double.doubleToLongBits(total) == Double.doubleToLongBits(other.total);
	}

	public String toString() {
		return "Factura [id_compra=" + id_compra + ", cliente=" + cliente + ", fecha=" + fecha + ", pagado=" + pagado
				+ ", total=" + total + "]";
	}
	
	public int compareTo(Factura otraFactura) {
        // Comparar por fecha, de más recientes a más antiguas
        return otraFactura.getFecha().compareTo(this.getFecha());
    }

	public void agregarLinea(Linea linea) {
				lineas.add(linea);
				
	}

	public void eliminarLinea(Linea linea) {
				lineas.remove(linea);
	}
	
	public void calcularTotal() {
		double total = 0.0;
	    for (Linea linea : lineas) {
	        total += linea.getSub_total();
	    }
	    
	    this.total = total;
	}
	
	
	public void calcularPrecioConDescuento(double descuento) {
	    double totalConDescuento = 0.0;
	    totalConDescuento = this.total*(1+descuento);
	    this.total = totalConDescuento;
	}

}
