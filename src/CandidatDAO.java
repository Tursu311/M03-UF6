import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CandidatDAO {
    private Connection con;

    public CandidatDAO(Connection con) {
        this.con = con;
    }

    public int countByCandidatura(int idCandidatura) throws SQLException {
        String sql = "SELECT COUNT(*) FROM candidats WHERE candidatura_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCandidatura);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


public void create(Candidat candidat) throws SQLException {
    String sql = "INSERT INTO candidats (candidatura_id, persona_id, provincia_id) VALUES (?, ?, ?)";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, candidat.getIdCandidatura());
        ps.setInt(2, candidat.getIdPersona());
        ps.setInt(3, candidat.getIdProvincia());
        ps.executeUpdate();
    }
}
}

