package modele.plateau;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class Salle {
    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 11;
    private Jeu jeu;
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

    public void salle(){

        for(int i=0;i<SIZE_Y;i++) {
            for(int j=0;j<SIZE_X;j++) {
                String objet = salle.substring(j + (i * SIZE_X), (j+1) + (i * SIZE_X)); 
                switch(objet) {
                    case "m":
                        jeu.addEntiteStatique(new Mur(jeu), j, i);
                    break;
                    case "f":
                        jeu.addEntiteStatique(new DalleUnique(jeu, false), j, i);
                    break;
                    case "d":
                        jeu.addEntiteStatique(new DalleUnique(jeu, true), j, i);
                    break;
                    case "p":
                        jeu.addEntiteStatique(new Porte(jeu, 1), j, i);
                    break;
                    case "h":
                        jeu.getHeros().setXY(j, i);
                    break;
                }
            }
        }
        jeu.addEntiteStatique(new Cles(jeu), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        jeu.addEntiteStatique(new Capsules(jeu), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        jeu.addEntiteStatique(new Coffre(jeu), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        jeu.addEntiteStatique(new Vide(jeu), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        jeu.addEntiteStatique(new Vide(jeu), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        jeu.addEntiteStatique(new Vide(jeu), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y-1; y++) {
                if (jeu.getGrille()[x][y] == null) {
                    jeu.addEntiteStatique(new CaseNormale(jeu), x, y);
                }
            }
        }


        /* // murs extérieurs horizontaux
        for (int x = 0; x < SIZE_X; x++) {
            jeu.addEntiteStatique(new Mur(jeu), x, 0);
            jeu.addEntiteStatique(new Mur(jeu), x, SIZE_Y-2);
        }

        // murs extérieurs verticaux
        for (int y = 1; y < SIZE_Y-2; y++) {
            jeu.addEntiteStatique(new Mur(jeu), 0, y);
            jeu.addEntiteStatique(new Mur(jeu), SIZE_X-1, y);
        }

        jeu.addEntiteStatique(new Mur(jeu), 2, 6);
        jeu.addEntiteStatique(new Mur(jeu), 3, 6);
        jeu.addEntiteStatique(new Porte(jeu, 1), 19, 5);
        jeu.addEntiteStatique(new Cles(jeu), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        jeu.addEntiteStatique(new Capsules(jeu), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        jeu.addEntiteStatique(new Coffre(jeu), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        jeu.addEntiteStatique(new DalleUnique(jeu), 5, 5);

        jeu.addEntiteStatique(new Vide(jeu), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        jeu.addEntiteStatique(new Vide(jeu), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        jeu.addEntiteStatique(new Vide(jeu), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1)); */
    }
}
