import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProgramaPrincipal {
    public static Connection con;

    public static void main(String[] args) throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://192.168.56.103/programacio?serverTimezone=UTC", "perepi", "pastanaga");
        ImportarComunitatsAutonomes();
        con.close();
    }

    // Importación de los datos de la tabla Comunitats Autonomes
    public static void ImportarComunitatsAutonomes() {

        CandidatDAO.countByCandidatura();
    }

}