package com.exemple.projectserver.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // Assurez-vous d'importer cette classe

import com.exemple.projectserver.model.Projet;

@Repository // Annotation @Repository ajoutée ici
public interface ProjetRepository extends JpaRepository<Projet, Long> {
    // Vos méthodes personnalisées si nécessaire
}
