package VueControleur;

import javax.swing.*;
import java.awt.*;

public class VuePause extends JPanel {
    public VuePause() {
        // Configurer le panneau de pause
        setLayout(new BorderLayout());
        // Ajouter des composants, des étiquettes, des boutons, etc., selon vos besoins

        JLabel label = new JLabel("Jeu en pause");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        add(label, BorderLayout.CENTER);
        

        
    }

    public void drawPauseInterface(Graphics g) {
        // Dessinez l'interface de pause ici, en utilisant les images que vous avez mentionnées
        
        
        ImageIcon BackgroundPauseImage = new ImageIcon("Data/BackgroundPause.png");
        ImageIcon ReprendreImage = new ImageIcon("Data/Reprendre.png");
        ImageIcon QuitterImage = new ImageIcon("Data/Quitter.png");
        g.drawImage(BackgroundPauseImage.getImage(), 500, 500, getWidth(), getHeight(), this);
        // Dessinez les autres éléments...
    }
}