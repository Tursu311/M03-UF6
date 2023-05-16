package Objecte;

public class Provincies {
    private int id;
    private int idComunitatAutonoma;
    private String nom;

    private int codiIne;


    public Provincies(int id, int idComunitatAutonoma, String nom, int codiIne) {
        this.id = id;
        this.idComunitatAutonoma = idComunitatAutonoma;
        this.nom = nom;
        this.codiIne = codiIne;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getidComunitatAutonoma() {
        return idComunitatAutonoma;
    }

    public void setidComunitatAutonoma(int idComunitatAutonoma) {
        this.idComunitatAutonoma = idComunitatAutonoma;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCodiIne() {
        return codiIne;
    }

    public void setCodiIne(int codiIne) {
        this.codiIne = codiIne;
    }
}
