package modele.plateau;

public class DalleUnique extends EntiteStatique {
    private boolean traversable = true;
    public DalleUnique(Jeu _jeu,boolean feu) { super(_jeu); traversable = feu; }

    //Permet de rendre la case traversable
    public void setTraversable(boolean traversable) {
        this.traversable = traversable;
    }

    //Retourne l'etat de la case, si ell est traversable ou non
    public boolean isTraversable() {
        return traversable;
    }

    //retourne si l'élément est traversable
    @Override
    public boolean traversable() {
        return traversable;
    }

    //retourne si l'élément est ramassable
    @Override
    public boolean ramassables() {
        return false;
    }

    

}
