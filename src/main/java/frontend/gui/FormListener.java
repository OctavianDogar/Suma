package frontend.gui;

import frontend.gui.model.FormEvent;

import java.util.EventListener;

/**
 * Created by dogaro on 12/07/2016.
 */

/**
 * @author Dogar Octavian
 * FormListener interface for intercepting events occurred in the {@link FormPanel}
 */
public interface FormListener extends EventListener{
    void formEventOccured(FormEvent e, String threw);

}
