package it.gianni.numberschecker.repository;

import it.gianni.numberschecker.model.SouthAfricanMobileNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SouthAfricanMobileNumberRepository extends JpaRepository<SouthAfricanMobileNumberEntity, Long> {
}
