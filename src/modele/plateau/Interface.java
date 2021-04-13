package modele.plateau;

public class Interface {
    private int nbCles;
    private int nbCapsules;

    //Constructeur
    public Interface(Jeu jeu){
        this.nbCles = 0;
        this.nbCapsules = 0;
    }

    //Ajoute d'une clé dans l'interface
    public void addCles(Jeu jeu){
        jeu.addEntiteStatique(new Cles(jeu,0), 0+nbCles, 10);
        this.nbCles++;
    }

    //Ajoute d'une capsule dans l'interface
    public void addCapsules(Jeu jeu){
        jeu.addEntiteStatique(new Capsules(jeu,0), 10+nbCapsules, 10);
        this.nbCapsules++;
    }

    //Surprime une clé dans l'interface
    public void removeCles(Jeu jeu){
        jeu.addEntiteStatique(new CaseNormale(jeu), nbCles-1, 10); // on change la clef en case normale
        this.nbCles--;
    }

    //Surprime une capsule dans l'interface
    public void removeCapsule(Jeu jeu){
        if(nbCapsules >0) {
            jeu.addEntiteStatique(new CaseNormale(jeu), 10+nbCapsules-1, 10); // on change la clef en case normale
            this.nbCapsules--;
        }
    }
}
