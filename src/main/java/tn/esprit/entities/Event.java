package tn.esprit.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;
    private String eventName;
    private Date eventDate;
    private String image;
    private int eventCapacity;
    private int remainingPlaces;
    private String eventLocation;
    @Enumerated(EnumType.STRING)
    private EType typeEvent;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    @JsonIgnore
    private User eventOrganizer;

    @ManyToMany(mappedBy = "events",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> participants;
    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<EventBooking> eventBookings;

    private String speakers ;
    private String description;
}
