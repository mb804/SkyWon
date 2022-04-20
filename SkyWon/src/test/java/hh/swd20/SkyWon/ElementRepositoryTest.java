package hh.swd20.SkyWon;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.SkyWon.domain.Element;
import hh.swd20.SkyWon.domain.ElementRepository;

@ExtendWith(SpringExtension.class) // JUnit5 eli Jupiter
@DataJpaTest
public class ElementRepositoryTest {
	
	@Autowired
	private ElementRepository repository;
	
	@Test // Testataan findByName()-metodin toimivuutta
	public void findByNameShouldReturnElement() {
		List<Element> elements = repository.findByName("Wind");
		
		// Assertit
		assertThat(elements).hasSize(1);
		assertThat(elements.get(0).getName()).isEqualTo("Wind");
	}
	
	@Test // Testataan save()-metodin toimivuutta
	public void createNewElement() {
		Element element = new Element("Time");
		repository.save(element);
		
		assertThat(element.getElementid()).isNotNull();
	}
	
	@Test // Testataan delete()-metodin toimivuutta ByName
	public void deleteElementByName() {
		repository.delete(repository.findByName("Fire").get(0));
		List<Element> elements = repository.findByName("Fire");
		
		assertThat(elements).hasSize(0);
	}
	
	@Test // Testataan deleteById()-metodia
	public void deleteElementById() {
		
		repository.deleteById(6L); // 6L = elementid 6 eli Wind
		
		assertThat(repository.findById(6L));
		
	}
}
