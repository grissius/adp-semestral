package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.controller.FrontController;
import cz.fit.dpo.mvcshooter.model.Model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ondrej Stuchlik
 */
public class MainWindow extends JFrame {

    public MainWindow(View view, final FrontController controller, Model model) {
        try {

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("MyShooter");
            this.setResizable(false);

            Dimension obrazovka = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(
                  (int) (obrazovka.getWidth() / 2 - 250),
                  (int) (obrazovka.getHeight() / 2 - 250));

            this.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    // delegate to controller
                    controller.handleKeyboard(evt);
                }
            });

            this.add(view.getCanvas());
            this.pack();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        controller.runGame();
    }
    
    public void showHelp() {
        JOptionPane.showMessageDialog(this, 
              "Controls: \n"
              + "here goes some description...");
    }
}
