package gov.utah.hs.ol.portal.services;

import gov.utah.hs.ol.portal.model.entities.Violation;
import gov.utah.hs.ol.portal.repositories.ViolationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ViolationsService {

    @Autowired
    private ViolationsRepository violationsRepository;

    public int getOpenViolationsCount(Long programId) {
        final Collection<Violation> violations = violationsRepository.findByProgramIdAndResolvedDateAndDeleted(programId, null, false);
        return violations != null ? violations.size() : 0;
    }

    public Collection<Violation> getOpenViolations(Long programId) {
        final Collection<Violation> violations = violationsRepository.findByProgramIdAndResolvedDateAndDeleted(programId, null, false);
        return violations;
    }

}
