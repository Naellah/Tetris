package VueControleur;

import Modele.Couleur;
import Modele.Grille;
import Modele.Points;
import Modele.Niveau;

import javax.swing.*;
import java.awt.*;


import java.util.Observable;
import java.util.Observer;

class VueGrilleV2 extends JPanel implements Observer {

    public int TAILLE = 30;
    private final static int TAILLE2 = 30;
    private Grille modele;
    private Couleur col;
    private Points points = new Points();
    private Niveau level = new Niveau();
    //private Grille2 modele2;

    public VueGrilleV2(Grille _modele) {
        modele = _modele;
        modele.addObserver(this); // Ajoutez cette ligne pour vous assurer que la vue est notifiée des changements dans le modèle
        col = new Couleur(modele);
    }



    public void afficherScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        //affichage propre selon le nombre de chiffre du score
        if (points.getScore() < 10) {
            g.drawString(Integer.toString(points.getScore()), 100, 200);
        }
        if (points.getScore() >= 10 && points.getScore() < 100) {
            g.drawString(Integer.toString(points.getScore()), 90, 200);
        }
        if (points.getScore() >= 100 && points.getScore() < 1000) {
            g.drawString(Integer.toString(points.getScore()), 80, 200);
        }
        if (points.getScore() >= 1000 && points.getScore() < 10000) {
            g.drawString(Integer.toString(points.getScore()), 70, 200);
        }
        if (points.getScore() >= 10000 && points.getScore() < 100000) {
            g.drawString(Integer.toString(points.getScore()), 60, 200);
        }
    }

    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        // Chargement de l'image depuis le fichier
        ImageIcon backgroundImage = new ImageIcon("Data/Background.png"); // Assurez-vous de mettre le chemin correct
        ImageIcon ScoreImage = new ImageIcon("Data/Score.png");
        ImageIcon LevelImage = new ImageIcon("Data/Level.png");
        ImageIcon PauseImage = new ImageIcon("Data/Pause.png");
        
    
        // Dessin de l'image de fond
        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);

        g.drawImage(ScoreImage.getImage(), 5, 50, 200, 200, this);
        g.drawImage(LevelImage.getImage(), 5, 300, 200, 200, this);
       


        // Affichage du niveau
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString(Integer.toString(level.getNiveau()), 100, 400);
        
        // Affichage du score
        afficherScore(g);
        
        
        // Calcul de la position pour centrer la grille
        int xOffset = (getWidth() - modele.TAILLE_X * TAILLE) / 2;
        int yOffset = (getHeight() - (modele.TAILLE_Y-1) * TAILLE) / 2;

        g.drawImage(PauseImage.getImage(),xOffset+430 , yOffset+520, 80, 80, this);
    
        for (int i = 2; i < modele.TAILLE_X; i++) {
            for (int j = 0; j < modele.TAILLE_Y-1; j++) {
                g.setColor(col.CouleurtoColor(modele.getGrilleCouleurs()[i][j]));
                g.fillRect(xOffset + i * TAILLE, yOffset + j * TAILLE, TAILLE, TAILLE);
            }
        }
    
        for (int k = 0; k < 4; k++) {
            for (int l = 0; l < 4; l++) {
                if (modele.getPieceCourante().getCase(k, l) == 1) {
                    g.setColor(col.CouleurtoColor(modele.getPieceCourante().getCouleur()));
                    g.fillRect(xOffset + (modele.getPieceCourante().getx() + k) * TAILLE, yOffset + (modele.getPieceCourante().gety() + l) * TAILLE, TAILLE, TAILLE);
                }
                if (modele.getPieceCourante().getCase(k, l) == 3) {
                    g.setColor(Color.WHITE);
                    g.fillRect(xOffset + (modele.getPieceCourante().getx() + k) * TAILLE, yOffset + (modele.getPieceCourante().gety() + l) * TAILLE, TAILLE, TAILLE);
                }
            }
        }

        for (int i = 2; i < modele.TAILLE_X; i++) {
            for (int j = 0; j < modele.TAILLE_Y-1; j++) {
                g.setColor(Color.GRAY);
                g.drawRoundRect(xOffset + i * TAILLE, yOffset + j * TAILLE, TAILLE, TAILLE, 1, 1);
            }
        }


        //affichage de la grille pour les pieces suivantes
       for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                g.setColor(col.CouleurtoColor(modele.getGrilleCouleurs()[i][j]));
                g.fillRect((xOffset +400) + i * TAILLE2, (yOffset + 90) + j * TAILLE2, TAILLE2, 8*TAILLE2);
                g.setColor(Color.GRAY);
                g.drawRoundRect(xOffset +399, yOffset +89 , TAILLE2+120, TAILLE2+320, 5, 5);
            }
        }

        //affichage de la grille de piece suivante
        for (int k = 0; k < 4; k++) {
            for (int l = 0; l < 4; l++) {
                if (modele.getPieceSuivante().getCase(k, l) == 1) {
                    g.setColor(col.CouleurtoColor(modele.getPieceSuivante().getCouleur()));
                    g.fillRect((xOffset +400) +k * TAILLE2, (yOffset +90 )+(l+1) * TAILLE2, TAILLE2, TAILLE2);
                    g.setColor(Color.GRAY);
                    g.drawRoundRect((xOffset +400) + k * TAILLE2, (yOffset +90 ) + (l+1) * TAILLE2, TAILLE2, TAILLE2, 1, 1);
                }
            }
        }

        //affichage de la grille de piece suivante2
        
        for (int k = 0; k < 4; k++) {
            for (int l = 0; l < 4; l++) {
                if (modele.getPieceSuivante2().getCase(k, l) == 1) {
                    g.setColor(col.CouleurtoColor(modele.getPieceSuivante2().getCouleur()));
                    g.fillRect((xOffset +400) +k * TAILLE2, (yOffset +190 )+(l+1) * TAILLE2, TAILLE2, TAILLE2);
                    g.setColor(Color.GRAY);
                    g.drawRoundRect((xOffset +400) + k * TAILLE2, (yOffset +190 ) + (l+1) * TAILLE2, TAILLE2, TAILLE2, 1, 1);
                }
            }
        }

        //affichage de la grille de piece suivante3
        
        for (int k = 0; k < 4; k++) {
            for (int l = 0; l < 4; l++) {
                if (modele.getPieceSuivante3().getCase(k, l) == 1) {
                    g.setColor(col.CouleurtoColor(modele.getPieceSuivante3().getCouleur()));
                    g.fillRect((xOffset +400) +k * TAILLE2, (yOffset +290 )+(l+1) * TAILLE2, TAILLE2, TAILLE2);
                    g.setColor(Color.GRAY);
                    g.drawRoundRect((xOffset +400) + k * TAILLE2, (yOffset +290 ) + (l+1) * TAILLE2, TAILLE2, TAILLE2, 1, 1);
                }
            }
        }


        

    }
    

    @Override
    public void update(Observable o, Object arg) {
        
        repaint();
        
    }
}
