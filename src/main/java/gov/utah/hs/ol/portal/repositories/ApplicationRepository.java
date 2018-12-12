package gov.utah.hs.ol.portal.repositories;

import gov.utah.hs.ol.portal.model.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository  extends JpaRepository<Application, Long> {

    Application getById(Long id);

}
