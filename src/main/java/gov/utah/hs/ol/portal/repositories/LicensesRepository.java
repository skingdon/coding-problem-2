package gov.utah.hs.ol.portal.repositories;

import gov.utah.hs.ol.portal.model.entities.License;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface LicensesRepository extends JpaRepository<License, Long> {

    License getById(Long id);

    Long countByProgramIdAndExpirationDateBetween(Long programId, Date begin, Date end);

    Collection<License> findByProgramIdAndExpirationDateBetween(Long programId, Date begin, Date end);
    Collection<License> findByProgramIdAndFinalizedAndStartDateBeforeAndExpirationDateAfter(Long programId, Boolean finalized, Date before, Date after);

    List<License> findByProgramId(Long programId);

}
