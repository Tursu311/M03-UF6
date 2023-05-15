import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatureDAO2 implements DAODB<Candidature>{
    private Connection con;

    @Override
    public boolean create(Candidature candidature) throws SQLException {
        String sql = "INSERT INTO candidats (eleccio_id, codi_candidatura, nom_curt, nom_llarg, codi_acumulacio_provincia, codi_acumulacio_ca, codi_acumulacio_nacional) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, 1);
            ps.setInt(2, 1);
            ps.setString(3, "1");
            ps.setString(4, " ");
            ps.setInt(5, 1);
            ps.setInt(6, 1);
            ps.setInt(7, 1);
            ps.executeUpdate();
        }
        return false;
    }

    @Override
    public boolean read(Candidature candidature) {
        return false;
    }

    @Override
    public boolean update(Candidature candidature) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public Candidature exists(int id) throws SQLException {
        return null;
    }

    @Override
    public int count() {
        return 0;
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

    @Override
    public List<Candidature> all() throws SQLException {
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
