package tn.esprit.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entreprise {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int identreprise ;
    private String nom ;
    private String secteur;
    private String adresse ;
    private int telephone;
    @OneToMany(mappedBy = "entreprise1",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reunion> entreprise1Reunions;

    @OneToMany(mappedBy = "entreprise2",cascade = CascadeType.ALL)
    @JsonIgnore

    private List<Reunion> entreprise2Reunions ;

    private int invitations;





}