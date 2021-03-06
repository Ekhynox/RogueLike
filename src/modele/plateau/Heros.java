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

    //Constructeur
    public Heros(Jeu _jeu, int _x, int _y) {
        jeu = _jeu;
        x = _x;
        y = _y;
        saut = new Saut(this, _jeu);
        inv = new Inventaire(jeu);
        ori = new Orientation();
    }

    //Retourne l'inventaire
    public Inventaire getInventaire() {
        return inv;
    }

    //Retourne la position en X du personnage
    public int getX() {
        return x;
    }

    //Retourne la position en Y du personnage
    public int getY() {
        return y;
    }

    //Modifier la position en X du personnage
    public void setX(int x) {
        this.x = x;
    }

    //Modifier la position en Y du personnage
    public void setY(int y) {
        this.y = y;
    }

    //Modifier la position en X,Y du personnage
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //retourne l'orientation
    public Orientation getOri() {
        return ori;
    }


    //Deplacement vers la droite
    //si la  case est traversable, déplacement sur la case, on regarde si un il y a un objet à ramasser, on regarde s'il y a une porte.
    //si la case n'est pas traversable et si le personnage n'est pas orienté face à l'obstacle on modifie l'orientation du personnage.
    //si le personnage est orienté face à l'obstacle, on regarde si c'est un trou ou une porte;
    public void droite() {
        if (traversable(x + 1, y)) {
            x++;
            testDalleUnique(x, y);
            ramassable(x,y);
        }
        else {
            if(ori.getOri() != ori.droite()) {
                ori.setOri(ori.droite());
            }
            else{
                porteOuverte(x+1, y);
                ouvrirPorte(x + 1, y);
                saut.droite();
            }
        }
   }
    //Deplacement vers la Gauche
    //si la  case est traversable, déplacement sur la case, on regarde si un il y a un objet à ramasser, on regarde s'il y a une porte.
    //si la case n'est pas traversable et si le personnage n'est pas orienté face à l'obstacle on modifie l'orientation du personnage.
    //si le personnage est orienté face à l'obstacle, on regarde si c'est un trou ou une porte;
    public void gauche() {
        if (traversable(x - 1, y)) {
            x--;
            testDalleUnique(x, y);
            ramassable(x,y);
        }
        else {
            if(ori.getOri() != ori.gauche()) {
                ori.setOri(ori.gauche());
            }
            else {
                porteOuverte(x -1, y);
                ouvrirPorte(x - 1, y);
                saut.gauche();
            }
        }
    }

    //Deplacement vers le bas
    //si la  case est traversable, déplacement sur la case, on regarde si un il y a un objet à ramasser, on regarde s'il y a une porte.
    //si la case n'est pas traversable et si le personnage n'est pas orienté face à l'obstacle on modifie l'orientation du personnage.
    //si le personnage est orienté face à l'obstacle, on regarde si c'est un trou ou une porte;
    public void bas() {
        if (traversable(x, y + 1)) {
            y++;
            testDalleUnique(x, y);
            ramassable(x,y);
        }
        else {
            if(ori.getOri() != ori.bas()) {
                ori.setOri(ori.bas());
            }
            else {
                porteOuverte(x, y+1);
                ouvrirPorte(x, y + 1);
                saut.bas();
            }
        }
    }

    //Deplacement vers la haut
    //si la  case est traversable, déplacement sur la case, on regarde si un il y a un objet à ramasser, on regarde s'il y a une porte.
    //si la case n'est pas traversable et si le personnage n'est pas orienté face à l'obstacle on modifie l'orientation du personnage.
    //si le personnage est orienté face à l'obstacle, on regarde si c'est un trou ou une porte;
    public void haut() {

        if (traversable(x, y - 1)) {
            y--;
            testDalleUnique(x, y);
            ramassable(x,y);
        }
        else {
            if(ori.getOri() != ori.haut()) {
                ori.setOri(ori.haut()); 
            }
            else {
            ouvrirPorte(x, y - 1);
            porteOuverte(x, y - 1);
            saut.haut();
            }
        }
    }

    //Retourne si la case est traversable
    public boolean traversable(int x, int y) {
        if (x >0 && x < jeu.SIZE_X && y > 0 && y < jeu.SIZE_Y) {
            return jeu.getEntite(x, y).traversable();
        } else {
            return false;
        }
    }

    //Retourne si la case est ramassable
    //En fonction de l'élément ramasable, incremente l'inventaire et acutualise l'interface
    //Puis on change l'élément en case normale
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
        if(es instanceof Coffre){
            inv.addCoffre(jeu);
            jeu.addEntiteStatique(new CaseNormale(jeu), x, y); // on change le coffre en case normale
            jeu.getTabSalle(((Coffre)es).getSalle()-1).supprimerObjet(x,y);
        }
    }

    private void porteOuverte(int x, int y) {
        EntiteStatique es = jeu.getEntite(x, y);
        if(es instanceof Porte) {
            if(((Porte)es).isVerrouillee()) {
                // Ici que pour les portes qui n'ont pas besoin de clef pour ceux qui on besoin de clef => fct ouvrirPorte
                if(!((Porte)es).isSerrure()) { 
                // Ce if est là pour différencier un porte qui amene à la prochaine salle ou une porte qui amene à la salle précedente
                // Les portes avec serrure sont ceux qui amene à la suite alors que celle qui n'ont plus de serrure nous permet de retourner dans le niveau precedent
                    jeu.getTabSalle(((Porte)es).getSalle()+1).modifierCoordPerso(x,y);
                }
                else {
                    jeu.getTabSalle(((Porte)es).getSalle()-1).modifierCoordPerso(x,y);
                    // On sauvegarde la position du personnage dans la salle où on est 
                }
                jeu.prochaineSalle(((Porte)es).getSalle()); // On recupere un variable qui donne la prochaine salle à afficher
                inv.setNbCapsules(jeu,2); // 2 capsule par salle (recharge)
                }
        }
    } 

    //Si la prochaine case est une porte
    //Et si on a la clé de la porte, déverrouille la porte
    //Puis enleve une clé de l'inventaire et met a jour l'interface
    private void ouvrirPorte(int x, int y){
        EntiteStatique es = jeu.getEntite(x, y);
        if (es instanceof Porte) {
            if (inv.getNbCles() > 0) {
                if(!((Porte)es).isVerrouillee()) {
                ((Porte) es).setVerrouillee(true);
                inv.removeCles(jeu);
                //Code du changement de salle
                jeu.getTabSalle(((Porte)es).getSalle()-1).modifierCoordPerso(x,y);
                // Sauvegarde des coordonnées du personnage dans la salle
                jeu.getTabSalle(((Porte)es).getSalle()-1).modifierCasePorte(x,y);
                // On change la porte en une porte qui n'a plus besoin de clef pour etre passe
                jeu.prochaineSalle(((Porte)es).getSalle());
                inv.setNbCapsules(jeu,2);
                inv.setNbCles(jeu,0);
                }
            }
        }
    }

    // Si on veux passer une dalle unique il faut qu'elle ne soit pas en feu
    // Si elle est traversable on la passe puis elle devient du feu qui n'est plus traversable
    private void testDalleUnique(int x, int y) {
        EntiteStatique es = jeu.getEntite(x, y);
        if(es instanceof DalleUnique) {
            ((DalleUnique) es).setTraversable(false);
            jeu.getTabSalle(((DalleUnique)es).getSalle()-1).modifierCaseUnique(x,y,false);
            // On modifie la case car elle devenue en feu
        }
    }
}
