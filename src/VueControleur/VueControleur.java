package VueControleur;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;

import modele.plateau.*;

/** Cette classe a deux fonctions :
 *  (1) Vue : proposer une représentation graphique de l'application (cases graphiques, etc.)
 *  (2) Controleur : écouter les évènements clavier et déclencher le traitement adapté sur le modèle (flèches direction, etc.))
 *
 */
public class VueControleur extends JFrame implements Observer {
    private Jeu jeu; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)

    private int sizeX; // taille de la grille affichée
    private int sizeY;

    // icones affichées dans la grille
    private ImageIcon haut;
    private ImageIcon bas;
    private ImageIcon gauche;
    private ImageIcon droite;
    private ImageIcon icoHero;
    private ImageIcon icoCaseNormale;
    private ImageIcon icoMur;
    private ImageIcon icoColonne;
    private ImageIcon icoCles;
    private ImageIcon icoCapsules;
    private ImageIcon icoPorte;
    private ImageIcon icoCoffre;
    private ImageIcon icoVide;
    private ImageIcon icoCaseUnique;
    private ImageIcon icoFire;
    private JLabel[][] tabJLabel; // cases graphique (au moment du rafraichissement, chaque case va être associée à une icône, suivant ce qui est présent dans le modèle)


    public VueControleur(Jeu _jeu) {
        sizeX = jeu.SIZE_X;
        sizeY = _jeu.SIZE_Y;
        jeu = _jeu;

        chargerLesIcones();
        placerLesComposantsGraphiques();
        ajouterEcouteurClavier();
    }

    private void ajouterEcouteurClavier() {
        addKeyListener(new KeyAdapter() { // new KeyAdapter() { ... } est une instance de classe anonyme, il s'agit d'un objet qui correspond au controleur dans MVC
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {  // on regarde quelle touche a été pressée
                    case KeyEvent.VK_LEFT:
                        jeu.getHeros().gauche();
                        icoHero = gauche;
                        break;
                    case KeyEvent.VK_RIGHT:
                        jeu.getHeros().droite();
                        icoHero = droite;
                        break;
                    case KeyEvent.VK_DOWN:
                        jeu.getHeros().bas();
                        icoHero = bas;
                        break;
                    case KeyEvent.VK_UP:
                        jeu.getHeros().haut();
                        icoHero = haut;
                        break;
                    case KeyEvent.VK_C:
                        if(jeu.getHeros().getInventaire().getNbCapsules() > 0) {
                            EntiteStatique f;
                            switch (jeu.getHeros().getOri().getOri()) {
                                case 0:
                                    f = jeu.getEntite(jeu.getHeros().getX(), jeu.getHeros().getY() -1) ;
                                    if(f instanceof DalleUnique) {
                                        ((DalleUnique)f).setTraversable(true);
                                    }
                                break;
                                case 1:
                                    f = jeu.getEntite(jeu.getHeros().getX(), jeu.getHeros().getY() +1) ;
                                    if(f instanceof DalleUnique) {
                                        ((DalleUnique)f).setTraversable(true);
                                    }
                                break;
                                case 2:
                                    f = jeu.getEntite(jeu.getHeros().getX() -1, jeu.getHeros().getY()) ;
                                    if(f instanceof DalleUnique) {
                                        ((DalleUnique)f).setTraversable(true);
                                    }
                                break;
                                case 3:
                                    f = jeu.getEntite(jeu.getHeros().getX() +1, jeu.getHeros().getY()) ;
                                    if(f instanceof DalleUnique) {
                                        ((DalleUnique)f).setTraversable(true);
                                    }
                                break;
                            }
                            jeu.getHeros().getInventaire().removeCapsule(jeu);
                            jeu.getHeros().getInventaire().getInterface().removeCapsule(jeu);
                        }
                        break;
                }
            }
        });
    }

    private void chargerLesIcones() {
        icoHero = chargerIcone("Images/Haut.png");
        haut = chargerIcone("Images/Haut.png");
        bas = chargerIcone("Images/Bas.png");
        gauche = chargerIcone("Images/Gauche.png");
        droite = chargerIcone("Images/Droite.png");
        icoCaseNormale = chargerIcone("Images/Case.png");
        icoMur = chargerIcone("Images/Mur.png");
        icoVide = chargerIcone("Images/Vide.png");
        icoCles = chargerIcone("Images/Cles.png");
        icoCapsules = chargerIcone("Images/Capsules.png");
        icoPorte = chargerIcone("Images/Porte.png");
        icoCoffre = chargerIcone("Images/Coffre.png");
        icoCaseUnique = chargerIcone("Images/yellow_square.png");
        icoFire = chargerIcone("Images/Fire.png");
    }

    private ImageIcon chargerIcone(String urlIcone) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(urlIcone));
        } catch (IOException ex) {
            Logger.getLogger(VueControleur.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return new ImageIcon(image);
    }

    private void placerLesComposantsGraphiques() {
        setTitle("Roguelike");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer l'application à la fermeture de la fenêtre

        JComponent grilleJLabels = new JPanel(new GridLayout(sizeY, sizeX)); // grilleJLabels va contenir les cases graphiques et les positionner sous la forme d'une grille

        tabJLabel = new JLabel[sizeX][sizeY];

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                JLabel jlab = new JLabel();
                tabJLabel[x][y] = jlab; // on conserve les cases graphiques dans tabJLabel pour avoir un accès pratique à celles-ci (voir mettreAJourAffichage() )
                grilleJLabels.add(jlab);
            }
        }
        add(grilleJLabels);
    }

    /**
     * Il y a une grille du côté du modèle ( jeu.getGrille() ) et une grille du côté de la vue (tabJLabel)
     */
    private void mettreAJourAffichage() {

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
				EntiteStatique e = jeu.getEntite(x, y);
                if (e instanceof Mur) {
                    tabJLabel[x][y].setIcon(icoMur);
                } else if (e instanceof CaseNormale) {
                    tabJLabel[x][y].setIcon(icoCaseNormale);
                }else if (e instanceof Cles) {
                    tabJLabel[x][y].setIcon(icoCles);
                }
                else if (e instanceof Capsules) {
                    tabJLabel[x][y].setIcon(icoCapsules);
                }
                else if (e instanceof Porte) {
                    tabJLabel[x][y].setIcon(icoPorte);
                }
                else if (e instanceof Coffre) {
                    tabJLabel[x][y].setIcon(icoCoffre);
                }
                else if (e instanceof DalleUnique) {
                    if(((DalleUnique) e).traversable()) {
                        tabJLabel[x][y].setIcon(icoCaseUnique);
                    }
                    else {
                        tabJLabel[x][y].setIcon(icoFire);
                    }
                }
                else if (e instanceof Vide) {
                    tabJLabel[x][y].setIcon(icoVide);
                }
            }
        }
        tabJLabel[jeu.getHeros().getX()][jeu.getHeros().getY()].setIcon(icoHero);
    }

    @Override
    public void update(Observable o, Object arg) {
        mettreAJourAffichage();
        /*
        SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mettreAJourAffichage();
                    }
                }); 
        */
    }
}
