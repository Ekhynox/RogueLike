package modele.plateau;

public class Capsules extends EntiteStatique{
    private int salle;
    public Capsules(Jeu _jeu, int salle) { super(_jeu); this.salle = salle;}

    @Override
    public boolean traversable() {
        return true;
    }

    @Override
    public boolean ramassables() {
        return true;
    }

    public int getSalle() {
        return salle;
    }
}


