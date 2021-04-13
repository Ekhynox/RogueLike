package modele.plateau;

public class DalleUnique extends EntiteStatique {
    private boolean traversable = true;
    private int salle;
    public DalleUnique(Jeu _jeu,boolean feu, int salle) { super(_jeu); traversable = feu; this.salle =salle; }

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

    public int getSalle() {
        return salle;
    }

}
