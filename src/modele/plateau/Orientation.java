package modele.plateau;

public class Orientation{
    
    public enum direction {
        Nord, Sud , Est, Ouest 
    }
    
    private direction ori;
    
    public Orientation() {
        ori = direction.Nord;
    }

    public direction getOri() {
        return ori;
    }

    public void droite() {
        ori = direction.Est;
        System.out.println("droite");
    }

    public void gauche() {
        ori = direction.Ouest;
        System.out.println("gauche");
    }

    public void bas() {
        ori = direction.Sud;
        System.out.println("bas");
    }

    public void haut() {
        ori = direction.Nord;
        System.out.println("haut");
    }
}
