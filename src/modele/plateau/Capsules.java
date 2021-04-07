package modele.plateau;

public class Capsules extends EntiteStatique{
    public Capsules(Jeu _jeu) { super(_jeu); }

    @Override
    public boolean traversable() {
        return true;
    }

    @Override
    public boolean ramassables() {
        return true;
    }
}


