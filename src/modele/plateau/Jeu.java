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

    private EntiteStatique[][] grilleEntitesStatiques = new EntiteStatique[SIZE_X][SIZE_Y];

    public Jeu() {
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

	public EntiteStatique getEntite(int x, int y) {
		if (x < 0 || x >= SIZE_X || y < 0 || y >= SIZE_Y) {
			// L'entité demandée est en-dehors de la grille
			return null;
		}
		return grilleEntitesStatiques[x][y];
	}

    private void initialisationDesEntites() {
        heros = new Heros(this, 4, 4);

        // murs extérieurs horizontaux
        for (int x = 0; x < SIZE_X; x++) {
            addEntiteStatique(new Mur(this), x, 0);
            addEntiteStatique(new Mur(this), x, SIZE_Y-2);
        }

        // murs extérieurs verticaux
        for (int y = 1; y < SIZE_Y-2; y++) {
            addEntiteStatique(new Mur(this), 0, y);
            addEntiteStatique(new Mur(this), SIZE_X-1, y);
        }

        addEntiteStatique(new Mur(this), 2, 6);
        addEntiteStatique(new Mur(this), 3, 6);
        addEntiteStatique(new Porte(this), 19, 5);
        addEntiteStatique(new Cles(this), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        addEntiteStatique(new Capsules(this), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        addEntiteStatique(new Coffre(this), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        addEntiteStatique(new DalleUnique(this), 5, 5);

        addEntiteStatique(new Vide(this), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        addEntiteStatique(new Vide(this), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        addEntiteStatique(new Vide(this), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));

        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y-1; y++) {
                if (grilleEntitesStatiques[x][y] == null) {
                    grilleEntitesStatiques[x][y] = new CaseNormale(this);
                }
            }
        }
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
