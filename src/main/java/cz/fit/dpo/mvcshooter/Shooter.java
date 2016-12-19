package cz.fit.dpo.mvcshooter;

import cz.fit.dpo.mvcshooter.controller.FrontController;
import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.view.Canvas;
import cz.fit.dpo.mvcshooter.view.MainWindow;
import cz.fit.dpo.mvcshooter.view.View;

import javax.swing.SwingUtilities;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

/**
 *
 * @author stue
 */
public class Shooter {
    
    public static void main(String[] args) {
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        List<String> arguments = runtimeMxBean.getInputArguments();

        for (String arg: arguments) {
            System.out.println(arg);
        }

        final Model model = new Model();
        View view = new View(model);
        final FrontController controller = new FrontController(model, view);
        model.registerObserver(view);

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
               new MainWindow(view,controller,model).setVisible(true);
            }
        });
    }
}
