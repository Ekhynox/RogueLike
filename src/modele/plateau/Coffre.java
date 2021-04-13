package modele.plateau;

public class Coffre extends EntiteStatique{
    public Coffre(Jeu _jeu) { super(_jeu); }

    //retourne si l'élément est traversable
    @Override
    public boolean traversable() {
        return true;
    }


    //retourne si l'élément est ramassable
    @Override
    public boolean ramassables() {
        return true;
    }
}
