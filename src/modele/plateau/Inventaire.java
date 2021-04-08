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

    public void addCles(Jeu jeu){
        jeu.addEntiteStatique(new Cles(jeu), 0+nbCles, 10);
        this.nbCles++;
        System.out.println("nb cles = " + nbCles);
    }

    public void addCapsules(Jeu jeu){
        jeu.addEntiteStatique(new Capsules(jeu), 10+nbCapsules, 10);
        this.nbCapsules++;
        System.out.println("nb capsules = " + nbCapsules);
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
        jeu.addEntiteStatique(new CaseNormale(jeu), nbCles-1, 10); // on change la clef en case normale
        this.nbCles--;
        System.out.println("nb cles = " + nbCles);
    }
    public void removeCapsule(Jeu jeu){
        jeu.addEntiteStatique(new CaseNormale(jeu), 10+nbCapsules-1, 10); // on change la clef en case normale
        this.nbCapsules--;
        System.out.println("nb capsules = " + nbCapsules);
    }
}