package Objecte;

public class Municipis {
    private int id;
    private int idProvincia;
    private String nom;

    private int codiIne;
    private String districte;
    public Municipis(int id, int idProvincia, String nom, int codiIne, String districte) {
        this.id = id;
        this.idProvincia = idProvincia;
        this.nom = nom;
        this.codiIne = codiIne;
        this.districte = districte;
    }

    public int getId() {
        return id;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public String getNom() {
        return nom;
    }

    public int getCodiIne() {
        return codiIne;
    }

    public String getDistricte() {
        return districte;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCodiIne(int codiIne) {
        this.codiIne = codiIne;
    }

    public void setDistricte(String districte) {
        this.districte = districte;
    }
}
