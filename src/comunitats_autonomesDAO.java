import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class comunitats_autonomesDAO {
    private Connection con;

    public comunitats_autonomesDAO(Connection con) {
        this.con = con;
    }

    public int countByCodiINE(String codiINE) throws SQLException {
        String sql = "SELECT COUNT(*) FROM comunitats_autonomes WHERE codi_ine = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codiINE);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void create(Comunitats_autonomes comunitat) throws SQLException {
        String sql = "INSERT INTO comunitats_autonomes (nom, codi_ine) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, comunitat.getNom());
            ps.setInt(2, comunitat.getCodiINE());
            ps.executeUpdate();
        }
    }

    public void update(Comunitats_autonomes comunitat) throws SQLException {
        String sql = "UPDATE comunitats_autonomes SET nom = ? WHERE comunitat_aut_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, comunitat.getNom());
            ps.setInt(2, comunitat.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM comunitats_autonomes WHERE comunitat_aut_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void findAll() throws SQLException {
        String sql = "SELECT * FROM comunitats_autonomes";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("comunitat_aut_id");
                    String nom = rs.getString("nom");
                    int codiINE = rs.getInt("codi_ine");
                    Comunitats_autonomes comunitat = new Comunitats_autonomes(id, nom, codiINE);
                    System.out.println(comunitat.getId() + " " + comunitat.getNom() + " " + comunitat.getCodiINE());
                }
            }
        }
    }

    public Comunitats_autonomes findById(int id) throws SQLException {
        String sql = "SELECT * FROM comunitats_autonomes WHERE comunitat_aut_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                String nom = rs.getString("nom");
                int codiINE = rs.getInt("codi_ine");
                Comunitats_autonomes comunitat = new Comunitats_autonomes(id, nom, codiINE);
                return comunitat;
            }
        }
    }
}
