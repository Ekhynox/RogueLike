package modele.plateau;

public class DalleUnique extends EntiteStatique {
    private boolean traversable = true;
    private int salle;
    public DalleUnique(Jeu _jeu,boolean feu, int salle) { super(_jeu); traversable = feu; this.salle =salle; }

    public void setTraversable(boolean traversable) {
        this.traversable = traversable;
    }

    public boolean isTraversable() {
        return traversable;
    }

    @Override
    public boolean traversable() {
        return traversable;
    }

    @Override
    public boolean ramassables() {
        return false;
    }

    public int getSalle() {
        return salle;
    }

}
