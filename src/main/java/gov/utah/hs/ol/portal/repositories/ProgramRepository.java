package gov.utah.hs.ol.portal.repositories;

import gov.utah.hs.ol.portal.model.entities.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {

}
