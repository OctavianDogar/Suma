package backend.service;

import backend.service.impl.UserServiceImpl;

/**
 * Created by dogaro on 11/07/2016.
 */

/**
 * @author Dogar Octavian
 * Utility class that recieves an instance of the service layer implementation {@link UserServiceImpl}
 */
public class ServiceManager {
    public UserService getUserService(){
        return new UserServiceImpl();
    }
}
