package frontend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backend.model.User;
import backend.model.UserType;
import backend.service.UserService;
import backend.service.impl.UserServiceImpl;
import frontend.gui.model.FormEvent;

/**
 * Created by dogaro on 12/07/2016.
 */
public class FormController {

    private UserService userService;
    private static Logger LOG = LoggerFactory.getLogger(FormController.class);


    public FormController(){
        userService = new UserServiceImpl();
    }

    public List<User> getUsers(){

        return userService.getAllUsers();
    }

    private User eventToUser(FormEvent event){
        String id = event.getID();
        String firstName = event.getFirstName();
        String lastName = event.getLastName();
        String password = event.getPassword();
        String username = event.getUsername();
        String userType = event.getUserType();


        Long labelId;
        try{
            labelId = Long.parseLong(id);
            return new User(labelId,firstName,lastName,password,username,UserType.valueOf(userType));
        }catch(NullPointerException | NumberFormatException e){
            return new User(firstName,lastName,password,username,UserType.valueOf(userType));
        }
    }

    public void addUser(FormEvent event){

        userService.insertUser(eventToUser(event));
    }

    public void updateUser(FormEvent event){

        userService.updateUser(eventToUser(event));

    }

    public void deleteUser(FormEvent event){

        userService.deleteUser(eventToUser(event));
    }

}
