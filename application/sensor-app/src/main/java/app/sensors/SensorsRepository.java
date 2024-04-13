package app.sensors;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorsRepository extends JpaRepository<Sensor, Long> {
    @SuppressWarnings("null")
    List<Sensor> findAll();
    Sensor findByName(String name);
    Sensor findByLocation(String location);
    
    @SuppressWarnings("null")
    void deleteById(Long id);
    
    long count();
}
