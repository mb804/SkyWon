package hh.swd20.SkyWon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.SkyWon.domain.Character;
import hh.swd20.SkyWon.domain.CharacterRepository;
import hh.swd20.SkyWon.domain.Element;
import hh.swd20.SkyWon.domain.ElementRepository;
import hh.swd20.SkyWon.domain.User;
import hh.swd20.SkyWon.domain.UserRepository;
import hh.swd20.SkyWon.domain.Weapon;
import hh.swd20.SkyWon.domain.WeaponRepository;

@SpringBootApplication
public class SkyWonApplication {
	private static final Logger log = LoggerFactory.getLogger(SkyWonApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SkyWonApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner skyWonDemo(CharacterRepository characterRepository, ElementRepository elementRepositorty, WeaponRepository weaponRepository, UserRepository userRepository) {
		return (args) -> {
			log.info("save couple of characters"); // Save to database > SQL INSERT
			
			// WeaponRepository
			Weapon weapon1 = new Weapon("Sword");
			weaponRepository.save(weapon1);
			Weapon weapon2 = new Weapon("Bow");
			weaponRepository.save(weapon2);
			Weapon weapon3 = new Weapon("Chakram");
			weaponRepository.save(weapon3);
			
			log.info("fetch all weapons"); // From database > SQL SELECT
			for (Weapon weapon : weaponRepository.findAll()) {
				log.info(weapon.toString());
				
			}
			
			// ElementRepository
			Element element1 = new Element("Fire");
			elementRepositorty.save(element1);
			Element element2 = new Element("Water");
			elementRepositorty.save(element2);
			Element element3 = new Element("Wind");
			elementRepositorty.save(element3);
			Element element4 = new Element("Earth");
			elementRepositorty.save(element4);
			
			log.info("fetch all elements"); // From database > SQL SELECT
			for (Element element : elementRepositorty.findAll()) {
				log.info(element.toString());
			
			}

			//CharacterRepository
			characterRepository.save(new Character("Seven", "Female", weapon1, element1));
			characterRepository.save(new Character("Four", "Male", weapon1, element3));
			characterRepository.save(new Character("Zero", "Female", weapon3, element4));
			characterRepository.save(new Character("Eight", "Male", weapon2, element2));
			
			// Users
			User user1 = new User("user", "$2a$10$5a5kvPU64KIfj1RrdgWmVeeGUG/F0IUTsCMgJ86Scks870KbmPOou", "user@email.com", "USER");
			User user2 = new User("admin", "$2a$10$xr2YjICF7tOJzteCz2GkAeFL2krhlRmqDw7AXMcFo23PngPwJSAMy", "admin@email.com", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
			
			
			log.info("fetch all characters"); // From database > SQL SELECT
			for (Character character : characterRepository.findAll()) {
				log.info(character.toString());
			}
		};
	}
}
