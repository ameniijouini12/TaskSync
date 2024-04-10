package tn.esprit.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long idUser;
    @NotBlank
    @Size(max = 20)
    private String firstName;
    @NotBlank
    @Size(max = 20)
    private String lastName;
    @NotBlank
    private String email;
    @Enumerated(EnumType.STRING)
    private ERole role;
    private String job;
    private String image;
    private boolean active;
    private String tel;

    private String password;

    private String code;

    @Transient
    private int statusCode;
    @Transient
    private String status;

    private String bestFriend;
    private String favoritePet;
    private String entreprise;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Event> events;

    @OneToMany(mappedBy = "eventOrganizer",cascade = CascadeType.ALL)
    private List<Event> organizedEvents;

    @ManyToMany(mappedBy = "participants",cascade = CascadeType.ALL)
    private List<Reunion> reunions ;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<EventBooking> eventBookings;
}

