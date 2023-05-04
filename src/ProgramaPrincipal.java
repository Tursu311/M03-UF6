import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProgramaPrincipal {
    public static Connection con;

    public static void main(String[] args) throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://192.168.122.59/mydb?serverTimezone=UTC", "perepi", "pastanaga");
        ImportarComunitatsAutonomes(); //todo aquesta si que es fa
        System.out.println("Comunitats Autonomes importades");
        ImportProvincies();//todo aquesta si que es fa
        System.out.println("Provincies importades");
        ImportMunicipis();//todo aquesta si que es fa
        System.out.println("Municipis importats");
        ImportarCandidatures();
        System.out.println("Candidatures importades");
        ImportPersonas();
        System.out.println("Personas importades");
        ImportarCandidats();
        System.out.println("Candidats importats");
        con.close();
    }

    // Importación de los datos de la tabla Comunitats Autonomes
    public static void ImportarComunitatsAutonomes() {

        Leer.comunitatsAutonomes(con);
    }
    // Importación de los datos de la tabla Provincies
    public static void ImportProvincies() {

        Leer.provincies(con);
    }
    // Importación de los datos de lal tabla Municipis
    public static void ImportMunicipis() {

        Leer.municipis(con);
    }
    public static void ImportarCandidatures(){

        Leer.candidatures(con);
    }
    // Importación de los datos de la tabla Personas
    public static void ImportPersonas() {

        Leer.personas(con);
    }
    public static void ImportarCandidats(){

        Leer.candidats(con);
    }
}