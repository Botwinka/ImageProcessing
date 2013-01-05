package imagep;
import java.awt.EventQueue;
import javax.swing.*;

/**
 *
 * @author Asia
 */
public class MainClass {

    /**
     * @param args the command line arguments
     * 
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                ParentFrame mainWindow = new ParentFrame();
                mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainWindow.setVisible(true);
            }
        });
        
    }
}
