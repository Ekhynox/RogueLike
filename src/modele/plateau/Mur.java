package modele.plateau;

public class Mur extends EntiteStatique {
    public Mur(Jeu _jeu) { super(_jeu); }

    //retourne si l'élément est traversable
    @Override
    public boolean traversable() {
        return false;
    }

    //retourne si l'élément est ramassable
    @Override
    public boolean ramassables() {
        return false;
    }
}
