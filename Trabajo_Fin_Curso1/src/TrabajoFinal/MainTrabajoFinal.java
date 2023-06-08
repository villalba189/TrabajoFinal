package TrabajoFinal;

import java.util.*;

public class MainTrabajoFinal {
    public static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        menuPrincipal();
    }

    private static void menuPrincipal() {
        int eleccion;
        do {
            System.out.println("Introduzca la opción que desea realizar: " +
                    "\n1. Crear Factura.\n" +
                    "2. Visualizar productos o categorías.\n" +
                    "3. Administrar.\n" +
                    "0. Salir.\n");
            eleccion = sc.nextInt();

            switch (eleccion) {
                case 0:
                    System.out.println("Bye Bye");
                    break;
                case 1:
                   // crearFactura();
                    break;
                case 2:
                    visualizar();
                    break;
                case 3:
                    administrar();
                    break;
                default:
                    break;
            }
        } while (eleccion != 0);
    }

    private static void administrar() {
        int eleccion;
        do {
            System.out.println("Introduzca la opción que desea realizar: " +
                    "\n1. Menú Categoría.\n" +
                    "2. Menú Productos.\n" +
                    "3. Menú Clientes.\n" +
                    "0. Salir.\n");
            eleccion = sc.nextInt();

            switch (eleccion) {
                case 0:
                    break;
                case 1:
                    menuCategorias();
                    break;
                case 2:
                    //menuProductos();
                    break;
                case 3:
                    //menuClientes();
                    break;
                default:
                    break;
            }
        } while (eleccion != 0);
    }

    private static void menuCategorias() {
        int eleccion;
        do {
            System.out.println("Menú categorías: \n" +
                    "1. Añadir categoría.\n" +
                    "2. Añadir producto a la categoría.\n" +
                    "0. Volver.\n");
            eleccion = sc.nextInt();

            switch (eleccion) {
                case 0:
                    break;
                case 1:
                	crearCategoria();
                    break;
                case 2:
                    agregarProductoCategoria();
                    break;
                
                default:
                    break;
            }
        } while (eleccion != 0);
    }

    private static void mostrarCategorias() {
    	int cont= 1;
        System.out.println("Categorías existentes: ");
        for (Categoria categoria : GestorBBDD.getCategorias()) {
            System.out.println(cont +". "+ categoria.getNombre());
            cont++;
        }
    }

    private static void crearCategoria() {
        sc.nextLine(); // Limpiar el búfer del escáner
        System.out.println("Ingrese el nombre de la categoría:");
        String nombreCategoria = sc.nextLine();

        Categoria nuevaCategoria = new Categoria(nombreCategoria);
        GestorBBDD.insertarCategoriaEnBBDD(nuevaCategoria);
        System.out.println("Categoría agregada con éxito.");
    }

    private static void mostrarProductosCategoria() {
        System.out.println("Categorías disponibles:");
        int cont = 1;
        for (Categoria categoria : GestorBBDD.getCategorias()) {
            System.out.println(cont + ". " + categoria.getNombre());
            cont++;
        }
        
        System.out.println("Ingrese el número de la categoría:");
        int indiceCategoria = sc.nextInt();
        
        if (indiceCategoria >= 1 && indiceCategoria <= GestorBBDD.getCategorias().size()) {
            Categoria categoriaSeleccionada = (Categoria) GestorBBDD.getCategorias().toArray()[indiceCategoria - 1];
            
            System.out.println("Productos de la categoría '" + categoriaSeleccionada.getNombre() + "':");
            for (Producto producto : GestorBBDD.productosCategoriaEnBBDD(categoriaSeleccionada)) {
                System.out.println(producto);
            }
        } else {
            System.out.println("¡Índice de categoría inválido!");
        }
    }
    
    private static void mostrarProductosCategoria(Categoria categoria) {
        System.out.println("Productos de la categoría '" + categoria.getNombre() + "':");
        HashSet<Producto> productos = GestorBBDD.productosCategoriaEnBBDD(categoria);
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    private static void agregarProductoCategoria() {
        System.out.println("Categorías disponibles:");
        int cont = 1;
        for (Categoria categoria : GestorBBDD.getCategorias()) {
            System.out.println(cont + ". " + categoria.getNombre());
            cont++;
        }
        
        System.out.println("Ingrese el número de la categoría:");
        int indiceCategoria = sc.nextInt();
        Producto nuevoProducto = new Producto();
        if (indiceCategoria >= 1 && indiceCategoria <= GestorBBDD.getCategorias().size()) {
            sc.nextLine(); // Limpiar el búfer del escáner
            
            Categoria categoriaSeleccionada = GestorBBDD.getCategorias().get(indiceCategoria-1);
            
            System.out.println("Esta ya creado el producto? (Y , N)");
            String aceptar =sc.next();
            switch (aceptar) {
			case "Y":
			    mostrarProductos();
			    System.out.println("Ingrese el número del producto existente:");
			    int indiceProducto = sc.nextInt();
			    if (indiceProducto >= 1 && indiceProducto <= GestorBBDD.getProductos().size()) {
			        Producto productoExistente = GestorBBDD.getProductos().get(indiceCategoria-1);
			         boolean exitoAsociar = GestorBBDD.insertarRelacionCatPro(categoriaSeleccionada, productoExistente);
			         if (exitoAsociar) {
		                    System.out.println("Producto agregado a la categoría '" + categoriaSeleccionada.getNombre() + "' con éxito.");
		                } else {
		                    System.out.println("Error al asociar el producto a la categoría.");
		                }			    
			    } else {
			        System.out.println("¡Índice de producto inválido!");
			    }
				break;
			case "N":
				sc.nextLine();
				System.out.println("Ingrese el nombre del producto:");
            String nombreProducto = sc.nextLine();
            System.out.println("Ingrese el precio del producto:");
            double precioProducto = sc.nextDouble();
            nuevoProducto.setNombre(nombreProducto);
            nuevoProducto.setPrecio(precioProducto);
            boolean exitoInsertar = GestorBBDD.insertarProductoEnBBDD(nuevoProducto);
            if (exitoInsertar) {
            	boolean exitoAsociar = GestorBBDD.insertarRelacionCatPro(categoriaSeleccionada,nuevoProducto );
                if (exitoAsociar) {
                    System.out.println("Producto agregado a la categoría '" + categoriaSeleccionada.getNombre() + "' con éxito.");
                } else {
                    System.out.println("Error al asociar el producto a la categoría.");
                }
            } else {
                System.out.println("Error al insertar el producto en la base de datos.");
            }
            
				break;
			default:
				System.err.println("Tienes que poner Y o N");
				break;
			}
            
        } else {
            System.out.println("¡Índice de categoría inválido!");
        }
    }


    
    
    
//    
//    private static void menuProductos() {
//        int eleccion;
//        do {
//            System.out.println("Menú productos: " +
//                    "\n1. Mostrar productos.\n" +
//                    "2. Añadir producto.\n" +
//                    "3. Eliminar producto.\n" +
//                    "0. Volver al menú principal.\n");
//            eleccion = sc.nextInt();
//
//            switch (eleccion) {
//                case 0:
//                    break;
//                case 1:
//                    mostrarProductos();
//                    break;
//                case 2:
//                    agregarProducto();
//                    break;
//                case 3:
//                    eliminarProducto();
//                    break;
//                default:
//                    break;
//            }
//        } while (eleccion != 0);
//    }
//
    private static void mostrarProductos() {
    	int cont=1;
        System.out.println("Productos existentes:");
        for (Producto producto : GestorBBDD.getProductos()) {
            System.out.println(cont + ". " +producto.getNombre());
            cont++;
        }
    }
//
//    private static void agregarProducto() {
//        sc.nextLine(); // Limpiar el búfer del escáner
//        System.out.println("Ingrese el nombre del producto:");
//        String nombreProducto = sc.nextLine();
//        System.out.println("Ingrese el precio del producto:");
//        double precioProducto = sc.nextDouble();
//
//        Producto nuevoProducto = new Producto(nombreProducto, precioProducto);
//        productos.add(nuevoProducto);
//
//        System.out.println("Producto agregado con éxito.");
//    }
//
//    private static void eliminarProducto() {
//        mostrarProductos();
//        System.out.println("Ingrese el índice del producto a eliminar:");
//        int indiceProducto = sc.nextInt();
//
//        if (indiceProducto >= 0 && indiceProducto < productos.size()) {
//            Producto productoEliminar = productos.get(indiceProducto);
//            productos.remove(indiceProducto);
//
//            // Eliminar el producto de las categorías
//            for (Categoria categoria : categorias) {
//                categoria.eliminarProducto(productoEliminar);
//            }
//
//            System.out.println("Producto eliminado con éxito.");
//        } else {
//            System.out.println("Índice de producto inválido.");
//        }
//    }
//
//    private static void menuClientes() {
//        int eleccion;
//        do {
//            System.out.println("Menú clientes: " +
//                    "\n1. Mostrar clientes.\n" +
//                    "2. Añadir cliente.\n" +
//                    "3. Eliminar cliente.\n" +
//                    "0. Volver al menú principal.\n");
//            eleccion = sc.nextInt();
//
//            switch (eleccion) {
//                case 0:
//                    break;
//                case 1:
//                    mostrarClientes();
//                    break;
//                case 2:
//                    agregarCliente();
//                    break;
//                case 3:
//                    eliminarCliente();
//                    break;
//                default:
//                    break;
//            }
//        } while (eleccion != 0);
//    }
//
//    private static void mostrarClientes() {
//        // Implementar la lógica para mostrar los clientes
//    }
//
//    private static void agregarCliente() {
//        // Implementar la lógica para agregar un cliente
//    }
//
//    private static void eliminarCliente() {
//        // Implementar la lógica para eliminar un cliente
//    }

    private static void visualizar() {
        int eleccion;
        do {
            System.out.println("Introduzca la opción que desea realizar: " +
                    "\n1. Mostrar productos.\n" +
                    "2. Mostrar categorías.\n" +
                    "0. Volver al menú principal.\n");
            eleccion = sc.nextInt();

            switch (eleccion) {
                case 0:
                    break;
                case 1:
                    mostrarProductos();
                    break;
                case 2:
                    mostrarCategorias();
                    break;
                default:
                    break;
            }
        } while (eleccion != 0);
    }
}
