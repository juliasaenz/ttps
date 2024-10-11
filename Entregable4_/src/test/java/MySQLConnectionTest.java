import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionTest {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/ttps";
		String username = "ttps";
		String password = "4qwvQMFK";

		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			if (connection != null) {
				System.out.println("Conexión exitosa a MySQL.");
			}
		} catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos.");
			e.printStackTrace();
		}
	}
}
