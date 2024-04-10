package com.exemple.tacheserver.repositorie;

import com.exemple.tacheserver.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // Assurez-vous d'importer cette classe


@Repository // Annotation @Repository ajoutée ici
public interface TacheRepository extends JpaRepository<Tache, Long> {
}
