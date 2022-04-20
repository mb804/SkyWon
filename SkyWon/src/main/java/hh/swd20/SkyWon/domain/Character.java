package hh.swd20.SkyWon.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Character {
	
	@Id // Primary key-määritys
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	// Attribuutit
	private String name;
	private String gender;
	
	// Weapon
	@ManyToOne // Character @ManyToOne Weapon
	@JsonIgnoreProperties ("characters") // Yksi tapa välttää loputon silmukka JSON-serialisoinnin/deserialoinnin aikana kaksisuuntaisilla suhteilla
	@JoinColumn(name = "weaponid") // Foreign key-määritys
	private Weapon weapon;
	
	// Element
	@ManyToOne // Character @ManyToOne Element
	@JsonIgnoreProperties ("characters") // Yksi tapa välttää loputon silmukka JSON-serialisoinnin/deserialoinnin aikana kaksisuuntaisilla suhteilla
	@JoinColumn(name = "elementid") // Foreign key-määritys
	private Element element;
	
	// Parametrillinen konstruktori
	public Character(String name, String gender, Weapon weapon, Element element) {
		super();
		this.name = name;
		this.gender = gender;
		this.weapon = weapon;
		this.element = element;
	}
	
	// Parametriton konstruktori
	public Character() {
		
	}
	
	// Setterit
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public void setElement(Element element) {
		this.element = element;
	}
	
	// Getterit
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public Element getElement() {
		return element;
	}
	
	// toString
	@Override
	public String toString() {
		if (this.weapon !=null)
			return "Character [id=" + id + ", name=" + name + ", gender=" + gender + " weapon =" + this.getWeapon() + " element =" + this.getElement() + "]";
		else
			return "Character [id=" + id + ", name=" + name + ", gender=" + gender + "]";
	}
}
