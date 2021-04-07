package modele.plateau;

public class Vide extends EntiteStatique {
    public Vide(Jeu _jeu) { super(_jeu); }

    @Override
    public boolean traversable() {
        return false;
    }

    @Override
    public boolean ramassables() {
        return false;
    }
}
