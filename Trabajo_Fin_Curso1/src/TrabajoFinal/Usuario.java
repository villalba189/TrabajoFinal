package TrabajoFinal;

import java.util.*;

public abstract class Usuario {
	protected int id_Usuario;
	protected String nombre;
	protected String apellido;
	protected int telefono;
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Usuario(int id_Usario, String nombre, String apellido, int telefono) {
		super();
		this.id_Usuario = id_Usario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
	}
	public Usuario( String nombre, String apellido, int telefono) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
	}
	
	public Usuario(Usuario usuario) {
	    this.id_Usuario = usuario.id_Usuario;
	    this.nombre = usuario.nombre;
	    this.apellido = usuario.apellido;
	    this.telefono = usuario.telefono;
	}
	
	public int getID_Usuario() {
		return id_Usuario;
	}
	public void setID_Usuario(int iD_Usario) {
		id_Usuario = iD_Usario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	@Override
	public int hashCode() {
		return Objects.hash(apellido, id_Usuario, nombre, telefono);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(apellido, other.apellido) && id_Usuario == other.id_Usuario
				&& Objects.equals(nombre, other.nombre) && telefono == other.telefono;
	}
	@Override
	public String toString() {
		return "Usuario [ID_Usario=" + id_Usuario + ", Nombre=" + nombre + ", Apellido=" + apellido + ", Telefono="
				+ telefono + "]";
	}
	

}
