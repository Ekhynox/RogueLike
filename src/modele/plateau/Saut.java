package modele.plateau;

public class Saut {
    private Heros heros;
    private Jeu jeu;

    //Constructeur
    public Saut(Heros heros, Jeu jeu){
        this.heros = heros;
        this.jeu = jeu;
    }

    //Si la case a droite du personnage est un trou et que la case suivante est traversable
    //on saut la case vide
    public void droite() {
        EntiteStatique es = jeu.getEntite(heros.getX()+1, heros.getY());
        if (es instanceof Vide) { // si on est sur une case vide
            if (heros.traversable(heros.getX()+2, heros.getY())) {
                heros.setXY(heros.getX()+2, heros.getY());
            }
        }
    }

    //Si la case a gauche du personnage est un trou et que la case suivante est traversable
    //on saut la case vide
    public void gauche() {
        EntiteStatique es = jeu.getEntite(heros.getX()-1, heros.getY());
        if (es instanceof Vide) { // si on est sur une case vide
            if (heros.traversable(heros.getX()-2, heros.getY())) {
                heros.setXY(heros.getX()-2, heros.getY());
            }
        }
    }

    //Si la case en bas du personnage est un trou et que la case suivante est traversable
    //on saut la case vide
    public void bas() {
        EntiteStatique es = jeu.getEntite(heros.getX(), heros.getY()+1);
        if (es instanceof Vide) { // si on est sur une case vide
            if (heros.traversable(heros.getX(), heros.getY()+2)) {
                heros.setXY(heros.getX(), heros.getY()+2);
            }
        }
    }

    //Si la case en haut du personnage est un trou et que la case suivante est traversable
    //on saut la case vide
    public void haut() {
        EntiteStatique es = jeu.getEntite(heros.getX(), heros.getY()-1);
        if (es instanceof Vide) { // si on est sur une case vide
            if (heros.traversable(heros.getX(), heros.getY()-2)) {
                heros.setXY(heros.getX(), heros.getY()-2);
            }
        }
    }
}