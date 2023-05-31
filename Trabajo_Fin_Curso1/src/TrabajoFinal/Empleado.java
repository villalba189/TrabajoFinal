package TrabajoFinal;

import java.util.*;

public class Empleado extends Usuario  {
	private Boolean is_admin;
	private int num_empleado;
	
	
	
	public Empleado() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//constructor copy
	public Empleado(Empleado empleado) {
	    this.id_Usario = empleado.id_Usario;
	    this.nombre = empleado.nombre;
	    this.apellido = empleado.apellido;
	    this.telefono = empleado.telefono;
	    this.is_admin = empleado.is_admin;
	    this.num_empleado = empleado.num_empleado;
	}

	public Empleado(int id_Usario, String nombre, String apellido, int telefono, Boolean is_admin, int num_empleado) {
		super(id_Usario, nombre, apellido, telefono);
		this.is_admin = is_admin;
		this.num_empleado = num_empleado;
		// TODO Auto-generated constructor stub
	}

	public Empleado(Usuario usuario) {
		super(usuario);
		// TODO Auto-generated constructor stub
	}

	public Boolean getIs_admin() {
		return is_admin;
	}

	public void setIs_admin(Boolean is_admin) {
		this.is_admin = is_admin;
	}

	public int getNum_empleado() {
		return num_empleado;
	}

	public void setNum_empleado(int num_empleado) {
		this.num_empleado = num_empleado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(is_admin, num_empleado);
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
		Empleado other = (Empleado) obj;
		return Objects.equals(is_admin, other.is_admin) && num_empleado == other.num_empleado;
	}

	@Override
	public String toString() {
		return "Empleado [is_admin=" + is_admin + ", num_empleado=" + num_empleado + ", toString()=" + super.toString()
				+ "]";
	}

	
	
	
}
