package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.entities.EType;
import tn.esprit.entities.Event;
import tn.esprit.entities.User;

import java.util.List;


public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByParticipantsContainingAndTypeEvent(User user, EType typeEvent);

}
