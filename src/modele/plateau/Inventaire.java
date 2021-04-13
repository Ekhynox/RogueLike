package modele.plateau;

public class Inventaire{
    private int nbCles;
    private int nbCapsules;
    private Jeu jeu;
    private Interface interf = new Interface(jeu);

    //Constructeur
    public Inventaire(Jeu jeu){
        nbCles = 0;
        nbCapsules = 2;
    }

    //retourne l'inventaire
    public Interface getInterface() {
        return interf;
    }

    //retoune le nombre de capsule
    public int getNbCapsules() {
        return nbCapsules;
    }

    //set le nombre de capsule
    public void setNbCapsules() {
        for(int i=0; i<nbCapsules; i++){
            interf.addCapsules(jeu);
        }
    }

    //retourne le nombre de cles
    public int getNbCles() {
        return nbCles;
    }

    //set le nombre de cles
    public void setNbCles(int nbCles) {
        this.nbCles = nbCles;
    }

    //Ajoute une cle dans l'inventaire et demande l'affichage dans l'interface;
    public void addCles(Jeu jeu){
        this.nbCles++;
        interf.addCles(jeu);
    }

    //Ajoute une capsule dans l'inventaire et demande l'affichage dans l'interface;
    public void addCapsules(Jeu jeu){
        this.nbCapsules++;
        interf.addCapsules(jeu);
    }

    //Ajoute une capsule ou une cle
    //Si coffre dans un coffre recommence
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

    //Supprime une cle dans l'inventaire;
    public void removeCles(Jeu jeu){
        this.nbCles--;
        interf.removeCles(jeu);
    }

    //Supprime une capsule dans l'inventaire
    public void removeCapsule(Jeu jeu){
        this.nbCapsules--;
        interf.removeCapsule(jeu);
    }
}