/*crear almenys dos casuístiques diferents a la Vista, on es treballi amb el patró CRUD sobre dos models diferents. Per exemple donar l’opció a l’usuari a canviar dades de la taula candidats, o afegir nous registres a la taula Candidatures, etc….
 */

//fem un programa per a calcular el percentatge de homes i dones a la taula persones, i mirar els persentatges per provincies, i ho mostrem en una taula a consola, donem per sentat que tenim les dades a la taula
import java.sql.*;
import java.util.Scanner;

public class DAO {
    public static Connection con;

    public static void main(String[] args) throws SQLException {
        try {
            con = DriverManager.getConnection("jdbc:mysql://192.168.1.190/mydb?serverTimezone=UTC", "perepi", "pastanaga");
        } catch (SQLException e) {
            System.out.println("Error al crear la connexió");
        }
        Scanner sc = new Scanner(System.in);
        int opcio = 0;
        do {
            System.out.println("1. Mostrar percentatge de dones i homes per candidatura");
            System.out.println("2. Mostrar percentatge de dones i homes per provincia");
            System.out.println("4. Mostrar percentatge de dones i homes per comunitat autonoma");
            System.out.println("5. Sortir");
            opcio = sc.nextInt();
            switch (opcio) {
                case 1:
                    donesHomesCandidatura();
                    break;
                case 2:
                    donesHomesProvincia();
                    break;
                case 3:
                    donesHomesComunitatAutonoma();
                    break;
                case 4:
                    System.out.println("Adéu");
                    break;
            }
        } while (opcio != 4);
        con.close();
    }

    //Tenim una taula candidats on hi ha la id de la persona( que aquest id esta a la taula persones, on esta el genere), i la id de la candidatura.
    //fem un metode que ens mostri el percentatge de dones i homes per candidatura
    public static void donesHomesCandidatura() throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM candidatures");
        while (rs.next()) {
            String nomCandidatura = rs.getString("nom");
            int idCandidatura = rs.getInt("id");
            Statement st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery("SELECT COUNT(*) FROM candidats WHERE id_candidatura = " + idCandidatura);
            rs2.next();
            int totalPersones = rs2.getInt(1);
            Statement st3 = con.createStatement();
            ResultSet rs3 = st3.executeQuery("SELECT COUNT(*) FROM candidats INNER JOIN persones ON candidats.id_persona = persones.id WHERE persones.sexe = 'F' AND candidats.id_candidatura = " + idCandidatura);
            rs3.next();
            int dones = rs3.getInt(1);
            Statement st4 = con.createStatement();
            ResultSet rs4 = st4.executeQuery("SELECT COUNT(*) FROM candidats INNER JOIN persones ON candidats.id_persona = persones.id WHERE persones.sexe = 'M' AND candidats.id_candidatura = " + idCandidatura);
            rs4.next();
            int homes = rs4.getInt(1);
            System.out.println("Candidatura: " + nomCandidatura);
            System.out.println("Dones: " + dones + " Homes: " + homes);
            System.out.println("Percentatge dones: " + (dones * 100 / totalPersones) + "%");
            System.out.println("Percentatge homes: " + (homes * 100 / totalPersones) + "%");
            System.out.println();
        }
    }
}