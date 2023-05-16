package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import Objecte.Comunitats_autonomes;

public class comunitats_autonomesDAO implements DAODB<Comunitats_autonomes> {
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
    @Override
    public boolean create(Comunitats_autonomes comunitat) throws SQLException {
        String sql = "INSERT INTO comunitats_autonomes (nom, codi_ine) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, comunitat.getNom());
            ps.setInt(2, comunitat.getCodiINE());
            ps.executeUpdate();
        }
        return false;
    }
    @Override
    public boolean update(Comunitats_autonomes comunitat) throws SQLException {
        String sql = "UPDATE comunitats_autonomes SET nom = ? WHERE comunitat_aut_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, comunitat.getNom());
            ps.setInt(2, comunitat.getId());
            ps.executeUpdate();
        }
        return false;
    }
    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM comunitats_autonomes WHERE comunitat_aut_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        return false;
    }

    @Override
    public Comunitats_autonomes exists(int id) throws SQLException {
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

    @Override
    public List<Comunitats_autonomes> all() throws SQLException {
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
        return null;
    }
}
