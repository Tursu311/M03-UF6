import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class AMDCandidat {
    public static void main(String[] args) {
        try (Connection con = getConnection()) {
            Scanner scanner = new Scanner(System.in);
            if (args == "M") {
                //show current candidats
                CandidatDAO candidatDAO = new CandidatDAO(con);
                System.out.println(candidatDAO.findAll());
                return;
                System.out.println("What candidate do you want to modify?");
                int id = scanner.nextInt();
                Candidat candidat = candidatDAO.findById(id);
                System.out.println("What do you want to modify?");
                System.out.println("1. Candidatura");
                System.out.println("2. Persona");
                System.out.println("3. Provincia");
                System.out.print("Enter option: ");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        System.out.print("Enter new candidatura: ");
                        int idCandidatura = scanner.nextInt();
                        candidat.setIdCandidatura(idCandidatura);
                        break;
                    case 2:
                        System.out.print("Enter new persona: ");
                        int idPersona = scanner.nextInt();
                        candidat.setIdPersona(idPersona);
                        break;
                    case 3:
                        System.out.print("Enter new provincia: ");
                        int idProvincia = scanner.nextInt();
                        candidat.setIdProvincia(idProvincia);
                        break;
                    default:
                        System.out.println("Invalid option.");
                        return;
                }
                candidatDAO.update(candidat);
                System.out.println("Candidate updated successfully.");
                return;
                } else if (args == "A") {
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
                return;
            } else if (args == "D") {
                System.out.print("Enter ID of candidat: ");
                int id = scanner.nextInt();
                CandidatDAO candidatDAO = new CandidatDAO(con);
                candidatDAO.delete(id);
                System.out.println("Candidate deleted successfully.");
                return;
            } else {
                System.out.println("Invalid option.");
                return;
            }
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
