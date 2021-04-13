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
    private Orientation ori;
    private Inventaire inv;
    private Saut saut;

    public Inventaire getInventaire() {
        return inv;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Orientation getOri() {
        return ori;
    }

    public Heros(Jeu _jeu, int _x, int _y) {
        jeu = _jeu;
        x = _x;
        y = _y;
        saut = new Saut(this, _jeu);
        inv = new Inventaire(jeu);
        ori = new Orientation();
    }

    public void droite() {
        if (traversable(x + 1, y)) {
            x++;
            testDalleUnique(x, y);
            ramassable(x, y);
            isPorte(x, y);
        }
        else {
            if(ori.getOri() != ori.droite()) {
                ori.setOri(ori.droite());
            }
            else{
                ouvrirPorte(x + 1, y);
                saut.droite();
            }
        }
   }

    public void gauche() {
        if (traversable(x - 1, y)) {
            x--;
            testDalleUnique(x, y);
            ramassable(x, y);
            isPorte(x, y);
        }
        else {
            if(ori.getOri() != ori.gauche()) {
                ori.setOri(ori.gauche());
            }
            else {
                ouvrirPorte(x - 1, y);
                saut.gauche();
            }
        }
    }

    public void bas() {
        if (traversable(x, y + 1)) {
            y++;
            testDalleUnique(x, y);
            ramassable(x, y);
            isPorte(x, y);
        }
        else {
            if(ori.getOri() != ori.bas()) {
                ori.setOri(ori.bas());
            }
            else {
                ouvrirPorte(x, y + 1);
                saut.bas();
            }
        }
    }

    public void haut() {
        if (traversable(x, y - 1)) {
            y--;
            testDalleUnique(x, y);
            ramassable(x, y);
            isPorte(x, y);
        }
        else {
            if(ori.getOri() != ori.haut()) {
                ori.setOri(ori.haut());
            }
            else {
            ouvrirPorte(x, y - 1);
            saut.haut();
            }
        }
    }

    public boolean traversable(int x, int y) {
        if (x >0 && x < jeu.SIZE_X && y > 0 && y < jeu.SIZE_Y) {
            return jeu.getEntite(x, y).traversable();
        } else {
            return false;
        }
    }

    protected void ramassable(int x, int y) {
        EntiteStatique es = jeu.getEntite(x, y);
        if (es instanceof Cles) { // si on est sur une cle
            inv.addCles(jeu); // ajout de clef
            jeu.addEntiteStatique(new CaseNormale(jeu), x, y); // on change la clef en case normale
        }
        if(es instanceof Capsules){ // si on est sur une capsule
            inv.addCapsules(jeu); // ajout de capsule
            jeu.addEntiteStatique(new CaseNormale(jeu), x, y); // on change la capsule en case normale
        }
        if(es instanceof Coffre){ // si on est sur un coffre
            inv.addCoffre(jeu);
            jeu.addEntiteStatique(new CaseNormale(jeu), x, y); // on change le coffre en case normale
        }
    }

    private void isPorte(int x, int y) {
        EntiteStatique es = jeu.getEntite(x, y);
        if(es instanceof Porte) {
            jeu.prochaineSalle(((Porte)es).getSalle() );
        }
    }

    private void ouvrirPorte(int x, int y){
        EntiteStatique es = jeu.getEntite(x, y);
        if (es instanceof Porte) { // si on est sur une case vide
            if (inv.getNbCles() > 0) { // si on a la clef
                ((Porte) es).setVerrouillee(true); // ouvre la porte
                inv.removeCles(jeu); //cles --
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
}
