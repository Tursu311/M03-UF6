package Objecte;

import java.util.Date;

public class Persones {
    private int id;
    private String nom;
    private String cog1;
    private String cog2;
    private String sexe;
    private Date dataNaixement;
    private String dni;
public Persones(int id, String nom, String cog1, String cog2, String sexe, Date dataNaixement, String dni) {
    this.id = id;
    this.nom = nom;
    this.cog1 = cog1;
    this.cog2 = cog2;
    this.sexe = sexe;
    this.dataNaixement = dataNaixement;
    this.dni = dni;
}

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCog1() {
        return cog1;
    }

    public String getCog2() {
        return cog2;
    }

    public String getSexe() {
        return sexe;
    }

    public Date getDataNaixement() {
        return dataNaixement;
    }

    public String getDni() {
        return dni;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCog1(String cog1) {
        this.cog1 = cog1;
    }

    public void setCog2(String cog2) {
        this.cog2 = cog2;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setDataNaixement(Date dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
