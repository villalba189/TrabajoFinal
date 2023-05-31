package TrabajoFinal;


import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBBDD {
    
    Connection conexion;
    String url = "jdbc:mysql://localhost:3307/trabajofinal_tpv";
	String usuario = "Villalba";
	String clave = "Villalba";
    
    /**
     * Método para conxión a base de datos
     * @return conexion Conexión a BBDD
     */
    public Connection conectar(){
        try{
            conexion = DriverManager.getConnection(url,usuario,clave);
        }catch(Exception e){            
            System.out.println("No conecta");//Para control interno
        }
        System.out.println("Conectado a BBDD");//Para control interno
        return conexion;
    }
    
        
        
        
    }
