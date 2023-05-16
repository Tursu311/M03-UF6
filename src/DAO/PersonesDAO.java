package DAO;

import Objecte.Municipis;
import Objecte.Persones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class PersonesDAO implements DAODB <Persones>{
    private Connection con;

    public PersonesDAO(Connection con) {
        this.con = con;
    }
    @Override
    public boolean create(Persones persones) throws SQLException {
        String sql = "INSERT INTO persones (nom, cog1, cog2, sexe, data_naixemnet, dni) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, persones.getNom());
            ps.setString(2, persones.getCog1());
            ps.setString(3, persones.getCog2());
            ps.setString(4, persones.getSexe());
            ps.setDate(5, (java.sql.Date) persones.getDataNaixement());
            ps.setString(6, persones.getDni());
            ps.executeUpdate();
        }
        return false;
    }

    @Override
    public boolean update(Persones persones) throws SQLException {
        String sql = "UPDATE municipis SET nom = ?, cog1 = ?, cog2 = ?, sexe = ?, data_naixement = ?, dni = ? WHERE persona_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, persones.getNom());
            ps.setString(2, persones.getCog1());
            ps.setString(3, persones.getCog2());
            ps.setString(4, persones.getSexe());
            ps.setDate(5, (java.sql.Date) persones.getDataNaixement());
            ps.setString(6, persones.getDni());
            ps.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM persones WHERE persona_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        return false;
    }

    @Override
    public Persones exists(int id) throws SQLException {
        String sql = "SELECT * FROM persones WHERE persona_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                String nom = rs.getString("nom");
                String cog1 = rs.getString("cog1");
                String cog2 = rs.getString("cog2");
                String sexe = rs.getString("sexe");
                Date dataNaixement = rs.getDate("data_naixement");
                String dni = rs.getString("dni");
                Persones persones = new Persones(id, nom, cog1, cog2, sexe, dataNaixement, dni);
                return persones;
            }
        }
    }

    @Override
    public List<Persones> all() throws SQLException {
        String sql = "SELECT * FROM persones";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("persona_id");
                    String nom = rs.getString("nom");
                    String cog1 = rs.getString("cog1");
                    String cog2 = rs.getString("cog2");
                    String sexe = rs.getString("sexe");
                    Date dataNaixement = rs.getDate("data_naixement");
                    String dni = rs.getString("dni");
                    Persones persones = new Persones(id, nom, cog1, cog2, sexe, dataNaixement, dni);
                    System.out.println(persones.getId() + " " + persones.getNom() + " " + persones.getCog1() + " " + persones.getCog2() + " " + persones.getSexe() + " " + persones.getDataNaixement() + " " + persones.getDni());
                }
            }
        }
        return null;
    }
}
