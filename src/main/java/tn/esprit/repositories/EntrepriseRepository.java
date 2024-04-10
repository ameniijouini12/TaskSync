package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.entities.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise,Integer> {
}
