package DAO;

import Objecte.Comunitats_autonomes;
import Objecte.Municipis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MunicipisDAO implements DAODB <Municipis> {
    private Connection con;
    public MunicipisDAO(Connection con) {
        this.con = con;
    }
    @Override
    public boolean create(Municipis municipis) throws SQLException {
        String sql = "INSERT INTO municipis (nom, codi_ine, provincia_id, districte) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, municipis.getNom());
            ps.setInt(2, municipis.getCodiIne());
            ps.setInt(3, municipis.getIdProvincia());
            ps.setString(4, municipis.getDistricte());
            ps.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean update(Municipis municipis) throws SQLException {
        String sql = "UPDATE municipis SET municipi_id = ?,nom = ?, codi_ine = ?, provincia_id = ?, dsitricte = ? WHERE candidat_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, municipis.getId());
            ps.setString(2, municipis.getNom());
            ps.setInt(3, municipis.getCodiIne());
            ps.setInt(4, municipis.getIdProvincia());
            ps.setString(5, municipis.getDistricte());
            ps.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM municipis WHERE municipi_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        return false;
    }

    @Override
    public Municipis exists(int id) throws SQLException {
        String sql = "SELECT * FROM municipis WHERE municipi_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                String nom = rs.getString("nom");
                int codiINE = rs.getInt("codi_ine");
                int provincia_id = rs.getInt("provincia_id");
                String districte = rs.getString("districte");
                Municipis municipis = new Municipis(id, nom, codiINE, provincia_id, districte);
                return municipis;
            }
        }
    }

    @Override
    public List<Municipis> all() throws SQLException {
        String sql = "SELECT * FROM municipis";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("municipi_id");
                    String nom = rs.getString("nom");
                    int codiINE = rs.getInt("codi_ine");
                    int provincia_id = rs.getInt("provincia_id");
                    String districte = rs.getString("districte");
                    Municipis municipis = new Municipis(id, nom, codiINE, provincia_id, districte);
                    System.out.println(municipis.getId() + " " + municipis.getNom() + " " + municipis.getCodiIne() + " " + municipis.getIdProvincia() + " " + municipis.getDistricte());
                }
            }
        }
        return null;
    }
}
