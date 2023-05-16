public class Candidature {
    private int id;
    private int eleccioId;
    private int codiCandidatura;
    private int codiAcumulacioProvincia;
    private int codiAcumulacioCa;
    private int codiAcumulacioNacional;
    private String nomCurt;
    private String nomLlarg;

    public Candidature(int id, String nomCurt, String nomLlarg, int eleccioId, int codiCandidatura, int codiAcumulacioProvincia, int codiAcumulacioCa, int codiAcumulacioNacional) {
        this.id = id;
        this.nomCurt = nomCurt;
        this.nomLlarg = nomLlarg;
        this.eleccioId = eleccioId;
        this.codiCandidatura = codiCandidatura;
        this.codiAcumulacioProvincia = codiAcumulacioProvincia;
        this.codiAcumulacioCa = codiAcumulacioCa;
        this.codiAcumulacioNacional = codiAcumulacioNacional;
    }

    public int getIdEleccio() {
        return eleccioId;
    }

    public int getId() {
        return id;
    }

    public int getCodiCandidatura() {
        return codiCandidatura;
    }

    public int getCodiAcumulacioProvincia() {
        return codiAcumulacioProvincia;
    }

    public int getCodiAcumulacioCa() {
        return codiAcumulacioCa;
    }

    public int getCodiAcumulacioNacional() {
        return codiAcumulacioNacional;
    }

    public String getNomCurt() {
        return nomCurt;
    }

    public String getNomLlarg() {
        return nomLlarg;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEleccioId(int eleccioId) {
        this.eleccioId = eleccioId;
    }

    public void setCodiCandidatura(int codiCandidatura) {
        this.codiCandidatura = codiCandidatura;
    }

    public void setCodiAcumulacioProvincia(int codiAcumulacioProvincia) {
        this.codiAcumulacioProvincia = codiAcumulacioProvincia;
    }

    public void setCodiAcumulacioCa(int codiAcumulacioCa) {
        this.codiAcumulacioCa = codiAcumulacioCa;
    }

    public void setCodiAcumulacioNacional(int codiAcumulacioNacional) {
        this.codiAcumulacioNacional = codiAcumulacioNacional;
    }

    public void setNomCurt(String nomCurt) {
        this.nomCurt = nomCurt;
    }

    public void setNomLlarg(String nomLlarg) {
        this.nomLlarg = nomLlarg;
    }
}