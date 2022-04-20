package hh.swd20.SkyWon;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.SkyWon.domain.User;
import hh.swd20.SkyWon.domain.UserRepository;

@ExtendWith(SpringExtension.class) // JUnit5 eli Jupiter
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository repository;
	
	@Test // Testataan findByRole()-metodia
	public void findByRoleShouldReturnUser() {
		
		List<User> users = repository.findByRole("USER");
		
		// Assertit
		assertThat(users).hasSize(1);
		assertThat(users.get(0).getUsername()).isEqualTo("user");
	}
	
	@Test // Testataan findAll()-metodia
	public void findAll() {
		
		repository.findAll();
	}
	
	@Test // Testataan save()-metodia
	public void createNewUser() {
		User user = new User("staff", "$2a$10$K2G68UG5uWqjV6CHPlgPguD74mLUbaTWehDoC7pRRNj9a8QrnXWCC", "staff@email.com", "STAFF");
		repository.save(user);
		
		assertThat(user.getId()).isNotNull();
	}
	
	@Test // Testataan deleteByEmail()-metodia
	public void deleteByEmail() {
		repository.delete(repository.deleteByEmail("user@email.com").get(0));
		List<User> users = repository.deleteByEmail("user@email.com");
		
		assertThat(users).hasSize(0);
	}
}
