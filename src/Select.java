import java.sql.*;

public class Select {

    // Método para hacer un Select e insertar la id de comunitats autonomes según el código ine
    public static int provincies(Connection con, String ca) {
        int x = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //SENTÈNCIA SELECT
            // Busca ID de comunitat autonoma para introducirlo en la tabla Provincias
            String query = "SELECT comunitat_aut_id " +
                    " FROM comunitats_autonomes " +
                    "WHERE codi_ine = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, ca);

            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                x = rs.getInt("comunitat_aut_id");
            }
        }
        catch(Exception e){
            System.out.println(e);}

        return x;
    }
    // Método para hacer un Select e insertar la id de provincias según el código ine

    public static int municipis(Connection con, String ine_prov) {
        int x = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //SENTÈNCIA SELECT
            // Busca ID de comunitat autonoma para introducirlo en la tabla Provincias
            String query = "SELECT provincia_id " +
                    " FROM provincies " +
                    "WHERE codi_ine = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, ine_prov);

            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                x = rs.getInt("provincia_id");
            }
        }
        catch(Exception e){
            System.out.println(e);}

        return x;
    }

    // Método para hacer un Select e insertar la id de eleccions según el any

    public static int elecciones1(int any, Connection con) {
        int x = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //SENTÈNCIA SELECT
            // Busca ID de comunitat autonoma para introducirlo en la tabla Provincias
            String query = "SELECT eleccio_id " +
                    " FROM eleccions " +
                    "WHERE any = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, any);

            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                x = rs.getInt("eleccio_id");
            }
        }
        catch(Exception e){
            System.out.println(e);}

        return x;
    }

    public static int candidatsCod(int cod_can, Connection con) {
        int x = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //SENTÈNCIA SELECT
            // Busca ID de comunitat autonoma para introducirlo en la tabla Provincias
            String query = "SELECT candidatura_id " +
                    " FROM candidatures  " +
                    "WHERE codi_candidatura = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, cod_can);

            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                x = rs.getInt("candidatura_id");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return x;
    }
    public static int candidatsPersona(String dni,String nom,String cog1,String cog2, Connection con) {
        int x = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //SENTÈNCIA SELECT
            // Busca ID de comunitat autonoma para introducirlo en la tabla Provincias
            if (!(dni.equals("null"))) {
                String query = "SELECT persona_id " +
                        " FROM persones " +
                        "WHERE dni = ?";

                PreparedStatement preparedStmt = con.prepareStatement(query);

                preparedStmt.setString(1, dni);

                ResultSet rs = preparedStmt.executeQuery();
                if (rs.next()) {
                    x = rs.getInt("persona_id");
                }
            }else{
                String query = "SELECT persona_id " +
                        " FROM   persones " +
                        "WHERE nom = ? && cog1 = ? && cog2 = ?" +
                        "LIMIT 1";

                PreparedStatement preparedStmt = con.prepareStatement(query);

                preparedStmt.setString(1, nom);
                preparedStmt.setString(2, cog1);
                preparedStmt.setString(3, cog2);

                ResultSet rs = preparedStmt.executeQuery();
                if (rs.next()) {
                    x = rs.getInt("persona_id");
                }
            }
            } catch(Exception e){
                System.out.println(e);
            }

            return x;
        }
    public static int candidatsProvincia(int ine, Connection con) {
        int x = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //SENTÈNCIA SELECT
            // Busca ID de comunitat autonoma para introducirlo en la tabla Provincias
            String query = "SELECT provincia_id " +
                    " FROM provincies " +
                    "WHERE codi_ine = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, ine);

            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                x = rs.getInt("provincia_id");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return x;
    }
}