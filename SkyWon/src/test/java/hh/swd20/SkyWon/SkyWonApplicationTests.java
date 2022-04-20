package hh.swd20.SkyWon;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.SkyWon.web.CharacterController;
import hh.swd20.SkyWon.web.ElementController;
import hh.swd20.SkyWon.web.WeaponController;

@ExtendWith(SpringExtension.class) // JUnit5 eli Jupiter
@SpringBootTest
class SkyWonApplicationTests {
	
	@Autowired
	private CharacterController characterController;
	private ElementController elementController;
	private WeaponController weaponController;
	
	@Test
	void contextLoads() throws Exception {
		assertThat(characterController).isNotNull();
		assertThat(elementController).isNotNull();
		assertThat(weaponController).isNotNull();
	}
}
