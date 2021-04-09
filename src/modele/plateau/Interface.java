package modele.plateau;

public class Interface {
    private int nbCles;
    private int nbCapsules;

    public Interface(Jeu jeu){
        this.nbCles = 0;
        this.nbCapsules = 0;
    }

    public void addCles(Jeu jeu){
        jeu.addEntiteStatique(new Cles(jeu), 0+nbCles, 10);
        this.nbCles++;
    }

    public void addCapsules(Jeu jeu){
        jeu.addEntiteStatique(new Capsules(jeu), 10+nbCapsules, 10);
        this.nbCapsules++;
    }

    public void removeCles(Jeu jeu){
        jeu.addEntiteStatique(new CaseNormale(jeu), nbCles-1, 10); // on change la clef en case normale
        this.nbCles--;
    }
    public void removeCapsule(Jeu jeu){
        jeu.addEntiteStatique(new CaseNormale(jeu), 10+nbCapsules-1, 10); // on change la clef en case normale
        this.nbCapsules--;
    }
}
