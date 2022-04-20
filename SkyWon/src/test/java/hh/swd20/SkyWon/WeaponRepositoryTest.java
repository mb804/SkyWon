package hh.swd20.SkyWon;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.SkyWon.domain.Weapon;
import hh.swd20.SkyWon.domain.WeaponRepository;

@ExtendWith(SpringExtension.class) // JUnit5 eli Jupiter
@DataJpaTest
public class WeaponRepositoryTest {
	
	@Autowired
	private WeaponRepository repository;
	
	@Test // Testataan findById()-metodia
	public void findById() {
		
		Weapon weapon = repository.findById(1L).get(); // 1L = weaponid 1 eli Sword
		
		// Assert
		assertThat(weapon).isEqualTo(weapon);
	}
	
	@Test // Testataan save()-metodia
	public void createNewWeapon() {
		Weapon weapon = new Weapon("Bombs");
		repository.save(weapon);
		
		assertThat(weapon.getWeaponid()).isNotNull();
	}
	
	@Test // Testataan deleteById()-metodia
	public void deleteWeaponById() {
		
		repository.deleteById(3L); // 3L = weaponid 3 eli Chakram
		
		assertThat(repository.findById(3L));
		
	}
	
	@Test // Testataan deleteAll()-metodia
	public void deleteWeaponAll() {
		
		repository.deleteAll();
		
		assertThat(repository.findAll()).isEmpty();
	}
}
