package modele.plateau;

public class Inventaire{
    private int nbCles;
    private int nbCapsules;

    public Inventaire(){
        nbCles = 0;
        nbCapsules = 0;
    }

    public int getNbCapsules() {
        return nbCapsules;
    }

    public void setNbCapsules(int nbCapsules) {
        this.nbCapsules = nbCapsules;
    }


    public int getNbCles() {
        return nbCles;
    }

    public void setNbCles(int nbCles) {
        this.nbCles = nbCles;
    }

    public void addCles(){
        this.nbCles++;
        System.out.println("nb cles = " + nbCles);
    }
    public void addCapsules(){
        this.nbCapsules++;
        System.out.println("nb capsules = " + nbCapsules);
    }
    public void removeCles(){
        this.nbCles--;
        System.out.println("nb cles = " + nbCles);
    }
    public void removeCapsule(){
        this.nbCapsules--;
        System.out.println("nb capsules = " + nbCapsules);
    }
}