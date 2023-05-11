import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        menu();
    }

    public static void menu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Candidatures");
        System.out.println("2. Provincies");
        System.out.println("3. Comunitats Autonomes");
        System.out.print("Escull una opció: ");
        int taula = scanner.nextInt();
        switch (taula) {
            case 1 -> menuCandidatures();
            case 2 -> menuProvincies();
            case 3 -> menuComunitatsAutonomes();
        }
    }

    private static void menuComunitatsAutonomes() throws SQLException {
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
                System.out.print("Enter Nom: ");
                String name = scanner.next();
                System.out.print("Enter Code Ine: ");
                int codeIne = scanner.nextInt();
                comunitats_autonomesDAO comunitats_autonomesDAO = new comunitats_autonomesDAO(con);
                Comunitats_autonomes comunitats_autonomes = new Comunitats_autonomes(0, name, codeIne);
                comunitats_autonomesDAO.create(comunitats_autonomes);
                System.out.println("Provincia added successfully.");
                break;
            case 2:
                System.out.println("You want to see all or search by ID?");
                System.out.println("1. All");
                System.out.println("2. Search by ID");
                System.out.print("Enter option: ");
                int option = scanner.nextInt();
                switch (option) {
                    case 1 -> {
                        comunitats_autonomesDAO = new comunitats_autonomesDAO(con);
                        comunitats_autonomesDAO.findAll();
                    }
                    case 2 -> {
                        System.out.print("Enter ID of provincia: ");
                        int id = scanner.nextInt();
                        comunitats_autonomesDAO = new comunitats_autonomesDAO(con);
                        comunitats_autonomes = comunitats_autonomesDAO.findById(id);
                        System.out.println(comunitats_autonomes.getId() + " " + comunitats_autonomes.getNom() + " " + comunitats_autonomes.getCodiINE());
                    }
                    default -> {
                        System.out.println("Invalid option.");
                        return;
                    }
                }
                break;
            case 3:
                comunitats_autonomesDAO = new comunitats_autonomesDAO(con);
                System.out.println("What provincia do you want to modify?");
                int id = scanner.nextInt();
                comunitats_autonomes = comunitats_autonomesDAO.findById(id);
                System.out.println("What do you want to modify?");
                System.out.println("1. Comunitat Autonoma");
                System.out.println("2. Nom");
                System.out.println("3. Codi ine");
                System.out.print("Enter option: ");
                int optiona = scanner.nextInt();
                switch (optiona) {
                    case 1 -> {
                        System.out.print("Enter new comunitat autonoma: ");
                        int idComuniatatAutonoma = scanner.nextInt();
                        comunitats_autonomes.setId(idComuniatatAutonoma);
                    }
                    case 2 -> {
                        System.out.print("Enter new nom: ");
                        name = scanner.next();
                        comunitats_autonomes.setNom(name);
                    }
                    case 3 -> {
                        System.out.print("Enter new codi ine: ");
                        codeIne = scanner.nextInt();
                        comunitats_autonomes.setCodiINE(codeIne);
                    }
                    default -> {
                        System.out.println("Invalid option.");
                        return;
                    }
                }
                comunitats_autonomesDAO.update(comunitats_autonomes);
                System.out.println("Provincia updated successfully.");
                return;
            case 4:
                System.out.print("Enter ID of provincia: ");
                id = scanner.nextInt();
                comunitats_autonomesDAO = new comunitats_autonomesDAO(con);
                comunitats_autonomesDAO.delete(id);
                System.out.println("Provincia deleted successfully.");
                break;
            case 5:
                System.exit(0);
        }
    }
        private static void menuProvincies () throws SQLException {
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
                    System.out.print("Enter ID of Comunitat Autonoma (1-19): ");
                    int idComuniatatAutonoma = scanner.nextInt();
                    System.out.print("Enter name: ");
                    String name = scanner.next();
                    System.out.print("Enter Code Ine: ");
                    int codeIne = scanner.nextInt();
                    ProvinciesDAO provinciesDAO = new ProvinciesDAO(con);
                    Provincies provincia = new Provincies(0, idComuniatatAutonoma, name, codeIne);
                    provinciesDAO.create(provincia);
                    System.out.println("Provincia added successfully.");
                    break;
                case 2:
                    System.out.println("You want to see all or search by ID?");
                    System.out.println("1. All");
                    System.out.println("2. Search by ID");
                    System.out.print("Enter option: ");
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1 -> {
                            provinciesDAO = new ProvinciesDAO(con);
                            provinciesDAO.findAll();
                        }
                        case 2 -> {
                            System.out.print("Enter ID of provincia: ");
                            int id = scanner.nextInt();
                            provinciesDAO = new ProvinciesDAO(con);
                            provincia = provinciesDAO.findById(id);
                            System.out.println(provincia.getId() + " " + provincia.getidComunitatAutonoma() + " " + provincia.getNom() + " " + provincia.getCodiIne());
                        }
                        default -> {
                            System.out.println("Invalid option.");
                            return;
                        }
                    }
                    break;
                case 3:
                    provinciesDAO = new ProvinciesDAO(con);
                    System.out.println("What provincia do you want to modify?");
                    int id = scanner.nextInt();
                    provincia = provinciesDAO.findById(id);
                    System.out.println("What do you want to modify?");
                    System.out.println("1. Comunitat Autonoma");
                    System.out.println("2. Nom");
                    System.out.println("3. Codi ine");
                    System.out.print("Enter option: ");
                    int optiona = scanner.nextInt();
                    switch (optiona) {
                        case 1 -> {
                            System.out.print("Enter new comunitat autonoma: ");
                            idComuniatatAutonoma = scanner.nextInt();
                            provincia.setidComunitatAutonoma(idComuniatatAutonoma);
                        }
                        case 2 -> {
                            System.out.print("Enter new nom: ");
                            name = scanner.next();
                            provincia.setNom(name);
                        }
                        case 3 -> {
                            System.out.print("Enter new codi ine: ");
                            codeIne = scanner.nextInt();
                            provincia.setCodiIne(codeIne);
                        }
                        default -> {
                            System.out.println("Invalid option.");
                            return;
                        }
                    }
                    provinciesDAO.update(provincia);
                    System.out.println("Provincia updated successfully.");
                    return;
                case 4:
                    System.out.print("Enter ID of provincia: ");
                    id = scanner.nextInt();
                    provinciesDAO = new ProvinciesDAO(con);
                    provinciesDAO.delete(id);
                    System.out.println("Provincia deleted successfully.");
                    break;
                case 5:
                    System.exit(0);
            }
        }

        public static void menuCandidatures () throws SQLException {
            Scanner scanner = new Scanner(System.in);
            Connection con = getConnection();
            System.out.println("1. Afegir");
            System.out.println("2. Mostrar");
            System.out.println("3. Modificar");
            System.out.println("4. Esborrar");
            System.out.println("5. Mostrar percentatjes dones/homes");
            System.out.println("6. Sortir");
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
                    break;
                case 2:
                    System.out.println("You want to see all or search by ID?");
                    System.out.println("1. All");
                    System.out.println("2. Search by ID");
                    System.out.print("Enter option: ");
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1 -> {
                            candidatDAO = new CandidatDAO(con);
                            candidatDAO.findAll();
                        }
                        case 2 -> {
                            System.out.print("Enter ID of candidat: ");
                            int id = scanner.nextInt();
                            candidatDAO = new CandidatDAO(con);
                            candidat = candidatDAO.findById(id);
                            System.out.println(candidat.getId() + " " + candidat.getIdCandidatura() + " " + candidat.getIdPersona() + " " + candidat.getIdProvincia());
                        }
                        default -> {
                            System.out.println("Invalid option.");
                            return;
                        }
                    }
                    break;
                case 3:
                    candidatDAO = new CandidatDAO(con);
                    System.out.println("What candidate do you want to modify?");
                    int id = scanner.nextInt();
                    candidat = candidatDAO.findById(id);
                    System.out.println("What do you want to modify?");
                    System.out.println("1. Candidatura");
                    System.out.println("2. Persona");
                    System.out.println("3. Provincia");
                    System.out.print("Enter option: ");
                    int optiona = scanner.nextInt();
                    switch (optiona) {
                        case 1 -> {
                            System.out.print("Enter new candidatura: ");
                            idCandidatura = scanner.nextInt();
                            candidat.setIdCandidatura(idCandidatura);
                        }
                        case 2 -> {
                            System.out.print("Enter new persona: ");
                            idPersona = scanner.nextInt();
                            candidat.setIdPersona(idPersona);
                        }
                        case 3 -> {
                            System.out.print("Enter new provincia: ");
                            idProvincia = scanner.nextInt();
                            candidat.setIdProvincia(idProvincia);
                        }
                        default -> {
                            System.out.println("Invalid option.");
                            return;
                        }
                    }
                    candidatDAO.update(candidat);
                    System.out.println("Candidate updated successfully.");
                    return;
                case 4:
                    System.out.print("Enter ID of candidat: ");
                    id = scanner.nextInt();
                    candidatDAO = new CandidatDAO(con);
                    candidatDAO.delete(id);
                    System.out.println("Candidate deleted successfully.");
                    break;
                case 5:
                    CandidatureDAO.donesHomesCandidatura();
                    break;
                case 6:
                    System.exit(0);
            }
             }
    private static Connection getConnection () {
        try {
            return DriverManager.getConnection("jdbc:mysql://192.168.56.103/programacio?serverTimezone=UTC", "perepi", "pastanaga");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            }
    }

