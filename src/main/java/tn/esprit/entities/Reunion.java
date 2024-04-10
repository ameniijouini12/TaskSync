package tn.esprit.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reunion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reunionId;
    private String reunionName;
    private LocalDate reunionDate;
    private String meetLink;
    private String reunionSubject;



    @ManyToMany()
@JsonIgnore
    private List<User> participants;

    @ManyToOne()

    private Entreprise entreprise1;
    @ManyToOne()

    private Entreprise entreprise2;
    // Supposons que vous avez déjà créé votre objet event
    private Date startTime;
    private Date endTime;

}