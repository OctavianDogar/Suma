package frontend.controller;

import backend.service.UserService;
import backend.service.impl.UserServiceImpl;

/**
 * Created by dogaro on 13/07/2016.
 */
public class LoginController {

    private UserService userService;

    public LoginController(){
        userService = new UserServiceImpl();
    }

    public boolean checkLogin(String username, String password){
        return userService.login(username,password);
    }


}
