package modele.plateau;

public class Cles extends EntiteStatique{
    private int salle;
    public Cles(Jeu _jeu, int salle) { super(_jeu); this.salle = salle;}

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
