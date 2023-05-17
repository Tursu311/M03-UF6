package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Objecte.Candidature;

public class CandidatureDAO implements DAODB<Objecte.Candidature> {
    private Connection con;
    public CandidatureDAO(Connection con) {
        this.con = con;
    }
    @Override
    public boolean create(Objecte.Candidature candidature) throws SQLException {
        String sql = "INSERT INTO candidatures (eleccio_id, codi_candidatura, nom_curt, nom_llarg, codi_acumulacio_provincia, codi_acumulacio_ca, codi_acumulario_nacional) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, candidature.getIdEleccio());
            ps.setInt(2, candidature.getCodiCandidatura());
            ps.setString(3, candidature.getNomCurt());
            ps.setString(4, candidature.getNomLlarg());
            ps.setInt(5, candidature.getCodiAcumulacioProvincia());
            ps.setInt(6, candidature.getCodiAcumulacioCa());
            ps.setInt(7, candidature.getCodiAcumulacioNacional());
            ps.executeUpdate();
        }
        return false;
    }

    @Override
    public boolean update(Objecte.Candidature candidature) throws SQLException {
        String sql = "UPDATE candidatures SET eleccio_id = ?, codi_candidatura = ?, nom_curt = ?, nom_llarg = ?, codi_acumulacio_provincia = ?, codi_acumulacio_ca = ?, codi_acumulario_nacional = ? WHERE candidatura_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, candidature.getIdEleccio());
            ps.setInt(2, candidature.getCodiCandidatura());
            ps.setString(3, candidature.getNomCurt());
            ps.setString(4, candidature.getNomLlarg());
            ps.setInt(5, candidature.getCodiAcumulacioProvincia());
            ps.setInt(6, candidature.getCodiAcumulacioCa());
            ps.setInt(7, candidature.getCodiAcumulacioNacional());
            ps.setInt(8, candidature.getId());
            ps.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM candidatures WHERE candidatura_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        return false;
    }

    @Override //TODO canviar a candidatures
    public Objecte.Candidature exists(int id) throws SQLException {
        String sql = "SELECT * FROM candidatures WHERE candidatura_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                int eleccio_id = rs.getInt("eleccio_id");
                String nomCurt = rs.getString("nom_curt");
                String nomLlarg = rs.getString("nom_llarg");
                int eleccioId = rs.getInt("eleccio_id");
                int codiCandidatura = rs.getInt("codi_candidatura");
                int codiAcumulacioProvincia = rs.getInt("codi_acumulacio_provincia");
                int codiAcumulacioCa = rs.getInt("codi_acumulacio_ca");
                int codiAcumulacioNacional = rs.getInt("codi_acumulario_nacional");
                Candidature candidature = new Candidature(id, nomCurt, nomLlarg, eleccioId, codiCandidatura, codiAcumulacioProvincia, codiAcumulacioCa, codiAcumulacioNacional);
                return candidature;
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

    public int count(int id, String sexe ) throws SQLException {
        String sql = "SELECT COUNT(*) FROM candidatures WHERE candidatura_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();

                return rs.getInt(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
                String nomLlarg = rs.getString("nom_llarg");
                int eleccioId = rs.getInt("eleccio_id");
                int codiCandidatura = rs.getInt("codi_candidatura");
                int codiAcumulacioProvincia = rs.getInt("codi_acumulacio_provincia");
                int codiAcumulacioCa = rs.getInt("codi_acumulacio_ca");
                int codiAcumulacioNacional = rs.getInt("codi_acumulario_nacional");
                Candidature candidature = new Candidature(id, nomCurt, nomLlarg, eleccioId, codiCandidatura, codiAcumulacioProvincia, codiAcumulacioCa, codiAcumulacioNacional);
                candidatures.add(candidature);
                System.out.println(candidature.getId() + " " + candidature.getNomCurt() + " " + candidature.getNomLlarg() + " " + candidature.getIdEleccio() + " " + candidature.getCodiCandidatura() + " " + candidature.getCodiAcumulacioProvincia() + " " + candidature.getCodiAcumulacioCa() + " " + candidature.getCodiAcumulacioNacional());
            }
        }
        return candidatures;
    }
    public static void donesHomesCandidatura() throws SQLException {
        try (Connection con = getConnection()) {
            CandidatureDAO candidatureDAO = new CandidatureDAO(con);
            List<Candidature> candidatures = candidatureDAO.all();
            for (Candidature candidature : candidatures) {
                int idCandidatura = candidature.getId();
                String nomCandidatura = candidature.getNomCurt();
                int totalPersones = candidatureDAO.countByCandidatura(idCandidatura);
                if (totalPersones == 0) {
                    break;
                }
                int dones = new CandidatDAO(con).countCandidats(idCandidatura, "F");
                int homes = new CandidatDAO(con).countCandidats(idCandidatura, "M");
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
