package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.entities.ERole;
import tn.esprit.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmail(String email);
    List<User> findUserByRole(ERole role);
    List<User>findUsersByEmailOrFirstNameOrLastNameOrTel(String a,String b,String c,String d);
}
