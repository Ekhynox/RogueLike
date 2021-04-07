package modele.plateau;

public class CaseNormale extends EntiteStatique {
    public CaseNormale(Jeu _jeu) { super(_jeu); }

    @Override
    public boolean traversable() {
        return true;
    }

    @Override
    public boolean ramassables() {
        return false;
    }

    public void ajout(){
        jeu.getHeros().getInventaire().addCles();
    }

}
