package hh.swd20.SkyWon.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Element {
	
	@Id // // Primary key määritys tietokannan Element-taululle
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	// Attribuutit
	private Long elementid;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "element") // Element @OneToMany Character
	// mappedBy -> viittaa Character-luokan siihen attribuuttiin (element), joka viittaa ko. luokkaan (Element)
	
	@JsonIgnoreProperties ("element") // Yksi tapa välttää loputon silmukka JSON-serialisoinnin/deserialoinnin aikana kaksisuuntaisilla suhteilla
	private List<Character> characters;
	
	// Parametrillinen konstruktori
	public Element( String name) {
		super();
		this.name = name;
	}
	
	// Parametriton konstruktori
	public Element() {
	}
	
	// Setterit
	public void setElementid(Long elementid) {
		this.elementid = elementid;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}
	
	// Getterit
	public Long getElementid() {
		return elementid;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Character> getCharacters() {
		return characters;
	}

	// toString
	@Override
	public String toString() {
		return "Element [elementid=" + elementid + ", name=" + name + "]";
	}
}
