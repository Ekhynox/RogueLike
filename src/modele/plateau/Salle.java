package modele.plateau;

public class Salle {
    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 11;
    private Jeu jeu;


    public Salle(Jeu jeu){
        this.jeu = jeu;

    }

    public void salle(){
        // murs extérieurs horizontaux
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
        jeu.addEntiteStatique(new Vide(jeu), (int)(Math.random() * (SIZE_X-2)+1), (int)(Math.random() * (SIZE_Y-3)+1));
    }
}
