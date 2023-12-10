package VueControleur;

import Modele.Grille;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VC extends JFrame implements Observer {

    JTextField jt = new JTextField("");
    JButton jb = new JButton("Tetris");
    Grille modele;
    
    Observer vueGrille;
    private Executor ex =  Executors.newSingleThreadExecutor();
    private VuePause vuePause;

    public VC(Grille _modele) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        modele = _modele;
        setSize(800, 700);
        JPanel jp = new JPanel(new BorderLayout());
        jp.add(jt, BorderLayout.NORTH);
        jp.add(jb, BorderLayout.SOUTH);

        //vueGrille = new VueGrilleV1(modele); // composants swing, saccades
        vueGrille = new VueGrilleV2(modele); // composant AWT dédié

        jp.add((JPanel)vueGrille, BorderLayout.CENTER);
        setContentPane(jp);

        int TAILLE = 30;
        int xOffset = (getWidth() - modele.TAILLE_X * TAILLE) / 2;
        int yOffset = (getHeight() - (modele.TAILLE_Y-1) * TAILLE) / 2;

       
       
        
        jb.addActionListener(new ActionListener() { //évènement bouton : object contrôleur qui réceptionne
            @Override
            public void actionPerformed(ActionEvent e) {
                ex.execute(new Runnable() {
                    @Override
                    public void run() {
                        
                    }
                });
            }
        });

        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                switch (e.getID()) {
                    case KeyEvent.KEY_PRESSED:
                        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                            modele.action_gauche();
                        }
                        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                            modele.action_droite();
                        }

                        if (e.getKeyCode() == KeyEvent.VK_UP) {
                            modele.action_rotation();
                        }
                        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                            modele.action_descente();
                        }
                        break;
                    case KeyEvent.KEY_RELEASED:
                        break;
                    case KeyEvent.KEY_TYPED:
                        break;
                }
                return false;
            }
        });

        ((VueGrilleV2) vueGrille).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Si le clic de souris est sur l'image du bouton pause
                // utilise  les coordonnées de g.drawImage(PauseImage.getImage(),xOffset+430 , yOffset+520, 80, 80, this);
                if (e.getX() >= xOffset+430 && e.getX() <= xOffset+430+80 && e.getY() >= yOffset+520 && e.getY() <= yOffset+520+80) {
                    System.out.println("Pause");
                    if (modele.getOrdonnanceur().isPaused()) {
                        modele.resumeOrdonnanceur();
                    } else {
                        modele.pauseOrdonnanceur();
                    }
                }
            }
        });

        
        


    }

    static long lastTime = System.currentTimeMillis();

    

    @Override
    public void update(Observable o, Object arg) {
        SwingUtilities.invokeLater(() -> {
            vueGrille.update(o, arg);

            jt.setText("Elapsed time : " + (System.currentTimeMillis() - lastTime) + "ms - x = " + modele.getPieceCourante().getx() + " y = " + modele.getPieceCourante().gety());
            lastTime = System.currentTimeMillis();

            if (modele.getOrdonnanceur().isPaused()) {
                vuePause.drawPauseInterface(getGraphics());
                
                // Assurez-vous que le dessin est visible en appelant repaint
                repaint();
            }
        });
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    Grille m = new Grille();
                    VC vc = new VC(m);
                    vc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    m.addObserver(vc);
                    vc.setVisible(true);

                }
            }
        );
    }
}
