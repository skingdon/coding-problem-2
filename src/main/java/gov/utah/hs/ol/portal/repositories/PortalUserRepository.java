package gov.utah.hs.ol.portal.repositories;

import gov.utah.hs.ol.portal.model.entities.PortalUser;
import gov.utah.hs.ol.portal.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortalUserRepository extends JpaRepository<PortalUser,Long> {

    PortalUser getByUsername(String username);

}
