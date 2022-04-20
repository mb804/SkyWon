package hh.swd20.SkyWon.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ElementRepository extends CrudRepository<Element, Long> {
	
	// ElementRepository perii CrudRepositorylta
	// findAll(), findById(), save(), deleteById() yms.
	
	List<Element> findByName(String name);
}
