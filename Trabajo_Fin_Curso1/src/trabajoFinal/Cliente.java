package trabajoFinal;

import java.util.*;

public class Cliente extends Usuario implements Administrar{
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
	@Override
	public void añadir() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void modificar() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
