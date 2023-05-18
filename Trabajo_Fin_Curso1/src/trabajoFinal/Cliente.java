package trabajoFinal;

import java.util.*;

public class Cliente extends Usuario {
	private String correo;
	private TreeSet<Nota> notas;
	private TreeSet<Factura> facturas;

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(int id_Usario, String nombre, String apellido, int telefono, String correo) {
		super(id_Usario, nombre, apellido, telefono);
		this.correo = correo;
		// TODO Auto-generated constructor stub
	}

	public Cliente(Usuario usuario) {
		super(usuario);
		// TODO Auto-generated constructor stub
	}

	public Cliente(Cliente cliente) {
		this.id_Usario = cliente.id_Usario;
		this.nombre = cliente.nombre;
		this.apellido = cliente.apellido;
		this.telefono = cliente.telefono;
		this.correo = cliente.correo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public TreeSet<Nota> getNotas() {
		return notas;
	}

	public TreeSet<Factura> getFacturas() {
		return facturas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(correo, facturas, notas);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(correo, other.correo) && Objects.equals(facturas, other.facturas)
				&& Objects.equals(notas, other.notas);
	}

	@Override
	public String toString() {
		return "Cliente [correo=" + correo + ", notas=" + notas + ", facturas=" + facturas + ", toString()="
				+ super.toString() + "]";
	}

	public void agregarNota(Nota nota) {
		notas.add(nota);

	}

	public void eliminarNota(Nota nota) {
		for (Nota miNota : notas) {
			if (miNota.equals(nota)) {
				notas.remove(miNota);
			}
		}
	}

	public void agregarFactura(Factura factura) {
		facturas.add(factura);
	}

	public void eliminarFactura(Factura factura) {
		for (Factura miFactura : facturas) {
			if (miFactura.equals(factura)) {
				notas.remove(miFactura);
			}
		}
	}
}
