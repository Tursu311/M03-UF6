import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        menu(args);
    }
    public static void menu(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Afegir candidat");
        System.out.println("2. Mostrar percentatge d'homes i dones que hi ha a una candidatura");
        System.out.println("3. Modificar");
        System.out.println("4. Sortir");
        System.out.print("Escull una opció: ");
        int opcio = scan.nextInt();

        switch(opcio){
            case 1: AMDCandidat.main(args);
            break;
            case 2: CandidatureDAO.donesHomesCandidatura();
            break;
            case 3: break;
            case 4: System.exit(0);
        }


    }

}
