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
                	crearFactura();
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
                    menuProductos();
                    break;
                case 3:
                    menuClientes();
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
                    "2. Eliminar categoría.\n" +
                    "3. Editar categoría.\n" +
                    "4. Añadir producto a la categoría.\n" +
                    "5. Eliminar producto a la categoría.\n" +
                    "0. Volver.\n");
            eleccion = sc.nextInt();

            switch (eleccion) {
                case 0:
                    break;
                case 1:
                	crearCategoria();
                    break;
                case 2:
                    eliminarCategoria();
                    break;
                case 3:
                	editarCategoria();
                	break;
                case 4:
                	agregarProductoCategoria();
                	break;
                case 5:
                	eliminarProductoCategoria();
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

    private static void mostrarProductosCategoria(Categoria categoria) {
        System.out.println("Productos de la categoría '" + categoria.getNombre() + "':");
        ArrayList<Producto> productos = GestorBBDD.productosCategoriaEnBBDD(categoria);
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    private static void agregarProductoCategoria() {
        System.out.println("Categorías disponibles:");
       mostrarCategorias();
        
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

    private static void eliminarCategoria() {
        System.out.println("Categorías disponibles:");
        mostrarCategorias();

        System.out.println("Ingrese el número de la categoría a eliminar:");
        int indiceCategoria = sc.nextInt();

        if (indiceCategoria >= 1 && indiceCategoria <= GestorBBDD.getCategorias().size()) {
            Categoria categoriaSeleccionada = GestorBBDD.getCategorias().get(indiceCategoria - 1);
            boolean exitoEliminar = GestorBBDD.eliminarCategoriaEnBBDD(categoriaSeleccionada);
            if (exitoEliminar) {
                System.out.println("Categoría eliminada con éxito.");
            } else {
                System.out.println("Error al eliminar la categoría.");
            }
        } else {
            System.out.println("¡Índice de categoría inválido!");
        }
    }

    private static void editarCategoria() {
        System.out.println("Categorías disponibles:");
        mostrarCategorias();

        System.out.println("Ingrese el número de la categoría a editar:");
        int indiceCategoria = sc.nextInt();

        if (indiceCategoria >= 1 && indiceCategoria <= GestorBBDD.getCategorias().size()) {
            Categoria categoriaSeleccionada = GestorBBDD.getCategorias().get(indiceCategoria - 1);
            sc.nextLine(); // Limpiar el búfer del escáner
            System.out.println("Ingrese el nuevo nombre de la categoría:");
            String nuevoNombre = sc.nextLine();
            categoriaSeleccionada.setNombre(nuevoNombre);
            boolean exitoEditar = GestorBBDD.editarCategoriaEnBBDD(categoriaSeleccionada);
            if (exitoEditar) {
                System.out.println("Categoría editada con éxito.");
            } else {
                System.out.println("Error al editar la categoría.");
            }
        } else {
            System.out.println("¡Índice de categoría inválido!");
        }
    }

    private static void eliminarProductoCategoria() {
        System.out.println("Categorías disponibles:");
        mostrarCategorias();

        System.out.println("Ingrese el número de la categoría:");
        int indiceCategoria = sc.nextInt();

        if (indiceCategoria >= 1 && indiceCategoria <= GestorBBDD.getCategorias().size()) {
            Categoria categoriaSeleccionada = GestorBBDD.getCategorias().get(indiceCategoria - 1);
            mostrarProductosCategoria(categoriaSeleccionada);
            System.out.println("Ingrese el número del producto a eliminar:");
            int indiceProducto = sc.nextInt();

            if (indiceProducto >= 1 && indiceProducto <= GestorBBDD.productosCategoriaEnBBDD(categoriaSeleccionada).size()) {
                Producto productoSeleccionado = (Producto) GestorBBDD.productosCategoriaEnBBDD(categoriaSeleccionada).toArray()[indiceProducto - 1];
                boolean exitoEliminar = GestorBBDD.eliminarRelacionCatPro(categoriaSeleccionada, productoSeleccionado);
                if (exitoEliminar) {
                    System.out.println("Producto eliminado de la categoría con éxito.");
                } else {
                    System.out.println("Error al eliminar el producto de la categoría.");
                }
            } else {
                System.out.println("¡Índice de producto inválido!");
            }
        } else {
            System.out.println("¡Índice de categoría inválido!");
        }
    }
    
    
    
    private static void menuProductos() {
        int eleccion;
        do {
            System.out.println("Menú productos: \n" +
                    "1. Añadir producto.\n" +
                    "2. Eliminar producto.\n" +
                    "3. Editar producto.\n" +
                    "4. Añadir categoría al producto.\n" +
                    "5. Eliminar categoría del producto.\n" +
                    "0. Volver.\n");
            eleccion = sc.nextInt();

            switch (eleccion) {
                case 0:
                    break;
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    eliminarProducto();
                    break;
                case 3:
                    editarProducto();
                    break;
                case 4:
                    agregarCategoriaAlProducto();
                    break;
                case 5:
                    eliminarCategoriaDelProducto();
                    break;
                default:
                    break;
            }
        } while (eleccion != 0);
    }

    private static void agregarProducto() {
        sc.nextLine(); // Limpiar el búfer del escáner
        System.out.println("Ingrese el nombre del producto:");
        String nombreProducto = sc.nextLine();
        System.out.println("Ingrese el precio del producto:");
        double precioProducto = sc.nextDouble();

        Producto nuevoProducto = new Producto(nombreProducto, precioProducto);
        boolean exitoInsertar = GestorBBDD.insertarProductoEnBBDD(nuevoProducto);
        if (exitoInsertar) {
            System.out.println("Producto agregado con éxito.");
        } else {
            System.out.println("Error al insertar el producto en la base de datos.");
        }
    }

    private static void eliminarProducto() {
        System.out.println("Productos existentes:");
        mostrarProductos();

        System.out.println("Ingrese el número del producto a eliminar:");
        int indiceProducto = sc.nextInt();

        if (indiceProducto >= 1 && indiceProducto <= GestorBBDD.getProductos().size()) {
            Producto productoSeleccionado = GestorBBDD.getProductos().get(indiceProducto - 1);
            boolean exitoEliminar = GestorBBDD.eliminarProductoEnBBDD(productoSeleccionado);
            if (exitoEliminar) {
                System.out.println("Producto eliminado con éxito.");
            } else {
                System.out.println("Error al eliminar el producto.");
            }
        } else {
            System.out.println("¡Índice de producto inválido!");
        }
    }

    private static void editarProducto() {
        System.out.println("Productos existentes:");
        mostrarProductos();

        System.out.println("Ingrese el número del producto a editar:");
        int indiceProducto = sc.nextInt();

        if (indiceProducto >= 1 && indiceProducto <= GestorBBDD.getProductos().size()) {
            Producto productoSeleccionado = GestorBBDD.getProductos().get(indiceProducto - 1);
            sc.nextLine(); // Limpiar el búfer del escáner
            System.out.println("Ingrese el nuevo nombre del producto:");
            String nuevoNombre = sc.nextLine();
            System.out.println("Ingrese el nuevo precio del producto:");
            double nuevoPrecio = sc.nextDouble();

            productoSeleccionado.setNombre(nuevoNombre);
            productoSeleccionado.setPrecio(nuevoPrecio);
            boolean exitoEditar = GestorBBDD.editarProductoEnBBDD(productoSeleccionado);
            if (exitoEditar) {
                System.out.println("Producto editado con éxito.");
            } else {
                System.out.println("Error al editar el producto.");
            }
        } else {
            System.out.println("¡Índice de producto inválido!");
        }
    }

    private static void agregarCategoriaAlProducto() {
        System.out.println("Productos existentes:");
        mostrarProductos();

        System.out.println("Ingrese el número del producto:");
        int indiceProducto = sc.nextInt();

        if (indiceProducto >= 1 && indiceProducto <= GestorBBDD.getProductos().size()) {
            Producto productoSeleccionado = GestorBBDD.getProductos().get(indiceProducto - 1);
            mostrarCategorias();
            System.out.println("Ingrese el número de la categoría a añadir:");
            int indiceCategoria = sc.nextInt();

            if (indiceCategoria >= 1 && indiceCategoria <= GestorBBDD.getCategorias().size()) {
                Categoria categoriaSeleccionada = GestorBBDD.getCategorias().get(indiceCategoria - 1);
                boolean exitoAsociar = GestorBBDD.insertarRelacionCatPro(categoriaSeleccionada, productoSeleccionado);
                if (exitoAsociar) {
                    System.out.println("Categoría agregada al producto con éxito.");
                } else {
                    System.out.println("Error al agregar la categoría al producto.");
                }
            } else {
                System.out.println("¡Índice de categoría inválido!");
            }
        } else {
            System.out.println("¡Índice de producto inválido!");
        }
    }

    private static void eliminarCategoriaDelProducto() {
        System.out.println("Productos existentes:");
        mostrarProductos();

        System.out.println("Ingrese el número del producto:");
        int indiceProducto = sc.nextInt();

        if (indiceProducto >= 1 && indiceProducto <= GestorBBDD.getProductos().size()) {
            Producto productoSeleccionado = GestorBBDD.getProductos().get(indiceProducto - 1);
            mostrarCategoriasProducto(productoSeleccionado);
            System.out.println("Ingrese el número de la categoría a eliminar:");
            int indiceCategoria = sc.nextInt();

            if (indiceCategoria >= 1 && indiceCategoria <= GestorBBDD.categoriasProductoEnBBDD(productoSeleccionado).size()) {
                Categoria categoriaSeleccionada = (Categoria) GestorBBDD.categoriasProductoEnBBDD(productoSeleccionado).toArray()[indiceCategoria - 1];
                boolean exitoEliminar = GestorBBDD.eliminarRelacionCatPro(categoriaSeleccionada, productoSeleccionado);
                if (exitoEliminar) {
                    System.out.println("Categoría eliminada del producto con éxito.");
                } else {
                    System.out.println("Error al eliminar la categoría del producto.");
                }
            } else {
                System.out.println("¡Índice de categoría inválido!");
            }
        } else {
            System.out.println("¡Índice de producto inválido!");
        }
    }

    private static void mostrarProductos() {
        int cont = 1;
        System.out.println("Productos existentes:");
        for (Producto producto : GestorBBDD.getProductos()) {
            System.out.println(cont + ". " + producto.getNombre());
            cont++;
        }
    }

    private static void mostrarCategoriasProducto(Producto producto) {
        System.out.println("Categorías del producto '" + producto.getNombre() + "':");
        ArrayList<Categoria> categorias = GestorBBDD.categoriasProductoEnBBDD(producto);
        int cont = 1;
        for (Categoria categoria : categorias) {
            System.out.println(cont + ". " + categoria.getNombre());
            cont++;
        }
    }



    private static void menuClientes() {
        int eleccion;
        do {
            System.out.println("Menú clientes: " +
                    "\n1. Mostrar clientes.\n" +
                    "2. Añadir cliente.\n" +
                    "3. Eliminar cliente.\n" +
                    "0. Volver.\n");
            eleccion = sc.nextInt();

            switch (eleccion) {
                case 0:
                    break;
                case 1:
                    mostrarClientes();
                    break;
                case 2:
                    agregarCliente();
                    break;
                case 3:
                    eliminarCliente();
                    break;
                default:
                    break;
            }
        } while (eleccion != 0);
    }

    private static void mostrarClientes() {
        System.out.println("Listado de clientes:");
        ArrayList<Cliente> clientes = GestorBBDD.getClientes();
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    private static void agregarCliente() {
        sc.nextLine(); // Limpiar el búfer del escáner
        System.out.println("Ingrese el nombre del cliente:");
        String nombreCliente = sc.nextLine();
        System.out.println("Ingrese el apellido del cliente:");
        String apellidoCliente = sc.nextLine();
        System.out.println("Ingrese la dirección del cliente:");
        String direccionCliente = sc.nextLine();
        System.out.println("Ingrese el teléfono del cliente:");
        int telefonoCliente = sc.nextInt();

        Cliente nuevoCliente = new Cliente(nombreCliente, apellidoCliente,telefonoCliente , direccionCliente);
        boolean exitoInsertar = GestorBBDD.insertaClienteEnBBDD(nuevoCliente);
        if (exitoInsertar) {
            System.out.println("Cliente agregado con éxito.");
        } else {
            System.out.println("Error al insertar el cliente en la base de datos.");
        }
    }

    private static void eliminarCliente() {
        System.out.println("Clientes existentes:");
        mostrarClientes();
        
        System.out.println("Ingrese el número del cliente que desea eliminar:");
        int indiceCliente = sc.nextInt();
        
        if (indiceCliente >= 1 && indiceCliente <= GestorBBDD.getClientes().size()) {
            Cliente clienteSeleccionado = GestorBBDD.getClientes().get(indiceCliente - 1);
            
            boolean exitoEliminar = GestorBBDD.eliminarClienteEnBBDD(clienteSeleccionado);
            if (exitoEliminar) {
                System.out.println("Cliente eliminado con éxito.");
            } else {
                System.out.println("Error al eliminar el cliente de la base de datos.");
            }
        } else {
            System.out.println("¡Índice de cliente inválido!");
        }
    }


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

    private static void crearFactura() {
    	boolean continuar = true;
    	while (continuar) {
    	// Mostrar clientes disponibles
        mostrarClientes();

        // Solicitar al usuario el número del cliente
        System.out.println("Ingrese el número del cliente:");
        int numeroCliente = sc.nextInt();

        // Obtener el cliente correspondiente al número ingresado
        ArrayList<Cliente> clientes = GestorBBDD.getClientes();
        if (numeroCliente >= 1 && numeroCliente <= clientes.size()) {
            Cliente clienteElegido = clientes.get(numeroCliente - 1);

            // Crear una nueva factura con el cliente seleccionado
            Factura nuevaFactura = new Factura();
            nuevaFactura.setCliente(clienteElegido);


    
    while (continuar) {
        // Solicitar al usuario que elija un producto
        mostrarProductos();
        System.out.println("Ingrese el número del producto que desea agregar a la factura:");
        int indiceProducto = sc.nextInt();

        // Verificar si el índice de producto es válido
        if (indiceProducto >= 1 && indiceProducto <= GestorBBDD.getProductos().size()) {
            Producto productoElegido = GestorBBDD.getProductos().get(indiceProducto - 1);

            // Solicitar la cantidad del producto
            System.out.println("Ingrese la cantidad del producto:");
            int cantidad = sc.nextInt();

            // Crear una nueva línea con el producto y la cantidad
            Linea nuevaLinea = new Linea(cantidad, productoElegido);
            nuevaLinea.calcularSub_Total(productoElegido);

            // Insertar la línea en la base de datos utilizando la ID de la factura
            int idFacturaGenerado = GestorBBDD.insertarFacturaEnBBDD(nuevaFactura);
            if (idFacturaGenerado != -1) {
                boolean exitoInsertarLinea = GestorBBDD.insertarLineaEnBBDD(nuevaLinea, idFacturaGenerado);
                if (!exitoInsertarLinea) {
                    System.out.println("Error al insertar la línea en la base de datos.");
                }
            } else {
                System.out.println("Error al insertar la factura en la base de datos.");
            }

            // Agregar la línea a la factura
            nuevaFactura.agregarLinea(nuevaLinea);

            // Solicitar al usuario si desea continuar agregando productos
            System.out.println("¿Desea agregar otro producto? (Sí/No)");
            String respuesta = sc.next();
            if (respuesta.equalsIgnoreCase("No")) {
                continuar = false;
            }
        } else {
            System.out.println("¡Índice de producto inválido!");
        }
    }
    continuar = true;
    // Calcular el total de la factura
    nuevaFactura.calcularTotal();

    // Actualizar la factura en la base de datos
    boolean exitoActualizarFactura = GestorBBDD.actualizarFacturaEnBBDD(nuevaFactura);
    if (!exitoActualizarFactura) {
        System.out.println("Error al actualizar la factura en la base de datos.");
    }

    // Mostrar el total de la factura
    System.out.println("El total de la factura es: " + nuevaFactura.getTotal());
}else {
    System.out.println("¡Número de cliente inválido!");
}
        System.out.println("¿Desea hacer otra factura? (Sí/No)");
        String respuesta = sc.next();
        if (respuesta.equalsIgnoreCase("No")) {
            continuar = false;
        }
}
}    
}
