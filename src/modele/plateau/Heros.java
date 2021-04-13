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
    private Inventaire inv;
    private Saut saut = new Saut(jeu);

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
        inv = new Inventaire(jeu);
    }

    public void droite() {
        ori.droite();
        if (traversable(x+1, y)) {
            x ++;
            testDalleUnique(x, y);
            ramassable(x,y);
            porteOuverte(x, y);
        }
        else{
            ouvrirPorte(x + 1, y);
            saut.sautDroite(this);
        }
   }

    public void gauche() {
        ori.gauche();
        if (traversable(x-1, y)) {
            x --;
            testDalleUnique(x, y);
            ramassable(x,y);
            porteOuverte(x, y);
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
            porteOuverte(x, y);
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
            porteOuverte(x, y);
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
            jeu.getTabSalle(((Cles)es).getSalle()-1).supprimerObjet(x,y);
        }
        if(es instanceof Capsules){ // si on est sur une capsule
            inv.addCapsules(jeu); // ajout de capsule
            jeu.addEntiteStatique(new CaseNormale(jeu), x, y); // on change la capsule en case normale
            jeu.getTabSalle(((Capsules)es).getSalle()-1).supprimerObjet(x,y);
        }
        if(es instanceof Coffre){ // si on est sur un coffre
            inv.addCoffre(jeu);
            jeu.addEntiteStatique(new CaseNormale(jeu), x, y); // on change le coffre en case normale
            jeu.getTabSalle(((Coffre)es).getSalle()-1).supprimerObjet(x,y);
        }
    }

    private void porteOuverte(int x, int y) {
        EntiteStatique es = jeu.getEntite(x, y);
        if(es instanceof Porte) {
            jeu.prochaineSalle(((Porte)es).getSalle());
            inv.setNbCapsules(jeu,2);
        }
    } 

    private void ouvrirPorte(int x, int y){
        EntiteStatique es = jeu.getEntite(x, y);
        if (es instanceof Porte) { // si on est sur une case vide
            if (inv.getNbCles() > 0) { // si on a la clef
                ((Porte) es).setVerrouillee(true); // ouvre la porte
                inv.removeCles(jeu); //cles --
                //Code du changement de salle
                jeu.prochaineSalle(((Porte)es).getSalle());
                inv.setNbCapsules(jeu,2);
                jeu.getTabSalle(((Porte)es).getSalle()-1).modifierCasePorte(x,y);
            }
        }
    }

    private void testDalleUnique(int x, int y) {
        EntiteStatique es = jeu.getEntite(x, y);
        if(es instanceof DalleUnique) {
            ((DalleUnique) es).setTraversable(false);
            jeu.getTabSalle(((DalleUnique)es).getSalle()-1).modifierCaseUnique(x,y,false);
        }
    }
}
