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

    public void setOri(direction dir){
        ori = dir;
    }

    public direction droite() {
        return direction.Est;
    }

    public direction gauche() {
        return direction.Ouest;
    }

    public direction bas() {
        return direction.Sud;
    }

    public direction haut() {
        return direction.Nord;
    }
}
