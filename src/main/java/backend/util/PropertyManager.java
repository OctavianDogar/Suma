package backend.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * Created by dogaro on 11/07/2016.
 */
/**
 * @author Dogar Octavian
 * Bundle property getter for backend's layers
 */
public enum PropertyManager {

    INSTANCE;
    private static Logger LOG = LoggerFactory.getLogger(PropertyManager.class);
//    private static final String BUNDLE_NAME = "";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("SUMADB",Locale.ROOT);

    public String getProperty(final String key){
        try{
            return RESOURCE_BUNDLE.getString(key);
        }catch(final MissingResourceException e){
        	LOG.trace("MissingResource",e);
        	e.printStackTrace();
            throw e;
        }
    }
}


