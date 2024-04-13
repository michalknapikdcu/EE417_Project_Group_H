package app.sensors;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findByUsername(String username);
    List<Login> findByRole(String role); // Method to find users by access level
}
