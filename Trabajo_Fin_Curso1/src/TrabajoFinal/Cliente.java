package TrabajoFinal;

import java.util.*;

public class Cliente extends Usuario {
	private String correo;
	private TreeSet<Nota> notas;
	private TreeSet<Factura> facturas;

	public Cliente() {
        super();
        notas = new TreeSet<>(); // Inicialización de la variable notas
        facturas = new TreeSet<>(); // Inicialización de la variable facturas
    }

    public Cliente(int id_Usuario, String nombre, String apellido, int telefono, String correo) {
        super(id_Usuario, nombre, apellido, telefono);
        this.correo = correo;
        notas = new TreeSet<>(); // Inicialización de la variable notas
        facturas = new TreeSet<>(); // Inicialización de la variable facturas
    }

    public Cliente(String nombre, String apellido, int telefono, String correo) {
        super(nombre, apellido, telefono);
        this.correo = correo;
        notas = new TreeSet<>(); // Inicialización de la variable notas
        facturas = new TreeSet<>(); // Inicialización de la variable facturas
    }

    public Cliente(Usuario usuario) {
        super(usuario);
        notas = new TreeSet<>(); // Inicialización de la variable notas
        facturas = new TreeSet<>(); // Inicialización de la variable facturas
    }

    public Cliente(Cliente cliente) {
        this.id_Usuario = cliente.id_Usuario;
        this.nombre = cliente.nombre;
        this.apellido = cliente.apellido;
        this.telefono = cliente.telefono;
        this.correo = cliente.correo;
        notas = new TreeSet<>(); // Inicialización de la variable notas
        facturas = new TreeSet<>(); // Inicialización de la variable facturas
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
		notas.remove(nota);

	}

	public void agregarFactura(Factura factura) {
		facturas.add(factura);
	}

	public void eliminarFactura(Factura factura) {
		facturas.remove(factura);

	}
}
