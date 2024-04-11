package app.sensors;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * @author Conor McCarthy
 */
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}
