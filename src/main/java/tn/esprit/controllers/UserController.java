package tn.esprit.controllers;


import lombok.AllArgsConstructor;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.esprit.dto.EventCalendar;
import tn.esprit.dto.UserDTO;
import tn.esprit.entities.*;
import tn.esprit.exceptions.CustomException;
import tn.esprit.services.EmailService;
import tn.esprit.services.IUserService;

import javax.validation.Valid;
import javax.ws.rs.core.Response;
import java.util.*;


@RequestMapping(value = "/users")
@RestController

public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    private String authServerUrl = "http://localhost:8180";
    private String realm = "spring-boot-microservices-realm";
    private String clientId = "spring-cloud-client";
    private String clientSecret = "yJBHwxHc9uwxn2f6xGmxrSVIEuD9y875";
    private String real ="master";
    private String client ="admin-cli";
    private String username="admin";
    private String password="admin";
    @Autowired
    private IUserService userService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @GetMapping(path = "/admin/{userName}")
    public User getUser(@PathVariable("userName") String userName){

        return userService.findUser(userName);
    }
    @DeleteMapping(path = "/admin/{userName}")
    public String deleteUser(@PathVariable("userName") String userName){
        Keycloak keycloak = KeycloakBuilder.builder().serverUrl(authServerUrl)
                .grantType(OAuth2Constants.PASSWORD).realm(real).clientId(client)
                .username(username).password(password)
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();
        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersRessource = realmResource.users();
        List<UserRepresentation> user = usersRessource.search(userName, true);
        for (UserRepresentation u:user
             ) {
            usersRessource.get(u.getId())
                    .remove();
        }
       userService.deleteUser(userName);
        return "User Deleted Successfully.";
    }



    @PostMapping(path = "/admin/{userName}")
    public String sendResetPassword(@PathVariable("userName") String userName,@RequestBody UserDTO userDTO){

        if (userService.findUser(userDTO.getEmail())==null) {

            return "There isn't an account with that email address:" + userDTO.getEmail();
        }
        else{
        Keycloak keycloak = KeycloakBuilder.builder().serverUrl(authServerUrl)
                .grantType(OAuth2Constants.PASSWORD).realm(real).clientId(client)
                .username(username).password(password)
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();
        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersRessource = realmResource.users();
        // create password credential
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(userDTO.getPassword());
        List<UserRepresentation> user = usersRessource.search(userName, true);
        for (UserRepresentation u:user
        ) {
            UserResource userResource = usersRessource.get(u.getId());

            // Set password credential
            userResource.resetPassword(passwordCred);

        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userService.resetPassword(userName,userDTO);
        return "Reset Password Successfully to Registered E-mail Id.";}
    }





    @PutMapping("/admin/banUser/{email}")
    public String banUser(@PathVariable String email){
        return userService.banUser(email);
}

    @PutMapping("/admin/unbanUser/{email}")
    public String unbanUser(@PathVariable String email){
        return userService.unbanUser(email);
    }
    @GetMapping("/admin/search/{data}")
    public List<User> search(@PathVariable String data){
        return userService.search(data);
    }

    @GetMapping("/admin/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAll();
    }
    @GetMapping("/admin/getEnt")
    public List<Entreprise> getEnt(){
        return userService.getEnt();
    }
    @GetMapping("/admin/getUserById/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }


    @GetMapping("/{userId}/reunions")
    public ResponseEntity<List<Reunion>> getUserReunions(@PathVariable String userId) {
        List<Reunion> reunions = userService.getUserReunions(userId);
        return ResponseEntity.ok(reunions);
    }

    @GetMapping("/{userId}/events")
    public ResponseEntity<List<Event>> getUserEvents(@PathVariable Long userId) {
        List<Event> events = userService.getUserEvents(userId);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{userId}/conferences")
    public ResponseEntity<List<Event>> getUserConferences(@PathVariable Long userId) {
        List<Event> conferences = userService.getUserConference(userId);
        return ResponseEntity.ok(conferences);
    }
    @GetMapping("/{userId}/Calendar")
    public List<EventCalendar> getUserCalendar(@PathVariable String userId) {
     return   userService.getUserCalendarEvents(userId);

    }
    @GetMapping("/{userId}/bookings")
    public List<EventBooking> getUserBookings(@PathVariable String userId) {
        return   userService.getUserBookings(userId);

    }
}