package frontend.gui;

import java.util.EventListener;

import frontend.gui.model.LoginEvent;

/**
 * Created by dogaro on 13/07/2016.
 */
/**
 * @author Dogar Octavian
 * FormListener interface for intercepting events occurred in the {@link LoginPanel}
 */
public interface LoginListener extends EventListener{
    void loginEventOccurred(LoginEvent e);
}
