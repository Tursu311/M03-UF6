package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatureDAO implements DAODB<Objecte.Candidature> {
    private Connection con;
    public CandidatureDAO(Connection con) {
        this.con = con;
    }
    @Override
    public boolean create(Objecte.Candidature candidature) throws SQLException {
        String sql = "INSERT INTO candidatures (eleccio_id, codi_candidatura, nom_curt, nom_llarg, codi_acumulacio_provincia, codi_acumulacio_ca, codi_acumulacio_nacional) VALUES (?, ?, ?, ?, ?, ?, ?)";
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
        String sql = "UPDATE candidatures SET eleccio_id = ?, codi_candidatura = ?, nom_curt = ?, nom_llarg = ?, codi_acumulacio_provincia = ?, codi_acumulacio_ca = ?, codi_acumulacio_nacional = ? WHERE candidatura_id = ?";
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

    @Override
    public Objecte.Candidature exists(int id) throws SQLException {
        String sql = "SELECT * FROM candidats WHERE candidat_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                int eleccio_id = rs.getInt("eleccio_id ");
                String nomCurt = rs.getString("nom_curt");
                String nomLlarg = rs.getString("nom_llarg");
                int eleccioId = rs.getInt("eleccio_id");
                int codiCandidatura = rs.getInt("codi_candidatura");
                int codiAcumulacioProvincia = rs.getInt("codi_acumulacio_provincia");
                int codiAcumulacioCa = rs.getInt("codi_acumulacio_ca");
                int codiAcumulacioNacional = rs.getInt("codi_acumulacio_nacional");
                Objecte.Candidature candidature = new Objecte.Candidature(eleccio_id, nomCurt, nomLlarg, eleccioId, codiCandidatura, codiAcumulacioProvincia, codiAcumulacioCa, codiAcumulacioNacional);
                return candidature;
            }
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
    public int countCandidats(int idCandidatura, String sexe) throws SQLException {
        String sql = "SELECT COUNT(*) FROM candidats INNER JOIN persones ON candidats.persona_id = persones.persona_id WHERE persones.sexe = ? AND candidats.candidatura_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, sexe);
            ps.setInt(2, idCandidatura);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

        @Override
    public List<Objecte.Candidature> all() throws SQLException {
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
                int codiAcumulacioNacional = rs.getInt("codi_acumulacio_nacional");
                Candidature candidature = new Candidature(id, nomCurt, nomLlarg, eleccioId, codiCandidatura, codiAcumulacioProvincia, codiAcumulacioCa, codiAcumulacioNacional);
                candidatures.add(candidature);
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
                int totalPersones = new CandidatDAO(con).countByCandidatura(idCandidatura);
                int dones = candidatureDAO.countCandidats(idCandidatura, "F");
                int homes = candidatureDAO.countCandidats(idCandidatura, "M");
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
