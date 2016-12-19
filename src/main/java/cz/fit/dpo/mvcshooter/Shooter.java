package cz.fit.dpo.mvcshooter;

import cz.fit.dpo.mvcshooter.controller.FrontController;
import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.view.Canvas;
import cz.fit.dpo.mvcshooter.view.MainWindow;
import javax.swing.SwingUtilities;

/**
 *
 * @author stue
 */
public class Shooter {
    
    public static void main(String[] args) {

        final Model model = new Model();
        final Canvas view = new Canvas(model, 0, 0, 500, 500);
        final FrontController controller = new FrontController(model, view);

        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
               new MainWindow(view,controller,model).setVisible(true);
            }
        });
    }
}
