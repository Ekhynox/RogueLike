package modele.plateau;

public class Saut {
    private Heros heros;
    private Jeu jeu;

    public Saut(Heros heros, Jeu jeu){
        this.heros = heros;
        this.jeu = jeu;
    }

    public void droite() {
        EntiteStatique es = jeu.getEntite(heros.getX()+1, heros.getY());
        if (es instanceof Vide) { // si on est sur une case vide
            if (heros.traversable(heros.getX()+2, heros.getY())) {
                heros.setXY(heros.getX()+2, heros.getY());
            }
        }
    }

    public void gauche() {
        EntiteStatique es = jeu.getEntite(heros.getX()-1, heros.getY());
        if (es instanceof Vide) { // si on est sur une case vide
            if (heros.traversable(heros.getX()-2, heros.getY())) {
                heros.setXY(heros.getX()-2, heros.getY());
            }
        }
    }

    public void bas() {
        EntiteStatique es = jeu.getEntite(heros.getX(), heros.getY()+1);
        if (es instanceof Vide) { // si on est sur une case vide
            if (heros.traversable(heros.getX(), heros.getY()+2)) {
                heros.setXY(heros.getX(), heros.getY()+2);
            }
        }
    }

    public void haut() {
        EntiteStatique es = jeu.getEntite(heros.getX(), heros.getY()-1);
        if (es instanceof Vide) { // si on est sur une case vide
            if (heros.traversable(heros.getX(), heros.getY()-2)) {
                heros.setXY(heros.getX(), heros.getY()-2);
            }
        }
    }
}