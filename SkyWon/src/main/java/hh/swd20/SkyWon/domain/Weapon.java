package hh.swd20.SkyWon.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Weapon {
	
	@Id // Primary key määritys tietokannan Category-taululle
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	// Attribuutit
	private Long weaponid;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "weapon") // Weapon @OneToMany Character
	// mappedBy -> viittaa Character-luokan siihen attribuuttiin (weapon), joka viittaa ko. luokkaan (Weapon)
	
	@JsonIgnoreProperties ("weapon") // Yksi tapa välttää loputon silmukka JSON-serialisoinnin/deserialoinnin aikana kaksisuuntaisilla suhteilla
	private List<Character> characters;
	
	// Parametrillinen konstruktori
	public Weapon(String name) {
		super();
		this.name = name;
	}
	
	// Parametriton konstruktori
	public Weapon() {
	}
	
	// Setterit
	public void setWeaponid(Long weaponid) {
		this.weaponid = weaponid;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}
	
	// Getterit
	public Long getWeaponid() {
		return weaponid;
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
		return "Weapon [weaponid=" + weaponid + ", name=" + name + "]";
	}
}
