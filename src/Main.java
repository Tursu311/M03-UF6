import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            CandidatureDAO.donesHomesCandidatura();
            AddCandidat.main(args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
