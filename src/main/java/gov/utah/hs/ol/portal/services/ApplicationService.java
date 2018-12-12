package gov.utah.hs.ol.portal.services;

import gov.utah.hs.ol.portal.model.entities.Application;
import gov.utah.hs.ol.portal.model.entities.ApplicationSite;
import gov.utah.hs.ol.portal.model.entities.Program;
import gov.utah.hs.ol.portal.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ProgramService programService;

    public Application newApplication(Long programId) {
        final Application application = new Application();
        final Program program = programService.findById(programId);
        application.setProgram(program);
        applicationRepository.save(application);
        return application;
    }

    public Application getApplication(Long applicationId) {
        return applicationRepository.getById(applicationId);
    }

    public Collection<Application> getUnfinishedApplications() {
        return applicationRepository.findAll();
    }

    public Long getUnfinishedApplicationsCount() {
        return applicationRepository.count();
    }

    public ApplicationSite getApplicationSite(Long applicationId) {

        final Application application = getApplication(applicationId);

        final ApplicationSite applicationSite = application.getSite();
        if (applicationSite != null) {
            return applicationSite;
        }
        else {
            return new ApplicationSite();
        }
    }

    public ApplicationSite save(Long applicationId, ApplicationSite applicationSite) {

        final Application application = applicationRepository.getById(applicationId);

        final ApplicationSite site = application.getSite();
        if (site == null) {
            application.setSite(applicationSite);
        }
        else {
            site.setSiteName(applicationSite.getSiteName());
            site.setSiteEmail(applicationSite.getSiteEmail());
            site.setSitePhone(applicationSite.getSitePhone());
            site.setWebsite(applicationSite.getWebsite());
            site.setContactName(applicationSite.getContactName());
            site.setPhysicalAddressOne(applicationSite.getPhysicalAddressOne());
            site.setPhysicalAddressTwo(applicationSite.getPhysicalAddressTwo());
            site.setPhysicalAddressCity(applicationSite.getPhysicalAddressCity());
            site.setPhysicalAddressState(applicationSite.getPhysicalAddressState());
            site.setPhysicalAddressZip(applicationSite.getPhysicalAddressZip());
            site.setMailingAddressOne(applicationSite.getMailingAddressOne());
            site.setMailingAddressTwo(applicationSite.getMailingAddressTwo());
            site.setMailingAddressCity(applicationSite.getMailingAddressCity());
            site.setMailingAddressState(applicationSite.getMailingAddressState());
            site.setMailingAddressZip(applicationSite.getMailingAddressZip());
            site.setMailingSameAsPhysical(applicationSite.isMailingSameAsPhysical());
        }
        applicationRepository.save(application);
        return applicationSite;
    }

}
