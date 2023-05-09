import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class AddCandidat {
    public static void main(String[] args) {
        try (Connection con = getConnection()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter ID of candidatura: ");
            int idCandidatura = scanner.nextInt();
            System.out.print("Enter ID of persona: ");
            int idPersona = scanner.nextInt();
            System.out.print("Enter ID of provincia: ");
            int idProvincia = scanner.nextInt();
            CandidatDAO candidatDAO = new CandidatDAO(con);
            Candidat candidat = new Candidat(0, idCandidatura, idPersona, idProvincia);
            candidatDAO.create(candidat);
            System.out.println("Candidate added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://192.168.56.103/programacio?serverTimezone=UTC", "perepi", "pastanaga");
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
