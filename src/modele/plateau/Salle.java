package modele.plateau;

public class Salle {
    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 11;
    private Jeu jeu;
    private Heros heros;
    private EntiteStatique[][] grilleEntitesStatiques = new EntiteStatique[SIZE_X][SIZE_Y];

    public Salle(Jeu jeu){
        this.jeu = jeu;
    }

    private void salle1() {
        heros = new Heros(jeu, 4, 4);

        // murs extérieurs horizontaux
        for (int x = 0; x < SIZE_X; x++) {
            addEntiteStatique(new Mur(jeu), x, 0);
            addEntiteStatique(new Mur(jeu), x, SIZE_Y-2);
        }

        // murs extérieurs verticaux
        for (int y = 1; y < SIZE_Y-2; y++) {
            addEntiteStatique(new Mur(jeu), 0, y);
            addEntiteStatique(new Mur(jeu), SIZE_X-1, y);
        }

        addEntiteStatique(new Mur(jeu), 2, 6);
        addEntiteStatique(new Mur(jeu), 3, 6);
        addEntiteStatique(new Porte(jeu, 1), 19, 5);
        addEntiteStatique(new Porte(jeu, 1), 15, 5);
        addEntiteStatique(new Porte(jeu, 1), 10, 5);
        addEntiteStatique(new Cles(jeu), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        addEntiteStatique(new Capsules(jeu), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        addEntiteStatique(new Coffre(jeu), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        addEntiteStatique(new DalleUnique(jeu), 5, 5);

        addEntiteStatique(new Vide(jeu), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        addEntiteStatique(new Vide(jeu), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
        addEntiteStatique(new Vide(jeu), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));

        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y-1; y++) {
                if (grilleEntitesStatiques[x][y] == null) {
                    grilleEntitesStatiques[x][y] = new CaseNormale(jeu);
                }
            }
        }
        addEntiteStatique(new Cles(jeu), 0, 10);
        addEntiteStatique(new Capsules(jeu), 4, 10);
    }
    protected void addEntiteStatique(EntiteStatique e, int x, int y) {
        grilleEntitesStatiques[x][y] = e;
    }
}
