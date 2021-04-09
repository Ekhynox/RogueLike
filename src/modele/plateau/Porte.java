package modele.plateau;

public class Porte extends EntiteStatique{
    private boolean Verrouillee;
    private int salle;

    public Porte(Jeu _jeu, int salle, boolean verouiller) {
        super(_jeu);
        this.Verrouillee = verouiller;
        this.salle = salle;
    }

    public void setVerrouillee(boolean verrouillee) {
        this.Verrouillee = verrouillee;
    }

    public boolean isVerrouillee() {
        return Verrouillee;
    }

    public int getSalle() {
        return salle;
    }

    public void setSalle(int salle) {
        this.salle = salle;
    }

    @Override
    public boolean traversable() {
        return isVerrouillee();
    }

    @Override
    public boolean ramassables() {
        return true;
    }
}
