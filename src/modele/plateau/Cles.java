package modele.plateau;

public class Cles extends EntiteStatique{
    public Cles(Jeu _jeu) { super(_jeu); }

    @Override
    public boolean traversable() {
        return true;
    }

    @Override
    public boolean ramassables() {
        return true;
    }
}
