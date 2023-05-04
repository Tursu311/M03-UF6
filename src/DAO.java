/*crear almenys dos casuístiques diferents a la Vista, on es treballi amb el patró CRUD sobre dos models diferents. Per exemple donar l’opció a l’usuari a canviar dades de la taula candidats, o afegir nous registres a la taula Candidatures, etc….
 */

//fem un programa per a calcular el percentatge de homes i dones a la taula persones, i mirar els persentatges per provincies, i ho mostrem en una taula a consola, donem per sentat que tenim les dades a la taula
import java.sql.*;
import java.util.Scanner;

public class ProgramaPrincipal {
    public static Connection con;

    public static void main(String[] args) throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://192.168.1.190/mydb?serverTimezone=UTC", "perepi", "pastanaga");
        Scanner sc = new Scanner(System.in);
        int opcio = 0;
        do {
            System.out.println("1. Mostrar percentatge de dones i homes per comunitat autonoma");
            System.out.println("2. Mostrar percentatge de dones i homes per provincia");
            System.out.println("4. Mostrar percentatge de dones i homes per candidatura");
            System.out.println("5. Sortir");
            opcio = sc.nextInt();
            switch (opcio) {
                case 1:
                    donesHomesComunitatAutonoma();
                    break;
                case 2:
                    donesHomesProvincia();
                    break;
                case 3:
                    donesHomesCandidatura();
                    break;
                case 4:
                    System.out.println("Adéu");
                    break;
            }
        } while (opcio != 4);
        con.close();
    }

    public static void donesHomesComunitatAutonoma() throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM comunitats_autonomes");
        while (rs.next()) {
            String nomComunitat = rs.getString("nom");
            int idComunitat = rs.getInt("id");
            Statement st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery("SELECT COUNT(*) FROM persones WHERE id_comunitat_autonoma = " + idComunitat);
            rs2.next();
            int totalPersones = rs2.getInt(1);
            Statement st3 = con.createStatement();
            ResultSet rs3 = st3.executeQuery("SELECT COUNT(*) FROM persones WHERE id_comunitat_autonoma = " + idComunitat + " AND sexe = 'Dona'");
            rs3.next();
            int dones = rs3.getInt(1);
            Statement st4 = con.createStatement();
            ResultSet rs4 = st4.executeQuery("SELECT COUNT(*) FROM persones WHERE id_comunitat_autonoma = " + idComunitat + " AND sexe = 'Home'");
            rs4.next();
            int homes = rs4.getInt(1);
            System.out.println("Comunitat Autonoma: " + nomComunitat);
            System.out.println("Dones: " + dones + " Homes: " + homes);
            System.out.println("Percentatge dones: " + (dones * 100 / totalPersones) + "%");
            System.out.println("Percentatge homes: " + (homes * 100 / totalPersones) + "%");
            System.out.println();
        }
    }