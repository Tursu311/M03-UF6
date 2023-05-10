import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvinciesDAO {
    private Connection con;

    public ProvinciesDAO(Connection con) {
        this.con = con;
    }

    public void create(Provincies provincia) throws SQLException {
        String sql = "INSERT INTO provincies (comunitat_aut_id, nom, codi_ine) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, provincia.getidComunitatAutonoma());
            ps.setString(2, provincia.getNom());
            ps.setInt(3, provincia.getCodiIne());
            ps.executeUpdate();
        }
    }

    public void update(Provincies provincia) throws SQLException {
        String sql = "UPDATE provincies SET comuniatat_aut_id = ?, nom = ? WHERE provincia_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, provincia.getidComunitatAutonoma());
            ps.setString(2, provincia.getNom());
            ps.setInt(3, provincia.getId());
            ps.executeUpdate();
        }
    }

    public void delete (int id) throws SQLException {
        String sql = "DELETE FROM provincia WHERE provincia_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void findAll () throws SQLException {
        String sql = "SELECT * FROM provincies";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("provincia_id");
                    int idComunitat = rs.getInt("comunitat_aut_id");
                    String nom = rs.getString("nom");
                    int idCodiIne = rs.getInt("codi_ine");
                    Provincies provincies = new Provincies(id, idComunitat, nom, idCodiIne);
                    //We print the object extracting its values
                    System.out.println(provincies.getId() + " " + provincies.getidComunitatAutonoma() + " " + provincies.getNom() + " " + provincies.getCodiIne());
                }
            }
        }
    }

    public Provincies findById (int id) throws SQLException {
        String sql = "SELECT * FROM provincies WHERE provincia_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                int idCandidatura = rs.getInt("comunitat_aut_id");
                String nom = rs.getString("nom");
                int idCodiIne = rs.getInt("provincia_id");
                Provincies provincies = new Provincies(id, idCandidatura, nom, idCodiIne);
                return provincies;
            }
        }

    }
}

