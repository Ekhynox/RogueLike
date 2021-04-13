package modele.plateau;

public class Porte extends EntiteStatique{
    private boolean Verrouillee;
    private int salle;
    private boolean serrure;

    //Constructeur
    public Porte(Jeu _jeu, int salle, boolean verouiller,boolean serrure) {
        super(_jeu);
        this.Verrouillee = verouiller;
        this.salle = salle;
        this.serrure = serrure;
    }

    //Permet d'ouvrir la porte Ouvre la porte
    public void setVerrouillee(boolean verrouillee) {
        this.Verrouillee = verrouillee;
    }

    public boolean isSerrure() {
        return serrure;
    }

    //Retourne l'etat de la porte, si ell est vérouillée ou non
    public boolean isVerrouillee() {
        return Verrouillee;
    }

    //retourne le numero de la salle suivante
    public int getSalle() {
        return salle;
    }

    //modifier le numero de la salle suivante
    public void setSalle(int salle) {
        this.salle = salle;
    }

    //retourne si l'élément est traversable
    @Override
    public boolean traversable() {
        return false;
    }

    //retourne si l'élément est ramassable
    @Override
    public boolean ramassables() {
        return true;
    }
}
