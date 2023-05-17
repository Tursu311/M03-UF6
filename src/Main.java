import DAO.*;
import Objecte.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ParseException {
        menu();
    }

    public static void menu() throws SQLException, ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Candidatures");
        System.out.println("2. Provincies");
        System.out.println("3. Comunitats Autonomes");
        System.out.println("4. Candidats");
        System.out.print("5. Municipis");
        System.out.print("6. Persones");
        System.out.println("7. Sortir");
        System.out.print("Escull una opció: ");
        int taula = scanner.nextInt();
        switch (taula) {
            case 1 -> menuCandidatures();
            case 2 -> menuProvincies();
            case 3 -> menuComunitatsAutonomes();
            case 4 -> menuCandidats();
            case 5 -> menuMunicipis();
            case 6 -> menuPersones();
            case 7 -> System.exit(0);
        }
    }

    private static void menuPersones () throws SQLException, ParseException {
        Scanner scanner = new Scanner(System.in);
        Connection con = getConnection();
        System.out.println("1. Add");
        System.out.println("2. Show");
        System.out.println("3. Modify");
        System.out.println("4. Delete");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
        int opcio = scanner.nextInt();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        switch (opcio) {
            case 1:
                System.out.print("Enter name: ");
                String name = scanner.next();
                System.out.print("Enter surname 1: ");
                String cog1 = scanner.next();
                System.out.print("Enter surname 2: ");
                String cog2 = scanner.next();
                System.out.print("Enter gender: ");
                String sexe = scanner.next();
                System.out.print("Enter date of birth (DD/MM/AAAA): ");
                String dataNaix = scanner.next();
                Date dataFormateada = dateFormat.parse(dataNaix);
                System.out.print("Enter DNI: ");
                String dni = scanner.next();
                PersonesDAO personesDAO = new PersonesDAO(con);
                Persones persones = new Persones(0,name, cog1,cog2,sexe,dataFormateada,dni);
                personesDAO.create(persones);
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
                        personesDAO = new PersonesDAO(con);
                        personesDAO.all();
                    }
                    case 2 -> {
                        System.out.print("Enter ID of provincia: ");
                        int id = scanner.nextInt();
                        personesDAO = new PersonesDAO(con);
                        persones = personesDAO.exists(id);
                        System.out.println(persones.getId() +  " " + persones.getNom() + " " + persones.getCog1() + " " + persones.getCog2() + " " + persones.getSexe() + " " + persones.getDataNaixement() + " " + persones.getDni());
                    }
                    default -> {
                        System.out.println("Invalid option.");
                        return;
                    }
                }
                break;
            case 3:
                personesDAO = new PersonesDAO(con);
                System.out.println("What provincia do you want to modify?");
                int id = scanner.nextInt();
                persones = personesDAO.exists(id);
                System.out.println("What do you want to modify?");
                System.out.println("1. Nom");
                System.out.println("2. Surname 1");
                System.out.println("3. Surname 2");
                System.out.println("4. Gender");
                System.out.println("5. Date of birth");
                System.out.println("6. DNI");
                System.out.print("Enter option: ");
                int optiona = scanner.nextInt();
                switch (optiona) {
                    case 1 -> {
                        System.out.print("Enter new name: ");
                        name = scanner.next();
                        persones.setNom(name);
                    }
                    case 2 -> {
                        System.out.print("Enter new surname 1: ");
                        cog1 = scanner.next();
                        persones.setCog1(cog1);
                    }
                    case 3 -> {
                        System.out.print("Enter new surname 2: ");
                        cog2 = scanner.next();
                        persones.setCog2(cog2);
                    }
                    case 4 -> {
                        System.out.print("Enter new gender: ");
                        sexe = scanner.next();
                        persones.setSexe(sexe);
                    }
                    case 5 -> {
                        System.out.print("Enter new date of birth: ");
                        dataNaix = scanner.next();
                        dataFormateada = dateFormat.parse(dataNaix);
                        persones.setDataNaixement(dataFormateada);
                    }
                    case 6 -> {
                        System.out.print("Enter new DNI: ");
                        dni = scanner.next();
                        persones.setDni(dni);
                    }
                    default -> {
                        System.out.println("Invalid option.");
                        return;
                    }
                }
                personesDAO.update(persones);
                System.out.println("Persones updated successfully.");
                return;
            case 4:
                System.out.print("Enter ID of persones: ");
                id = scanner.nextInt();
                personesDAO = new PersonesDAO(con);
                personesDAO.delete(id);
                System.out.println("Persona deleted successfully.");
                break;
            case 5:
                System.exit(0);
        }
    }
    private static void menuMunicipis () throws SQLException {
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
                System.out.print("Enter name: ");
                String name = scanner.next();
                System.out.print("Enter Code Ine: ");
                int codeIne = scanner.nextInt();
                System.out.println("Enter ID of Provincia: ");
                int idProvincia = scanner.nextInt();
                System.out.println("Enter District: ");
                String district = scanner.next();
                MunicipisDAO municipisDAO = new MunicipisDAO(con);
                Municipis municipis = new Municipis(0, name, codeIne, idProvincia, district);
                municipisDAO.create(municipis);
                System.out.println("Municipi added successfully.");
                break;
            case 2:
                System.out.println("You want to see all or search by ID?");
                System.out.println("1. All");
                System.out.println("2. Search by ID");
                System.out.print("Enter option: ");
                int option = scanner.nextInt();
                switch (option) {
                    case 1 -> {
                        municipisDAO = new MunicipisDAO(con);
                        municipisDAO.all();
                    }
                    case 2 -> {
                        System.out.print("Enter ID of provincia: ");
                        int id = scanner.nextInt();
                        municipisDAO = new MunicipisDAO(con);
                        municipis = municipisDAO.exists(id);
                        System.out.println(municipis.getId() + " " + municipis.getNom() + " " + municipis.getCodiIne() + " " + municipis.getIdProvincia() + " " + municipis.getDistricte());
                    }
                    default -> {
                        System.out.println("Invalid option.");
                        return;
                    }
                }
                break;
            case 3:
                municipisDAO = new MunicipisDAO(con);
                System.out.println("What provincia do you want to modify?");
                int id = scanner.nextInt();
                municipis = municipisDAO.exists(id);
                System.out.println("What do you want to modify?");
                System.out.println("1. Name");
                System.out.println("2. Codi ine");
                System.out.print("Enter option: ");
                int optiona = scanner.nextInt();
                switch (optiona) {
                    case 1 -> {
                        System.out.print("Enter new nom: ");
                        name = scanner.next();
                        municipis.setNom(name);
                    }
                    case 2 -> {
                        System.out.print("Enter new codi ine: ");
                        codeIne = scanner.nextInt();
                        municipis.setCodiIne(codeIne);
                    }
                    case 3 -> {
                        System.out.print("Enter new id provincia: ");
                        idProvincia = scanner.nextInt();
                        municipis.setIdProvincia(idProvincia);
                    }
                    case 4 -> {
                        System.out.print("Enter new district: ");
                        district = scanner.next();
                        municipis.setDistricte(district);
                    }
                    default -> {
                        System.out.println("Invalid option.");
                        return;
                    }
                }
                municipisDAO.update(municipis);
                System.out.println("Municipis updated successfully.");
                return;
            case 4:
                System.out.print("Enter ID of provincia: ");
                id = scanner.nextInt();
                municipisDAO = new MunicipisDAO(con);
                municipisDAO.delete(id);
                System.out.println("Municipis deleted successfully.");
                break;
            case 5:
                System.exit(0);
        }
    }

    private static void menuCandidats () throws SQLException {
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
                        comunitats_autonomesDAO.all();
                    }
                    case 2 -> {
                        System.out.print("Enter ID of provincia: ");
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
                System.out.println("What provincia do you want to modify?");
                int id = scanner.nextInt();
                comunitats_autonomes = comunitats_autonomesDAO.exists(id);
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
                            candidatDAO.all();
                        }
                        case 2 -> {
                            System.out.print("Enter ID of candidat: ");
                            int id = scanner.nextInt();
                            candidatDAO = new CandidatDAO(con);
                            candidat = candidatDAO.exists(id);
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
                    candidat = candidatDAO.exists(id);
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

