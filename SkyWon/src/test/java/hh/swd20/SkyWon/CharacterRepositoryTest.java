package hh.swd20.SkyWon;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.SkyWon.domain.Character;
import hh.swd20.SkyWon.domain.CharacterRepository;

@ExtendWith(SpringExtension.class) // JUnit eli Jupiter
@DataJpaTest
public class CharacterRepositoryTest {
	
	@Autowired
	private CharacterRepository repository;
	
	@Test // FindById()- metodin testaus
	public void findById() {
		
		Character character = repository.findById(8L).get(); // characterid 8 eli Seven
		
		// Assert varmistaa, toimiiko findById()- metodi
		assertThat(character).isEqualTo(character);
	}
	
	@Test // FindByName()- metodi testaus
	public void findByName() {
		List<Character> characters = repository.findByName("Four");
		
		// Assertit varmistavat, toimiiko findByName()- metodi
		assertThat(characters).hasSize(1);
		assertThat(characters.get(0).getGender()).isEqualTo("Male");
	}
	
	@Test // save()- metodi testaus
	public void createNewCharacter() {
		Character character = new Character("Nine", "Male", null, null);
		repository.save(character);
		
		assertThat(character.getId()).isNotNull();
	}
	
	@Test // delete()- metodin testaus ByName
	public void deleteCharacterByName() {
		repository.delete(repository.findByName("Zero").get(0));
		List<Character> characters = repository.findByName("Zero");
		
		assertThat(characters).hasSize(0);
	}
}
