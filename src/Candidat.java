public class Candidat {
    private int id;
    private int idCandidatura;
    private int idPersona;

    public Candidat(int id, int idCandidatura, int idPersona) {
        this.id = id;
        this.idCandidatura = idCandidatura;
        this.idPersona = idPersona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCandidatura() {
        return idCandidatura;
    }

    public void setIdCandidatura(int idCandidatura) {
        this.idCandidatura = idCandidatura;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
}
