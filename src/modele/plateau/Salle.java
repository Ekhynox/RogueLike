package modele.plateau;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class Salle {
    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 11;
    private Jeu jeu;
    private boolean generer = false;
    String salle = new String();

    public Salle(Jeu jeu){
        this.jeu = jeu;

    }

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

    public void modifierCasePorte(int x, int y) {
        String tmp = salle;
        salle = new String();
        salle = tmp.substring(0, x+(y * SIZE_X)) + "D" + tmp.substring(x+(y * SIZE_X)+1);
    }

    public void supprimerObjet(int x, int y) {
        String tmp = salle;
        salle = new String();
        salle = tmp.substring(0, x+(y * SIZE_X)) + "_" + tmp.substring(x+(y * SIZE_X)+1);
    }

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

    public void salle(int idSalle){

        if(!generer) {
            for(int i =0; i< 3 ;i++) {
                int randomX = (int)(Math.random() * (SIZE_X-2)+1);
                int randomY = (int)(Math.random() * (SIZE_Y-3)+1);
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
            }
            generer = true;
        }

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
                        jeu.addEntiteStatique(new Porte(jeu, idSalle,false), j, i);
                    break;
                    case "D":
                        jeu.addEntiteStatique(new Porte(jeu, idSalle,true), j, i);
                    break;
                    case "P":
                        jeu.addEntiteStatique(new Porte(jeu, idSalle-2,true), j, i);
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

        
        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y-1; y++) {
                if (jeu.getGrille()[x][y] == null) {
                    jeu.addEntiteStatique(new CaseNormale(jeu), x, y);
                }
            }
        }
    }
}
