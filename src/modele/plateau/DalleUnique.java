package modele.plateau;

public class DalleUnique extends EntiteStatique {
    private boolean traversable = true;
    public DalleUnique(Jeu _jeu,boolean feu) { super(_jeu); traversable = feu; }

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

    

}
