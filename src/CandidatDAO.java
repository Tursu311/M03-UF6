import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CandidatDAO {
    private Connection con;

    public CandidatDAO(Connection con) {
        this.con = con;
    }

    public int countByCandidatura(int idCandidatura) throws SQLException {
        String sql = "SELECT COUNT(*) FROM candidats WHERE id_candidatura = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCandidatura);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }
}
