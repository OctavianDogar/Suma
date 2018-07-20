package frontend.gui;
import frontend.gui.MainFrame;

import javax.swing.SwingUtilities;

/**
 * Created by dogaro on 10/07/2016.
 */

/**
 * @author Dogar Octavian
 * Initiator class for synchronous rendering of {@link MainFrame}'s components
 */
public class App {

    public static void main (String []args){

        SwingUtilities.invokeLater(() -> new MainFrame());
    }

}
