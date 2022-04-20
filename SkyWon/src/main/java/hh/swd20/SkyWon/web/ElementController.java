package hh.swd20.SkyWon.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.SkyWon.domain.Element;
import hh.swd20.SkyWon.domain.ElementRepository;

@Controller
public class ElementController {
	
	@Autowired
	private ElementRepository elementRepository;
	
	// localhost:8080/elementlist
	@RequestMapping(value = "/elementlist")
	public String elementlist(Model model) {
		model.addAttribute("elements", elementRepository.findAll());
		
		return "elementlist"; // elementlist.html
	}
	
	// localhost:8080/addelement
	@RequestMapping(value = "/addelement")
	public String addElement(Model model) {
		model.addAttribute("element", new Element());
		
		return "addelement"; // addelement.html
	}
	
	// localhost:8080/save
	@RequestMapping(value = "/saveElement", method = RequestMethod.POST)
	public String saveElement(Element element) {
		elementRepository.save(element);
		
		return "redirect:/elementlist"; // return to /elementlist endpoint
	}
	
	// localhost:8080/delete/{id}
	@RequestMapping(value = "/deleteElement/{id}", method = RequestMethod.GET)
	// Vain ADMIN voi poistaa
    @PreAuthorize("hasAuthority('ADMIN')")
	public String deleteElement(@PathVariable("id") Long elementId, Model model) {
		elementRepository.deleteById(elementId);
		
		return "redirect:/elementlist"; // return to /elementlist endpoint
	}
}
