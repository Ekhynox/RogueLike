package modele.plateau;

public class Saut {
    private Jeu jeu;
    private Heros heros;

    public Saut(Jeu jeu){
        this.jeu = jeu;
    }

    public void SautHaut(int x,int  y){

    }

    public void Sautbas(int x,int  y){

    }

    public void sautDroite(Heros heros){
        EntiteStatique es = jeu.getEntite(heros.getX()+1, heros.getY());
        if (es instanceof Vide) { // si on est sur une case vide
            if (heros.traversable(heros.getX()+2, heros.getY())) {
                heros.setXY(heros.getX()+2, heros.getY());
            }
        }
    }

    public void SautGauche(int x,int  y){
        System.out.println("droite");
    }


}
