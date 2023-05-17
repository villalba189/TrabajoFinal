package trabajoFinal;

import java.util.*;

public class Nota implements Administrar{
	private int id_nota;
	private String titulo;
	private Date fecha;
	private String descripcion;

	public Nota() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Nota(Nota nota) {
        this.id_nota = nota.id_nota;
        this.titulo = nota.titulo;
        this.fecha = new Date(nota.fecha.getTime());
        this.descripcion = nota.descripcion;
    }

	public Nota(int id_nota, String titulo, Date fecha, String descripcion) {
		super();
		this.id_nota = id_nota;
		this.titulo = titulo;
		this.fecha = fecha;
		this.descripcion = descripcion;
	}

	public int getId_nota() {
		return id_nota;
	}

	public void setId_nota(int id_nota) {
		this.id_nota = id_nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descripcion, fecha, titulo, id_nota);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nota other = (Nota) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(titulo, other.titulo) && id_nota == other.id_nota;
	}

	@Override
	public String toString() {
		return "Nota [id_nota=" + id_nota + ", Titulo=" + titulo + ", Fecha=" + fecha + ", Descripcion=" + descripcion
				+ "]";
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
