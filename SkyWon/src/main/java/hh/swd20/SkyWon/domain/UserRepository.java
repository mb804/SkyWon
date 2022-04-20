package hh.swd20.SkyWon.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username); // Must
	
	List<User> findByRole(String role);
	
	List<User> deleteByEmail(String email);
}
