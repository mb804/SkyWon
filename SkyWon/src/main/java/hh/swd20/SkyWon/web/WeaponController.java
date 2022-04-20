package hh.swd20.SkyWon.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.SkyWon.domain.Weapon;
import hh.swd20.SkyWon.domain.WeaponRepository;

@Controller
public class WeaponController {
	
	@Autowired
	private WeaponRepository weaponRepository;
	
	// localhost:8080/weaponlist
	@RequestMapping(value = "/weaponlist")
	public String weaponlist(Model model) {
		model.addAttribute("weapons", weaponRepository.findAll());
		
		return "weaponlist"; // weaponlist.html
	}

	// localhost:8080/addweapon
	@RequestMapping(value = "/addweapon")
	public String addWeapon(Model model) {
		model.addAttribute("weapon", new Weapon());
		
		return "addweapon"; // addweapon.html
	}
	
	// localhost:8080/save
	@RequestMapping(value = "/saveWeapon", method = RequestMethod.POST)
	public String saveWeapon(Weapon weapon) {
		weaponRepository.save(weapon);
		
		return "redirect:/weaponlist"; // return to /weaponlist endpoint
	}
	
	// localhost:8080/delete/{id}
	@RequestMapping(value = "/deleteWeapon/{id}", method = RequestMethod.GET)
	// Vain ADMIN voi poistaa
    @PreAuthorize("hasAuthority('ADMIN')")
	public String deleteWeapon(@PathVariable("id") Long weaponId, Model model) {
		weaponRepository.deleteById(weaponId);
		
		return "redirect:/weaponlist"; // return to /weaponlist endpoint
	}
}
