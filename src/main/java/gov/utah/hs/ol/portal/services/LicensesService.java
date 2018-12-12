package gov.utah.hs.ol.portal.services;

import gov.utah.hs.ol.portal.model.beans.LicenseListItem;
import gov.utah.hs.ol.portal.model.entities.License;
import gov.utah.hs.ol.portal.model.entities.PortalUser;
import gov.utah.hs.ol.portal.repositories.LicensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LicensesService {

    @Autowired
    private LicensesRepository licensesRepository;

    @Autowired
    private ProgramService programService;

    public License getLicense(Long licenseId) {
        return licensesRepository.getById(licenseId);
    }

    public Collection<LicenseListItem> getAllLicenses(Long programId) {
        final Collection<License> licenses = licensesRepository.findByProgramId(programId);
        final Collection<LicenseListItem> licenseBeans = new ArrayList<>();
        for (License license : licenses) {
            licenseBeans.add(new LicenseListItem(license));
        }
        return licenseBeans;
    }

    public Collection<LicenseListItem> getAllActiveLicenses(Long programId) {
        final Collection<License> licenses = licensesRepository.findByProgramIdAndFinalizedAndStartDateBeforeAndExpirationDateAfter(programId, Boolean.TRUE, new Date(), new Date());
        final Collection<LicenseListItem> licenseBeans = new ArrayList<>();
        for (License license : licenses) {
            licenseBeans.add(new LicenseListItem(license));
        }
        return licenseBeans;
    }

    public Collection<LicenseListItem> getExpiringLicenses(Long programId) {

        final Date now = new Date();
        final Calendar future = Calendar.getInstance();
        future.add(Calendar.DATE, 30);
        final Date futureDate = future.getTime();

        final Collection<License> licenses = licensesRepository.findByProgramIdAndExpirationDateBetween(programId, now, futureDate);
        final Collection<LicenseListItem> licenseListItems = new ArrayList<>();
        for (License license : licenses) {
            licenseListItems.add(new LicenseListItem(license));
        }
        return licenseListItems;
    }

    public Long getExpiringLicensesCount(PortalUser portalUser) {

        // NEED PROGRAM_PORTAL_USER table
        programService.getProgramsByUser(portalUser);

        final Date now = new Date();
        final Calendar future = Calendar.getInstance();
        future.add(Calendar.DATE, 30);
        final Date futureDate = future.getTime();

        return licensesRepository.countByProgramIdAndExpirationDateBetween(programId, now, futureDate);
    }

}
