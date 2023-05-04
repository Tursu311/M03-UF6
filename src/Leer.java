import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.sql.Connection;

public class Leer {

    // Función para leer el archivo 07 y buscar las  comunidades autónomas
    public static void comunitatsAutonomes(Connection con) {
        String nom;
        String ine;
        String ca;
        BufferedReader bfLector = null;
        try {

            Path pathActual = Paths.get(("out"));
            pathActual = pathActual.toAbsolutePath();

            Path pathFitxer = Paths.get(pathActual.toString(),"07021904.DAT");

            //objReader = new BufferedReader(new FileReader(pathFitxer.toString()));

            bfLector = Files.newBufferedReader(pathFitxer, StandardCharsets.ISO_8859_1);
            String strLinia;
            while ((strLinia = bfLector.readLine()) != null) {
                nom = strLinia.substring(14,64).trim();
                ine = strLinia.substring(11,13);
                ca = strLinia.substring(9,11);
                if (ine.equals("99") && !nom.equalsIgnoreCase("Total Nacional")) Insert.comunitatsAutonomes(nom, ca, con);

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bfLector != null)
                    bfLector.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    // Funcion para leer el archivo 07  y buscar las provincias
    public static void provincies (Connection con) {
        String nom;
        String ca;
        String ine;
        int num_escons;
        BufferedReader bfLector = null;
        try {

            Path pathActual = Paths.get(("out"));
            pathActual = pathActual.toAbsolutePath();

            Path pathFitxer = Paths.get(pathActual.toString(),"07021904.DAT");

            //objReader = new BufferedReader(new FileReader(pathFitxer.toString()));

            bfLector = Files.newBufferedReader(pathFitxer, StandardCharsets.ISO_8859_1);
            String strLinia;
            while ((strLinia = bfLector.readLine()) != null) {
                nom = strLinia.substring(14,64).trim();
                ca = strLinia.substring(9,11);
                ine = strLinia.substring(11,13);
                num_escons = Integer.parseInt(strLinia.substring(149,155));
                if (!(ine.equals("99")))Insert.provincies(nom, ine, ca, num_escons, con);

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bfLector != null)
                    bfLector.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Función para leer el archivo 05 y buscar los municipios
    public static void municipis (Connection con) {
        String nom;
        String codi;
        String ine;
        String ine_prov;
        String codi_districte;
        BufferedReader bfLector = null;
        try {

            Path pathActual = Paths.get(("out"));
            pathActual = pathActual.toAbsolutePath();

            Path pathFitxer = Paths.get(pathActual.toString(), "05021904.DAT");

            //objReader = new BufferedReader(new FileReader(pathFitxer.toString()));

            bfLector = Files.newBufferedReader(pathFitxer, StandardCharsets.ISO_8859_1);
            String strLinia;
            while ((strLinia = bfLector.readLine()) != null) {
                nom = strLinia.substring(18,118).trim();
                codi = strLinia.substring(16,18);
                ine = strLinia.substring(13,16);
                ine_prov = strLinia.substring(11,13);
                Insert.municipis(nom, ine, codi, ine_prov, con);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bfLector != null)
                    bfLector.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void candidatures (Connection con) {
        String codi;
        String nomCurt;
        String nomLlarg;
        String codi_ac_prov;
        String codi_ac_ca;
        String codi_ac_nac;
        int any;
        BufferedReader bfLector = null;
        try {

            Path pathActual = Paths.get(("out"));
            pathActual = pathActual.toAbsolutePath();

            Path pathFitxer = Paths.get(pathActual.toString(), "03021904.DAT");

            //objReader = new BufferedReader(new FileReader(pathFitxer.toString()));

            bfLector = Files.newBufferedReader(pathFitxer, StandardCharsets.ISO_8859_1);
            String strLinia;
            while ((strLinia = bfLector.readLine()) != null) {
                codi = strLinia.substring(8, 14);
                nomCurt = strLinia.substring(14, 64);
                nomLlarg = strLinia.substring(64, 214);
                codi_ac_prov = strLinia.substring(214, 220);
                codi_ac_ca = strLinia.substring(220, 226);
                codi_ac_nac = strLinia.substring(226, 232);
                any = Integer.parseInt(strLinia.substring(2, 6));
                Insert.candidatures(codi, nomCurt, nomLlarg, codi_ac_prov, codi_ac_ca, codi_ac_nac, any, con);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bfLector != null)
                    bfLector.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void personas (Connection con) {
        String nom;
        String cog1;
        String cog2;
        String sexe;
        String dob;
        String dia;
        String mes;
        String any;
        String dni;
        BufferedReader bfLector = null;
        try {

            Path pathActual = Paths.get(("out"));
            pathActual = pathActual.toAbsolutePath();

            Path pathFitxer = Paths.get(pathActual.toString(), "04021904.DAT");

            //objReader = new BufferedReader(new FileReader(pathFitxer.toString()));

            bfLector = Files.newBufferedReader(pathFitxer, StandardCharsets.ISO_8859_1);
            String strLinia;
            while ((strLinia = bfLector.readLine()) != null) {
                nom = strLinia.substring(25,50);
                cog1 = strLinia.substring(50,75);
                cog2 = strLinia.substring(75,100);
                sexe = strLinia.substring(100,101);
                dia = strLinia.substring(101,103);
                mes = strLinia.substring(103,105);
                any = strLinia.substring(105,109);
                if (dia.equals("00") || mes.equals("00") || any.equals("0000")) {
                    dob = null;
                }
                else {
                    dob = any + "-" + mes + "-" + dia;
                }
                if (strLinia.substring(109,119).equals("          ")) {
                    dni = null;
                }
                else dni = strLinia.substring(109,119);
                Insert.personas(nom, cog1, cog2, sexe, dob , dni, con);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bfLector != null)
                    bfLector.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void candidats(Connection con) {
        int num;
        String tipus;
        String dni;
        String nom;
        String cog1;
        String cog2;
        int ine;
        int cod_can;

        BufferedReader bfLector = null;
        try {

            Path pathActual = Paths.get(("out"));
            pathActual = pathActual.toAbsolutePath();

            Path pathFitxer = Paths.get(pathActual.toString(), "04021904.DAT");

            //objReader = new BufferedReader(new FileReader(pathFitxer.toString()));

            bfLector = Files.newBufferedReader(pathFitxer, StandardCharsets.ISO_8859_1);
            String strLinia;
            while ((strLinia = bfLector.readLine()) != null) {

                ine = Integer.parseInt(strLinia.substring(9, 11));
                cod_can = Integer.parseInt(strLinia.substring(15, 21));
                num = Integer.parseInt(strLinia.substring(21, 24));
                tipus = strLinia.substring(24, 25);
                nom = strLinia.substring(25, 50);
                cog1 = strLinia.substring(50, 75);
                cog2 = strLinia.substring(75, 100);
                if (strLinia.substring(109, 119).equals("          ")) {
                    dni = "null";
                } else dni = strLinia.substring(109, 119);
                Insert.candidats(ine, cod_can, tipus, num, dni, nom, cog1, cog2, con);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bfLector != null)
                    bfLector.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}