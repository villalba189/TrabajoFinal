package trabajoFinal;

import java.sql.*;

public class MainTrabajoFinal {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3307/trabajofinal_tpv";
		String usuario = "Villalba";
		String clave = "Villalba";

		try (Connection conexion = DriverManager.getConnection(url, usuario, clave)) {

		} catch (SQLException e) {

		}

	}

}
