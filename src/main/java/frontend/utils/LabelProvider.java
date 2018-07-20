package frontend.utils;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import backend.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dogaro on 14/07/2016.
 */

/**
 * @author Dogar Octavian
 * Bundle property getter for the presentation layer
 */
public class LabelProvider {
	
	private static Logger LOG = LoggerFactory.getLogger(LabelProvider.class);

    public static ResourceBundle resourceBundle = ResourceBundle.getBundle("SwingLabels",
            new Locale(
                    PropertyManager.INSTANCE.getProperty("default_language"),
                    PropertyManager.INSTANCE.getProperty("default_region"))
    );

    public static String getLabel(final String key){
        try{
            return new String(resourceBundle.getString(key).getBytes(),
                    PropertyManager.INSTANCE.getProperty("passwordEncoding"));
        }catch(final MissingResourceException e){
        	LOG.error("Missing resource: "+key+" ",e);
            return null;
        }catch (final UnsupportedEncodingException e){
        	LOG.error("Unsupported encoding",e);
            return resourceBundle.getString(key);
        }
    }

    public static void setLocale(final Locale locale){
        resourceBundle  = ResourceBundle.getBundle("SwingLabels",locale);
    }

}
