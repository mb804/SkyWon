package hh.swd20.SkyWon.web;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.SkyWon.domain.Character;
import hh.swd20.SkyWon.domain.CharacterRepository;
import hh.swd20.SkyWon.domain.ElementRepository;
import hh.swd20.SkyWon.domain.WeaponRepository;

@CrossOrigin
@Controller
public class CharacterController {
	
	@Autowired
	private CharacterRepository characterRepository;
	
	@Autowired
	private ElementRepository elementRepository;
	
	@Autowired
	private WeaponRepository weaponRepository;
	
	// http://localhost:8080/index
	// i18n testaus
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexSkyWon(Model model, HttpServletRequest request) {
		model.addAttribute("characters", characterRepository.findAll());
		
		Locale currentLocale = request.getLocale();
		String countryCode = currentLocale.getCountry();
		String countryName = currentLocale.getDisplayName();
		
		String langCode = currentLocale.getLanguage();
		String langName = currentLocale.getDisplayLanguage();
		
		System.out.println(countryCode + ": " + countryName);
		System.out.println(langCode + ": " + langName);
		
		// Lisää kaikki kielet (kaikki kielet näkyvät Consolessa, jos päivittää local sivun)
		System.out.println("============");
		String[] languagues = Locale.getISOLanguages();
		
		for (String language : languagues) {
			Locale locale = new Locale(language);
			System.out.println(language + ": " + locale.getDisplayLanguage());
		}
		
		return "index"; // index.html
	}
	
	// http://localhost:8080
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String SkyWon(Model model) {
		model.addAttribute("characters", characterRepository.findAll());
		
		return "index"; // index.html
	}
	
	// Show all characters
	// http://localhost:8080/characterlist
	@RequestMapping(value="/characterlist")
	public String characterList(Model model) {
		model.addAttribute("characters", characterRepository.findAll());
		
		return "characterlist"; //characterlist.html
	}
	
	// RESTful service to get all characters
	// Java-kielinen Character-luokan oliolista muunnetaan JSON-characterlistaksi ja lähetetään web-selaimelle vastauksena
	@RequestMapping(value="/characters", method = RequestMethod.GET)
	public @ResponseBody List<Character> characterListRest() {
		
		return (List<Character>) characterRepository.findAll();
		
	}
	
	// RESTful service to get character by id
    @RequestMapping(value="/characters/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Character> findCharacterRest(@PathVariable("id") Long characterId) {
    	
    	return characterRepository.findById(characterId);
    }
    
    // RESTful service to save new character
    // POST = uuden tiedon lisäystä
    @RequestMapping(value="/characters", method = RequestMethod.POST)
    public @ResponseBody Character saveCharacterRest(@RequestBody Character character) {	
    	
    	return characterRepository.save(character);
    }
	
	// http://localhost:8080/addcharacter
	@RequestMapping(value = "/addcharacter")
	public String addCharacter(Model model) {
		model.addAttribute("character", new Character());
		model.addAttribute("weapons", weaponRepository.findAll());
		model.addAttribute("elements", elementRepository.findAll());
			
			return "addcharacter"; // return to addcharacter
	}
	
	// http://localhost:8080/save
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveCharacter(Character character) {
		characterRepository.save(character);
			
			return "redirect:/characterlist"; // return to /characterlist endpoint
	}
	
	// http://localhost:8080/delete/{id}
		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		// Vain ADMIN voi poistaa
	    @PreAuthorize("hasAuthority('ADMIN')")
		public String deleteCharacter(@PathVariable("id") Long characterId, Model model) {
			characterRepository.deleteById(characterId);
			
			return "redirect:/characterlist"; // return to /characterlist endpoint
	}
		
	// http://localhost:8080/editcharacter/{id}
	@RequestMapping(value = "/editcharacter/{id}")
	public String editCharacter(@PathVariable("id") Long characterId, Model model) {
		model.addAttribute("character", characterRepository.findById(characterId));
		model.addAttribute("weapons", weaponRepository.findAll());
		model.addAttribute("elements", elementRepository.findAll());
			
		return "editcharacter"; // editcharacter.html
	}
}
