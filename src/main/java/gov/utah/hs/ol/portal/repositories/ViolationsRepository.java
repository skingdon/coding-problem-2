package gov.utah.hs.ol.portal.repositories;

import gov.utah.hs.ol.portal.model.entities.Violation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;

public interface ViolationsRepository extends JpaRepository<Violation, Long> {

    Collection<Violation> findByProgramIdAndResolvedDateAndDeleted(Long programId, Date resolvedDate, Boolean deleted);

}
