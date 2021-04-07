package modele.plateau;

public class Porte extends EntiteStatique{
    private boolean Verrouillee = false;
    public Porte(Jeu _jeu) { super(_jeu); }

    public void setVerrouillee(boolean verrouillee) {
        this.Verrouillee = verrouillee;
    }

    public boolean isVerrouillee() {
        return Verrouillee;
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
