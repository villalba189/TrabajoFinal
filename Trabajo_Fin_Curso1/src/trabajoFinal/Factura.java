package trabajoFinal;

import java.util.*;

public class Factura {
	private int id_compra;
	private Cliente cliente;
	private Date fecha;
	private Boolean pagado;
	private float total;
	private	HashSet<Linea> lineas;
}
