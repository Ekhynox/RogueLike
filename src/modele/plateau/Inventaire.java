package modele.plateau;

public class Inventaire{
    private int nbCles;
    private int nbCapsules;
    private Jeu jeu;
    private Interface interf = new Interface(jeu);

    public Inventaire(Jeu jeu){
        nbCles = 0;
        nbCapsules = 2;
    }

    public Interface getInterface() {
        return interf;
    }

    public int getNbCapsules() {
        return nbCapsules;
    }

    public void setNbCapsules() {
        for(int i=0; i<nbCapsules; i++){
            interf.addCapsules(jeu);
        }
    }

    public int getNbCles() {
        return nbCles;
    }

    public void setNbCles(int nbCles) {
        this.nbCles = nbCles;
    }

    public void addCles(Jeu jeu){
        this.nbCles++;
        interf.addCles(jeu);
    }

    public void addCapsules(Jeu jeu){
        this.nbCapsules++;
        interf.addCapsules(jeu);
    }

    public void addCoffre(Jeu jeu){
        int random;
        do {
            random = (int)(Math.random() * 3 );
            if(random == 0) {
                addCles(jeu);
            }
            else if(random == 1) {
                addCapsules(jeu);
            }
            //System.out.println(random);
        }while(random == 2 ); // Si on a un coffre dans le coffre on recommence
    }

    public void removeCles(Jeu jeu){
        this.nbCles--;
        interf.removeCles(jeu);
    }

    public void removeCapsule(Jeu jeu){
        this.nbCapsules--;
        interf.removeCapsule(jeu);
    }
}