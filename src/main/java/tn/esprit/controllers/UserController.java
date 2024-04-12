package tn.esprit.controllers;


import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.esprit.dto.UserDTO;
import tn.esprit.entities.*;
import tn.esprit.services.EmailService;
import tn.esprit.services.IUserService;

import java.util.*;


@RequestMapping(value = "/users")
@RestController

public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    private String authServerUrl = "http://localhost:8080";
    private String realm = "spring-boot-microservices-realm";
    private String clientId = "client_id";
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






    @GetMapping("/admin/search/{data}")
    public List<User> search(@PathVariable String data){
        return userService.search(data);
    }

    @GetMapping("/admin/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
    }
    @RequestMapping
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }


}