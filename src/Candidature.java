public class Candidature {
    private int id;
    private String nomCurt;

    public Candidature(int id, String nomCurt) {
        this.id = id;
        this.nomCurt = nomCurt;
    }

    public int getId() {
        return id;
    }

    public String getNomCurt() {
        return nomCurt;
    }
}