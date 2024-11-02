package controllers;

import dbinterfaces.PatientRepository;
import dbinterfaces.UserRepository;
import entities.User;

public class UserManager {
    private UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void updatePassword(User user, String newPW){
        user.setPassword(newPW);
        userRepository.store(user);
    }
}
