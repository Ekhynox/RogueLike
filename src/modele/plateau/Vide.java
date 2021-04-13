package modele.plateau;

public class Vide extends EntiteStatique {
    public Vide(Jeu _jeu) { super(_jeu); }

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
