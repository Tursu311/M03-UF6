import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProgramaPrincipal {
    public static Connection con;

    public static void main(String[] args) throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://192.168.122.59/mydb?serverTimezone=UTC", "perepi", "pastanaga");
        ImportarComunitatsAutonomes();
        System.out.println("Comunitats Autonomes importades");
        ImportProvincies();
        System.out.println("Provincies importades");
        ImportMunicipis();
        System.out.println("Municipis importats");
        ImportEleccionesMunicipales();
        System.out.println("Elecciones municipales importades");
        ImportarCandidatures();
        System.out.println("Candidatures importades");
        ImportPersonas();
        System.out.println("Personas importades");
        ImportarCandidats();
        System.out.println("Candidats importats");
        ImportVotosMunicipales();
        System.out.println("Votos municipales importats");
        ImportVotosCA();
        System.out.println("Votos CA importats");
        ImportVotsProvincial();
        System.out.println("Vots provincial importats");
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
    // Importacion de los datos de la tabla Eleccions Municipals
    public static void ImportEleccionesMunicipales() {

        Leer.elecciones_municipales(con);
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
    // Importación de los datos de la tabla Vots_candidatures_Municipals
    public static void ImportVotosMunicipales() {

        Leer.votos_municipales(con);
    }

    // Importación de los datos de la tabla Vots_candidatures_ca
    public static void ImportVotosCA() {

        Leer.votos_ca(con);
    }
    //Importación de los datos de la tabla Vots_candidatures_Provincies
    public static void ImportVotsProvincial(){
        Leer.votsProvincies(con);
    }
}