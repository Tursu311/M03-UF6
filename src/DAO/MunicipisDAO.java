package DAO;

import Objecte.Municipis;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        return false;
    }

    @Override
    public Municipis exists(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Municipis> all() throws SQLException {
        return null;
    }
}
