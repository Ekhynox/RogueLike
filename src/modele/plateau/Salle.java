package modele.plateau;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.SizeSequence;


public class Salle {
    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 11;
    private Jeu jeu;
    private boolean generer = false;
    String salle = new String();

    public Salle(Jeu jeu){
        this.jeu = jeu;

    }

    // Fonction qui lit les fichiers qui correspond chacun à un niveau
    public void lireFichier(String fichier) {

        try {
            BufferedReader a = new BufferedReader(new FileReader("Donjons/" + fichier));
            int c = a.read();
            while(c != -1) {
                if(((char) c) != '\n'  && ((char) c) != ' '  ) {
                salle = salle + ((char) c);
                }
                c = a.read();
            }
            a.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    //FONCTION MODIFICATION :
    // Toutes les fonctions modifier permet la sauvegarde des objets qui peuvent changer, bouger ou disparaitre
    // Cela permet qui si le joueur quitte le niveau, si il revient il y aura toujours les objets qu'il n'a pas rammasser 
    // ou a l'inverse que les objets ramasables ne soit plus la car le joueur les avaient déjà ramasser 

    // les portes qui avait besoin d'une clef pour etre traverser deviennent des portes avec serrure toujours mais qui n'ont plus besoin de clef pour etre traverser
    public void modifierCasePorte(int x, int y) {
        String tmp = salle;
        salle = new String();
        salle = tmp.substring(0, x+(y * SIZE_X)) + "D" + tmp.substring(x+(y * SIZE_X)+1);
    }

    // Tout les objets une fois rammaser n'existe plus donc on l'enleve du string "salle"
    public void supprimerObjet(int x, int y) {
        String tmp = salle;
        salle = new String();
        salle = tmp.substring(0, x+(y * SIZE_X)) + "_" + tmp.substring(x+(y * SIZE_X)+1);
    }

    // Si une dalle unique change d'état feu=>dalle ou dalle=>feu
    // On modifie la lettre dans le string "salle" de la case unique qui à changer d'état
    public void modifierCaseUnique(int x, int y, boolean feu) {
        String tmp = salle;
        salle = new String();
        if(feu) {
            salle = tmp.substring(0, x+(y * SIZE_X)) + "d" + tmp.substring(x+(y * SIZE_X)+1);
        }
        else {
            salle = tmp.substring(0, x+(y * SIZE_X)) + "f" + tmp.substring(x+(y * SIZE_X)+1);
        }
    }

    // Quand on quitte un niveau donc on passe par un porte soit pour aller dans le niveau précedent soit pour aller dans le niveau suivant
    // On sauvegarde la position du personnage dans le niveau afin que si le joueur revient dans ce niveau sont personnage sera a côté de la porte 
    // avec laquell il avait quitter ce niveau
    public void modifierCoordPerso(int x, int y) {
        int xPerso = jeu.getHeros().getX();
        int yPerso = jeu.getHeros().getY();
        int xAncienPerso;
        int yAncienPerso;
        for(int i=0;i<SIZE_Y -1;i++) {
            for(int j=0;j<SIZE_X;j++) {
                if(salle.subSequence(j + i*SIZE_X , j + i*SIZE_X +1).equals("h")) {
                    xAncienPerso = j;
                    yAncienPerso = i;
                    String tmp = salle;
                    salle = tmp.substring(0,xAncienPerso + yAncienPerso*SIZE_X) + "_" + tmp.substring(xAncienPerso + yAncienPerso*SIZE_X+1);
                }
            }
        }
            String tmp = salle;
            salle = tmp.substring(0, xPerso + yPerso*SIZE_X) + "h" + tmp.substring(xPerso + yPerso*SIZE_X +1);
    }


    // Explication de comment sont stockées les niveaux :
    // Chaque classe Salle instancié dans jeu dans tableaux de salle, sont un niveau = 1 fichier
    // Chaque classe salle contient donc un string salle qui est composer de lettre qui correspond chacun à un élément du jeu
    // LEGENDE DES LETTRES :
    // m : représente un mur
    // f : une case unique qui est déjà en feu
    // d : une dalle unique qui n'est pas encore en feu donc traversable
    // p : une porte qui a une serrure fermer donc on a besoin d'une clef pour la traverser
    // D : toute porte "p" traverser avec un clef devienne des porte "D" donc des portes avec serrures mais la serrure est ouvert donc plus besoin de clef
    // P : représente un porte sans serrure donc tout le temps traversable
    // h : représente on commencera le personnage dans ce niveau (position du personnage)
    // K : représente les clefs
    // C : représente les coffres
    // c : représente les capsules 
    // t : représente les troues
    // _ : représente qu'il y a rien ici donc une case unique
    public void salle(int idSalle){

        // Si la salle n'a jamais été charger à l'écran alors on genere aléatoirement un coffre, une capsule et une clef dans des emplacement libre
        if(!generer) {
            for(int i =0; i< 3 ;i++) {
                int randomX;
                int randomY;
                // tant que l'ont pas une case disponible c'et à dire "_" on rechercher un autre random des coordonnées
                do {
                    randomX = (int)(Math.random() * (SIZE_X-2)+1);
                    randomY = (int)(Math.random() * (SIZE_Y-3)+1);
                }while(!salle.substring(randomX + (randomY * SIZE_X), randomX + (randomY * SIZE_X)+1 ).equals("_"));
                String tmp = salle;
                salle = new String();
                if(i == 0) {
                    salle = tmp.substring(0, randomX+(randomY * SIZE_X)) + "K" + tmp.substring(randomX+(randomY * SIZE_X)+1);
                }
                else if(i == 1) {
                    salle = tmp.substring(0, randomX+(randomY * SIZE_X)) + "c" + tmp.substring(randomX+(randomY * SIZE_X)+1);
                }
                else {
                    salle = tmp.substring(0, randomX+(randomY * SIZE_X)) + "C" + tmp.substring(randomX+(randomY * SIZE_X)+1);
                }
                // On met dans le string avec des coordonnées génerer aléatoirement les items avec génération aléatoire
            }
            generer = true;
            // Permet que les items aléatoire ne se regenere pas si on les déjà generer une fois
        }

        // On parcous toute la grille de jeu afin de charger et afficher les objets du "string" salle qui contient le niveau
        // La règles des lettres est au dessus de la définition de la fonction "salle"
        for(int i=0;i<SIZE_Y;i++) {
            for(int j=0;j<SIZE_X;j++) {
                String objet = salle.substring(j + (i * SIZE_X), (j+1) + (i * SIZE_X)); 
                switch(objet) {
                    case "m":
                        jeu.addEntiteStatique(new Mur(jeu), j, i);
                    break;
                    case "f":
                        jeu.addEntiteStatique(new DalleUnique(jeu, false, idSalle), j, i);
                    break;
                    case "d":
                        jeu.addEntiteStatique(new DalleUnique(jeu, true, idSalle), j, i);
                    break;
                    case "p":
                        jeu.addEntiteStatique(new Porte(jeu, idSalle,false,true), j, i);
                    break;
                    case "D":
                        jeu.addEntiteStatique(new Porte(jeu, idSalle,true,true), j, i);
                    break;
                    case "P":
                        jeu.addEntiteStatique(new Porte(jeu, idSalle-2,true,false), j, i);
                    break;
                    case "h":
                        jeu.getHeros().setXY(j, i);
                    break;
                    case "K":
                        jeu.addEntiteStatique(new Cles(jeu,idSalle),j,i );
                    break;
                    case "c":
                        jeu.addEntiteStatique(new Capsules(jeu,idSalle), j, i);
                    break;
                    case "C":
                        jeu.addEntiteStatique(new Coffre(jeu,idSalle), j, i);
                    break;
                    case "t":
                        jeu.addEntiteStatique(new Vide(jeu), j, i);
                    break;
                }
            }
        }

        // Dès que l'on a charger tout objets du niveau hors case unique, on rajoute les cases unique où il n'y a rien
        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y-1; y++) {
                if (jeu.getGrille()[x][y] == null) {
                    jeu.addEntiteStatique(new CaseNormale(jeu), x, y);
                }
            }
        }
    }
}
