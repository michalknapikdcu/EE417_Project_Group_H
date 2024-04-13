package app.sensors;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * @author Conor McCarthy
 */

// Interface for handling contact entites
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}
