package tn.esprit.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.dto.UserDTO;
import tn.esprit.entities.*;
import tn.esprit.repositories.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class IUserServiceImpl implements IUserService{
    private final UserRepository userRepository;


    @Override
    public void deleteUser(String email) {
        userRepository.delete(userRepository.findUserByEmail(email));

    }

    @Override
    public User findUser(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void resetPassword(String email, UserDTO userDTO) {
User u=userRepository.findUserByEmail(email);
u.setPassword(userDTO.getPassword());
userRepository.save(u);
    }






    @Override
    public List<User> search(String data) {
        return userRepository.findUsersByEmailOrFirstNameOrLastNameOrTel(data,data,data,data);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }



    @Override
    public User findUserById(Long id) {

        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }


}
