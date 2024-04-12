package tn.esprit.services;

import tn.esprit.dto.UserDTO;
import tn.esprit.entities.*;

import java.util.List;

public interface IUserService {

    void deleteUser(String email);
     User findUser(String email);
    void resetPassword(String email,UserDTO userDTO);


     List<User> search(String data);
    List<User> getAll();


    User findUserById(Long id);

    User addUser(User user);
}
