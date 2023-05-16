package Objecte;

public class Comunitats_autonomes {
    private int id;
    private String nom;
    private int codiINE;

    public Comunitats_autonomes(int comunitatAutId, String nom, int codiINE) {
        this.id = id;
        this.nom = nom;
        this.codiINE = codiINE;
    }

    public int getId() {
        return id;
    }

    public void setId(int comunitatAutId) {
        this.id = comunitatAutId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCodiINE() {
        return codiINE;
    }

    public void setCodiINE(int codiINE) {
        this.codiINE = codiINE;
    }
}
