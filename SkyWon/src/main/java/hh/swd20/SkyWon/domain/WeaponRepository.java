package hh.swd20.SkyWon.domain;

import org.springframework.data.repository.CrudRepository;

public interface WeaponRepository extends CrudRepository<Weapon, Long> {
	
	// WeaponRepository perii CrudRepositorylta
	// findAll(), findById(), save(), deleteById() yms.
}
