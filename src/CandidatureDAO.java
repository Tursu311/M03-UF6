import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatureDAO {
    private Connection con;

    public CandidatureDAO(Connection con) {
        this.con = con;
    }

    public List<Candidature> findAll() throws SQLException {
        List<Candidature> candidatures = new ArrayList<>();
        String sql = "SELECT * FROM candidatures";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("candidatura_id");
                String nomCurt = rs.getString("nom_curt");
                Candidature candidature = new Candidature(id, nomCurt);
                candidatures.add(candidature);
            }
        }
        return candidatures;
    }

    public int countCandidats(int idCandidatura, String sexe) throws SQLException {
        String sql = "SELECT COUNT(*) FROM candidats INNER JOIN persones ON candidats.persona_id = persones.persona_id WHERE persones.sexe = ? AND candidats.candidatura_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, sexe);
            ps.setInt(2, idCandidatura);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    public static void donesHomesCandidatura() throws SQLException {
        try (Connection con = getConnection()) {
            CandidatureDAO candidatureDAO = new CandidatureDAO(con);
            List<Candidature> candidatures = candidatureDAO.findAll();
            for (Candidature candidature : candidatures) {
                int idCandidatura = candidature.getId();
                String nomCandidatura = candidature.getNomCurt();
                int totalPersones = new CandidatDAO(con).countByCandidatura(idCandidatura);
                int dones = candidatureDAO.countCandidats(idCandidatura, "F");
                int homes = candidatureDAO.countCandidats(idCandidatura, "M");
                System.out.println("Candidatura: " + nomCandidatura);
                System.out.println("Dones: " + dones + " Homes: " + homes);
                System.out.println("Percentatge dones: " + (dones * 100 / totalPersones) + "%");
                System.out.println("Percentatge homes: " + (homes * 100 / totalPersones) + "%");
                System.out.println();
            }
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

