import DAO.*;
import Objecte.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ParseException {
        menu();
    }

    public static void menu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Candidatures");
        System.out.println("2. Provincies");
        System.out.println("3. Comunitats Autonomes");
        System.out.println("4. Candidats");
        System.out.println("5. Sortir");
        System.out.print("Choose an option: ");
        int taula = scanner.nextInt();
        switch (taula) {
            case 1 -> menuCandidatures();
            case 2 -> menuProvincies();
            case 3 -> menuComunitatsAutonomes();
            case 4 -> menuCandidats();
            case 5 -> System.exit(0);
        }
    }
    private static void menuCandidats () throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Connection con = getConnection();
        System.out.println("1. Add");
        System.out.println("2. Show");
        System.out.println("3. Modify");
        System.out.println("4. Delete");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
        int opcio = scanner.nextInt();

        switch (opcio) {
            case 1:
                System.out.print("Enter candidatura ID: ");
                int candidaturaid = scanner.nextInt();
                System.out.print("Enter persona ID: ");
                int personaid = scanner.nextInt();
                System.out.println("Enter ID of Provincia: ");
                int idProvincia = scanner.nextInt();
                CandidatDAO candidatDAO = new CandidatDAO(con);
                Candidat candidat = new Candidat(0, candidaturaid, personaid, idProvincia);
                candidatDAO.create(candidat);
                System.out.println("Candidat added successfully.");
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
                        candidatDAO.all();
                    }
                    case 2 -> {
                        System.out.print("Enter ID of candidat: ");
                        int id = scanner.nextInt();
                        candidatDAO = new CandidatDAO(con);
                        candidat = candidatDAO.exists(id);
                        System.out.println(candidat.getId() + " "  + candidat.getIdCandidatura() + " " + candidat.getIdPersona() + " " + candidat.getIdProvincia());
                    }
                    default -> {
                        System.out.println("Invalid option.");
                        return;
                    }
                }
                break;
            case 3:
                candidatDAO = new CandidatDAO(con);
                System.out.println("What candidat do you want to modify?");
                int id = scanner.nextInt();
                candidat = candidatDAO.exists(id);
                System.out.println("What do you want to modify?");
                System.out.println("1. Candidatura ID");
                System.out.println("2. Persona ID");
                System.out.println("3. Provincia ID");
                System.out.print("Enter option: ");
                int optiona = scanner.nextInt();
                switch (optiona) {
                    case 1 -> {
                        System.out.print("Enter new candidatura ID: ");
                        candidaturaid = scanner.nextInt();
                        candidat.setIdCandidatura(candidaturaid);
                    }
                    case 2 -> {
                        System.out.print("Enter new persona ID: ");
                        personaid = scanner.nextInt();
                        candidat.getIdPersona();
                    }
                    case 3 -> {
                        System.out.print("Enter new provincia ID: ");
                        idProvincia = scanner.nextInt();
                        candidat.setIdProvincia(idProvincia);
                    }
                    default -> {
                        System.out.println("Invalid option.");
                        return;
                    }
                }
                candidatDAO.update(candidat);
                System.out.println("Candidat updated successfully.");
                System.out.println(candidat.getId() + " "  + candidat.getIdCandidatura() + " " + candidat.getIdPersona() + " " + candidat.getIdProvincia());
                return;
            case 4:
                System.out.print("Enter ID of candidat: ");
                id = scanner.nextInt();
                candidatDAO = new CandidatDAO(con);
                candidatDAO.delete(id);
                System.out.println("Candidat deleted successfully.");
                break;
            case 5:
                System.exit(0);
        }
    }

    private static void menuComunitatsAutonomes() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Connection con = getConnection();
        System.out.println("1. Add");
        System.out.println("2. Show");
        System.out.println("3. Modify");
        System.out.println("4. Delete");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
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
                System.out.println("Comunitat Autonoma added successfully.");
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
                        comunitats_autonomesDAO.all();
                    }
                    case 2 -> {
                        System.out.print("Enter ID of Comunitat Autonoma: ");
                        int id = scanner.nextInt();
                        comunitats_autonomesDAO = new comunitats_autonomesDAO(con);
                        comunitats_autonomes = comunitats_autonomesDAO.exists(id);
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
                System.out.println("What comunitat autonoma do you want to modify?");
                int id = scanner.nextInt();
                comunitats_autonomes = comunitats_autonomesDAO.exists(id);
                System.out.println("What do you want to modify?");
                System.out.println("1. Nom");
                System.out.println("2. Codi ine");
                System.out.print("Enter option: ");
                int optiona = scanner.nextInt();
                switch (optiona) {
                    case 1 -> {
                        System.out.print("Enter new nom: ");
                        name = scanner.next();
                        comunitats_autonomes.setNom(name);
                    }
                    case 2 -> {
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
                System.out.println("Comunitat autonoma updated successfully.");
                System.out.println(comunitats_autonomes.getId() + " " + comunitats_autonomes.getNom() + " " + comunitats_autonomes.getCodiINE());
                return;
            case 4:
                System.out.print("Enter ID of comunitat autonoma: ");
                id = scanner.nextInt();
                comunitats_autonomesDAO = new comunitats_autonomesDAO(con);
                comunitats_autonomesDAO.delete(id);
                System.out.println("Comunitat autonoma deleted successfully.");
                break;
            case 5:
                System.exit(0);
        }
    }
        private static void menuProvincies () throws SQLException {
            Scanner scanner = new Scanner(System.in);
            Connection con = getConnection();
            System.out.println("1. Add");
            System.out.println("2. Show");
            System.out.println("3. Modify");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
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
                            provinciesDAO.all();
                        }
                        case 2 -> {
                            System.out.print("Enter ID of provincia: ");
                            int id = scanner.nextInt();
                            provinciesDAO = new ProvinciesDAO(con);
                            provincia = provinciesDAO.exists(id);
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
                    provincia = provinciesDAO.exists(id);
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
                    System.out.println(provincia.getId() + " " + provincia.getidComunitatAutonoma() + " " + provincia.getNom() + " " + provincia.getCodiIne());
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
            System.out.println("1. Add");
            System.out.println("2. Show");
            System.out.println("3. Modify");
            System.out.println("4. Delete");
            System.out.println("5. Show  percentages Women/Men");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int opcio = scanner.nextInt();

            switch (opcio) {
                case 1:
                    System.out.print("Enter ID of eleccio: ");
                    int eleccioId = scanner.nextInt();
                    System.out.print("Enter code candidatura: ");
                    int codiCa = scanner.nextInt();
                    System.out.print("Enter short name ");
                    String sName = scanner.next();
                    System.out.println("Enter name: ");
                    String name = scanner.next();
                    System.out.println("Enter code accumulation province: ");
                    int codeAcumulacio = scanner.nextInt();
                    System.out.println("Enter code accumulation autonomous community: ");
                    int codeAcumulacioAutonomia = scanner.nextInt();
                    System.out.println("Enter code accumulation state: ");
                    int codeAcumulacioEstat = scanner.nextInt();
                    CandidatureDAO candidaturaDAO = new CandidatureDAO(con);
                    Candidature candidature = new Candidature(0, sName, name,eleccioId, codiCa, codeAcumulacio, codeAcumulacioAutonomia, codeAcumulacioEstat);
                    candidaturaDAO.create(candidature);
                    System.out.println("Candidature added successfully.");
                    break;
                case 2:
                    System.out.println("You want to see all or search by ID?");
                    System.out.println("1. All");
                    System.out.println("2. Search by ID");
                    System.out.print("Enter option: ");
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1 -> {
                            candidaturaDAO = new CandidatureDAO(con);
                            candidaturaDAO.all();
                        }
                        case 2 -> {
                            System.out.print("Enter ID of candidature: ");
                            int id = scanner.nextInt();
                            candidaturaDAO = new CandidatureDAO(con);
                            candidature = candidaturaDAO.exists(id);
                            System.out.println(candidature.getId() + " " + candidature.getNomCurt() + " " + candidature.getNomLlarg() + " " + candidature.getIdEleccio() + " " + candidature.getCodiCandidatura() + " " + candidature.getCodiAcumulacioProvincia() + " " + candidature.getCodiAcumulacioCa() + " " + candidature.getCodiAcumulacioNacional());
                        }
                        default -> {
                            System.out.println("Invalid option.");
                            return;
                        }
                    }
                    break;
                case 3:
                    candidaturaDAO = new CandidatureDAO(con);
                    System.out.println("What candidature do you want to modify?");
                    int id = scanner.nextInt();
                    candidature = candidaturaDAO.exists(id);
                    System.out.println("What do you want to modify?");
                    System.out.println("1. Eleccio ID");
                    System.out.println("2. Code Candidature");
                    System.out.println("3. Short Name");
                    System.out.println("4. Name");
                    System.out.println("5. Code accumulation province");
                    System.out.println("6. Code accumulation autonomous community");
                    System.out.println("7. Code accumulation state");
                    System.out.print("Enter option: ");
                    int optiona = scanner.nextInt();
                    switch (optiona) {
                        case 1 -> {
                            System.out.print("Enter new eleccio: ");
                            eleccioId = scanner.nextInt();
                            candidature.setEleccioId(eleccioId);
                        }
                        case 2 -> {
                            System.out.print("Enter new code candidature: ");
                            codiCa = scanner.nextInt();
                            candidature.setCodiCandidatura(codiCa);
                        }
                        case 3 -> {
                            System.out.print("Enter new short name: ");
                            sName = scanner.next();
                            candidature.setNomCurt(sName);
                        }
                        case 4 -> {
                            System.out.print("Enter new name: ");
                            name = scanner.next();
                            candidature.setNomLlarg(name);
                        }
                        case 5 -> {
                            System.out.print("Enter new code accumulation province: ");
                            codeAcumulacio = scanner.nextInt();
                            candidature.setCodiAcumulacioProvincia(codeAcumulacio);
                        }
                        case 6 -> {
                            System.out.print("Enter new code accumulation autonomous community: ");
                            codeAcumulacioAutonomia = scanner.nextInt();
                            candidature.setCodiAcumulacioCa(codeAcumulacioAutonomia);
                        }
                        case 7 -> {
                            System.out.print("Enter new code accumulation state: ");
                            codeAcumulacioEstat = scanner.nextInt();
                            candidature.setCodiAcumulacioNacional(codeAcumulacioEstat);
                        }
                        default -> {
                            System.out.println("Invalid option.");
                            return;
                        }
                    }
                    candidaturaDAO.update(candidature);
                    System.out.println(candidature.getId() + " " + candidature.getNomCurt() + " " + candidature.getNomLlarg() + " " + candidature.getIdEleccio() + " " + candidature.getCodiCandidatura() + " " + candidature.getCodiAcumulacioProvincia() + " " + candidature.getCodiAcumulacioCa() + " " + candidature.getCodiAcumulacioNacional());
                    System.out.println("Candidature updated successfully.");
                    return;
                case 4:
                    System.out.print("Enter ID of candidature: ");
                    id = scanner.nextInt();
                    candidaturaDAO = new CandidatureDAO(con);
                    candidaturaDAO.delete(id);
                    System.out.println("Candidature deleted successfully.");
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