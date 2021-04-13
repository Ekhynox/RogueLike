package modele.plateau;

public class Coffre extends EntiteStatique{
    private int salle;
    public Coffre(Jeu _jeu, int salle) { super(_jeu); this.salle=salle; }

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

    public int getSalle() {
        return salle;
    }
}
