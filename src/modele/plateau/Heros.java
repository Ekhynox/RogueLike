/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.plateau;

/**
 * Héros du jeu
 */
public class Heros {
    private int x;
    private int y;
    private Jeu jeu;
    private Orientation ori = new Orientation();
    private Inventaire inv = new Inventaire();

    public Inventaire getInventaire() {
        return inv;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Orientation getOri() {
        return ori;
    }

    public Heros(Jeu _jeu, int _x, int _y) {
        jeu = _jeu;
        x = _x;
        y = _y;
    }

    public void droite() {
        ori.droite();
        if (traversable(x+1, y)) {
            x ++;
            testDalleUnique(x, y);
            ramassable(x,y);
        }
        else{
            ouvrirPorte(x + 1, y);
            EntiteStatique es = jeu.getEntite(x+1, y);
            if (es instanceof Vide) { // si on est sur une case vide
                if (traversable(x+2, y)) {
                    x = x + 2;
                }
            }
        }
   }

    public void gauche() {
        ori.gauche();
        if (traversable(x-1, y)) {
            x --;
            testDalleUnique(x, y);
            ramassable(x,y);
        }
        else{
            ouvrirPorte(x-1, y);
            EntiteStatique es = jeu.getEntite(x-1, y);
            if (es instanceof Vide) { // si on est sur une case vide
                if (traversable(x-2, y)) {
                    x = x - 2;
                }
            }
        }
    }

    public void bas() {
        ori.bas();
        if (traversable(x, y+1)) {
            y ++;
            testDalleUnique(x, y);
            ramassable(x,y);
        }
        else{
            ouvrirPorte(x, y+1);
            EntiteStatique es = jeu.getEntite(x, y+1);
            if (es instanceof Vide) { // si on est sur une case vide
                if (traversable(x, y+2)) {
                    y = y + 2;
                }
            }
        }
    }

    public void haut() {
        ori.haut();
        if (traversable(x, y-1)) {
            y --;
            testDalleUnique(x, y);
            ramassable(x,y);
        }
        else{
            ouvrirPorte(x, y-1);
            EntiteStatique es = jeu.getEntite(x, y-1);
            if (es instanceof Vide) { // si on est sur une case vide
                if (traversable(x, y-2)) {
                    y = y-2;
                }
            }
        }
    }

    private boolean traversable(int x, int y) {
        if (x >0 && x < jeu.SIZE_X && y > 0 && y < jeu.SIZE_Y) {
            return jeu.getEntite(x, y).traversable();
        } else {
            return false;
        }
    }

    private void ramassable(int x, int y) {
        EntiteStatique es = jeu.getEntite(x, y);
        if (es instanceof Cles) { // si on est sur une clef
            inv.addCles(); // ajout de clef
            jeu.addEntiteStatique(new CaseNormale(jeu), x, y); // on change la clef en case normale
        }
        if(es instanceof Capsules){ // si on est sur une capsule
            inv.addCapsules(); // ajout de clef
            jeu.addEntiteStatique(new CaseNormale(jeu), x, y); // on change la clef en case normale
        }
        if(es instanceof Coffre){ // si on est sur un coffre
            int random;
            do {
                random = (int)(Math.random() * 3 );
                if(random == 0) {
                    inv.addCles();
                }
                else if(random == 1) {
                    inv.addCapsules();
                }
                //System.out.println(random);
            }while(random == 2 ); // Si on a un coffre dans le coffre on recommence 
            jeu.addEntiteStatique(new CaseNormale(jeu), x, y); // on change la clef en case normale
        }
    }

    private void ouvrirPorte(int x, int y){
        EntiteStatique es = jeu.getEntite(x, y);
        if (es instanceof Porte) { // si on est sur une case vide
            if (inv.getNbCles() > 0) { // si on a la clef
                ((Porte) es).setVerrouillee(true); // ouvre la porte
                inv.removeCles(); //cles --
                //Code du changement de salle
            }
        }
    }

    private void testDalleUnique(int x, int y) {
        EntiteStatique es = jeu.getEntite(x, y);
        if(es instanceof DalleUnique) {
            ((DalleUnique) es).setTraversable(false);
        }
    }

    private void saut(int x, int y){


    }
}
