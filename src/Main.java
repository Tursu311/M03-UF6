import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        menu(args);
    }
    public static void menu(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Connection con = getConnection();
        System.out.println("1. Afegir");
        System.out.println("2. Mostrar");
        System.out.println("3. Modificar");
        System.out.println("4. Esborrar");
        System.out.println("5. Sortir");
        System.out.print("Escull una opció: ");
        int opcio = scanner.nextInt();

        switch (opcio) {
            case 1:
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
            break;
            case 2:
                CandidatureDAO.donesHomesCandidatura();
                break;
            case 3:
                candidatDAO = new CandidatDAO(con);
                System.out.println(candidatDAO.findAll());
                return;
            System.out.println("What candidate do you want to modify?");
            int id = scanner.nextInt();
            candidat = candidatDAO.findById(id);
            System.out.println("What do you want to modify?");
            System.out.println("1. Candidatura");
            System.out.println("2. Persona");
            System.out.println("3. Provincia");
            System.out.print("Enter option: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.print("Enter new candidatura: ");
                    idCandidatura = scanner.nextInt();
                    candidat.setIdCandidatura(idCandidatura);
                    break;
                case 2:
                    System.out.print("Enter new persona: ");
                    idPersona = scanner.nextInt();
                    candidat.setIdPersona(idPersona);
                    break;
                case 3:
                    System.out.print("Enter new provincia: ");
                    idProvincia = scanner.nextInt();
                    candidat.setIdProvincia(idProvincia);
                    break;
                default:
                    System.out.println("Invalid option.");
                    return;
                candidatDAO.update(candidat);
                System.out.println("Candidate updated successfully.");
                return;
                break;
                case 4:
                    System.out.print("Enter ID of candidat: ");
                    id = scanner.nextInt();
                    candidatDAO = new CandidatDAO(con);
                    candidatDAO.delete(id);
                    System.out.println("Candidate deleted successfully.");
                    return;
            } else{
                System.out.println("Invalid option.");
                return;
            }
            ;
            break;
            case 5:
                System.exit(0);
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
