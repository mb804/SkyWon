package hh.swd20.SkyWon.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CharacterRepository extends CrudRepository<Character, Long> {
	
	// CharacterRepository perii CrudRepositorylta
	// findAll(), findById(), save(), deleteById() yms.
	
	List<Character> findByName(String name);
	
}
