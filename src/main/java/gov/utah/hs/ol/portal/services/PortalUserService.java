package gov.utah.hs.ol.portal.services;

import gov.utah.hs.ol.portal.model.entities.PortalUser;
import gov.utah.hs.ol.portal.repositories.PortalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PortalUserService {

    @Autowired
    private PortalUserRepository repo;

    public void save(PortalUser user){
        repo.save(user);
    }

    public PortalUser getByUsername(String username) {
        return repo.getByUsername(username);
    }

}
