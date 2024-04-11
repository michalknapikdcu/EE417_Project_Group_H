package app.sensors;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findByUsername(String username);
    List<Login> findByRole(String access_level); // Method to find users by role
}