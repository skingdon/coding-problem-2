package gov.utah.hs.ol.portal.services;

import gov.utah.hs.ol.portal.model.beans.AddressBean;
import gov.utah.hs.ol.portal.model.beans.ProgramInfo;
import gov.utah.hs.ol.portal.model.entities.Address;
import gov.utah.hs.ol.portal.model.entities.Phone;
import gov.utah.hs.ol.portal.model.entities.Program;
import gov.utah.hs.ol.portal.model.entities.SiteListItem;
import gov.utah.hs.ol.portal.repositories.ProgramRepository;
import gov.utah.hs.ol.portal.repositories.SiteListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private SiteListItemRepository siteListItemRepository;

    public Program findById(Long id) {
        final Optional<Program> optional = programRepository.findById(id);
        final Program program = optional.get();
        return program;
    }

    public boolean save(ProgramInfo programInfo) {

        // lookup program
        final Program program = findById(programInfo.getId());
        program.setSiteName(programInfo.getSiteName());
        program.setEmail(programInfo.getEmail());
        program.setWebsiteUrl(programInfo.getWebsite());

        Address locationAddress = program.getLocationAddress();
        AddressBean physicalAddress = programInfo.getPhysicalAddress();
        if (locationAddress != null && physicalAddress != null) {
            locationAddress.setAddressOne(physicalAddress.getAddressOne());
            locationAddress.setAddressTwo(physicalAddress.getAddressTwo());
            locationAddress.setCity(physicalAddress.getCity());
            locationAddress.setState(physicalAddress.getState());
            locationAddress.setZipCode(physicalAddress.getZipCode());
        }

        Address mailingAddress = program.getMailingAddress();
        AddressBean newMailingAddress = programInfo.getMailingAddress();
        if (mailingAddress != null && newMailingAddress != null) {
            mailingAddress.setAddressOne(newMailingAddress.getAddressOne());
            mailingAddress.setAddressTwo(newMailingAddress.getAddressTwo());
            mailingAddress.setCity(newMailingAddress.getCity());
            mailingAddress.setState(newMailingAddress.getState());
            mailingAddress.setZipCode(newMailingAddress.getZipCode());
        }

        Phone primaryPhone = program.getPrimaryPhone();
        String newPrimaryPhone = programInfo.getPhone();
        if (primaryPhone != null && newPrimaryPhone != null) {
            newPrimaryPhone = newPrimaryPhone.replace("(", "").replace(")", "").replaceAll("-", "").replaceAll(" ", "");
            primaryPhone.setPhoneNumber(newPrimaryPhone);
        }

        programRepository.save(program);
        return true;
    }

    public Collection<SiteListItem> getDecendants(Long programId) {
        if (programId == null) {
            throw new IllegalArgumentException("facilityId is required, but was null");
        }
        final Collection<SiteListItem> decendents = siteListItemRepository.getDecendants(programId);
        return decendents;
    }

}
