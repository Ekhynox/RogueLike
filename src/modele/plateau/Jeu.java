/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.plateau;

import java.util.Observable;
//import java.lang.Math; //importing Math class in Java

public class Jeu extends Observable implements Runnable {

    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 11;

    private int pause = 200; // période de rafraichissement

    private Heros heros;
    private Orientation orientation;
    private Salle [] tabSalle;

    private EntiteStatique[][] grilleEntitesStatiques = new EntiteStatique[SIZE_X][SIZE_Y];

    public Jeu() {
        tabSalle = new Salle[3];
        initialisationDesEntites();
    }

    public Heros getHeros() {
        return heros;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public EntiteStatique[][] getGrille() {
        return grilleEntitesStatiques;
    }

    public Salle getTabSalle(int i) {
        return tabSalle[i];
    }

    public Jeu getJeu(){return this;}

	public EntiteStatique getEntite(int x, int y) {
		if (x < 0 || x >= SIZE_X || y < 0 || y >= SIZE_Y) {
			// L'entité demandée est en-dehors de la grille
			return null;
		}
		return grilleEntitesStatiques[x][y];
	}

    private void initialisationDesEntites() {
        heros = new Heros(this, 4, 4);
        tabSalle[0] = new Salle(this);
        tabSalle[1] = new Salle(this);
        tabSalle[2] = new Salle(this);
        tabSalle[0].lireFichier("salle1.txt");
        tabSalle[1].lireFichier("salle2.txt");
        tabSalle[2].lireFichier("salle3.txt");
        tabSalle[0].salle(1);
        
    }

    public void prochaineSalle(int i) {
        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y-1; y++) {
                    addEntiteStatique(new CaseNormale(this), x, y);
            }
        }
        tabSalle[i].salle(i+1);
    }

    public void start() {
        new Thread(this).start();
    }

    public void run() {
        while(true) {
            setChanged();
            notifyObservers();
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void addEntiteStatique(EntiteStatique e, int x, int y) {
        grilleEntitesStatiques[x][y] = e;
    }

}
