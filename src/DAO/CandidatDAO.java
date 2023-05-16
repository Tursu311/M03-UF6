package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import Objecte.Candidat;

public class CandidatDAO implements DAODB<Candidat> {
    private Connection con;

    public CandidatDAO(Connection con) {
        this.con = con;
    }

    @Override
    public boolean create(Candidat candidat) throws SQLException {
        String sql = "INSERT INTO candidats (candidatura_id, persona_id, provincia_id) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, candidat.getIdCandidatura());
            ps.setInt(2, candidat.getIdPersona());
            ps.setInt(3, candidat.getIdProvincia());
            ps.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean update(Candidat candidat) throws SQLException {
        String sql = "UPDATE candidats SET candidatura_id = ?, persona_id = ? WHERE candidat_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, candidat.getIdCandidatura());
            ps.setInt(2, candidat.getIdPersona());
            ps.setInt(3, candidat.getId());
            ps.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM candidats WHERE candidat_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        return false;
    }

    @Override
    public Candidat exists(int id)  throws SQLException{
        String sql = "SELECT * FROM candidats WHERE candidat_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                int idCandidatura = rs.getInt("candidatura_id");
                int idPersona = rs.getInt("persona_id");
                int idProvincia = rs.getInt("provincia_id");
                Candidat candidat = new Candidat(id, idCandidatura, idPersona, idProvincia);
                return candidat;
            }
        }
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

    @Override
    public List<Candidat> all() throws SQLException {
        String sql = "SELECT * FROM candidats";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("candidat_id");
                    int idCandidatura = rs.getInt("candidatura_id");
                    int idPersona = rs.getInt("persona_id");
                    int idProvincia = rs.getInt("provincia_id");
                    Candidat candidat = new Candidat(id, idCandidatura, idPersona, idProvincia);
                    //We print the object extracting its values
                    System.out.println(candidat.getId() + " " + candidat.getIdCandidatura() + " " + candidat.getIdPersona() + " " + candidat.getIdProvincia());
                }
            }
        }
        return null;
    }
}

