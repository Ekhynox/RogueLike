package modele.plateau;

public class Orientation{
    private int ori;

    public Orientation() {
        ori =0;
    }

    public int getOri() {
        return ori;
    }

    public void droite() {
        ori = 3;
        System.out.println("droite");
    }

    public void gauche() {
        ori = 2;
        System.out.println("gauche");
    }

    public void bas() {
        ori = 1;
        System.out.println("bas");
    }

    public void haut() {
        ori = 0;
        System.out.println("haut");
    }
}
