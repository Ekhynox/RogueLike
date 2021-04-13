package modele.plateau;

public class Orientation{
    
    public enum direction {
        Nord, Sud , Est, Ouest 
    }
    
    private direction ori;
    
    public Orientation() {
        ori = direction.Nord;
    }

    //retourne l'oritentation du personnage
    public direction getOri() {
        return ori;
    }

    //Modifier l'orientation du personnage
    public void setOri(direction dir){
        ori = dir;
    }

    //retourne l'orientation a droite
    public direction droite() {
        return direction.Est;
    }

    //retourne l'orientation a gauche
    public direction gauche() {
        return direction.Ouest;
    }

    //retourne l'orientation en bas
    public direction bas() {
        return direction.Sud;
    }

    //retourne l'orientation en haut
    public direction haut() {
        return direction.Nord;
    }
}
