package gov.utah.hs.ol.portal.repositories;

import gov.utah.hs.ol.portal.model.entities.SiteListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface SiteListItemRepository extends JpaRepository<SiteListItem, Long> {

    @Query(value = "SELECT ID, FACILITYNAME, SITE_NAME FROM FACILITY WHERE ID IN (SELECT DISTINCT RELATED_ITEM_ID FROM (SELECT DISTINCT ITEM_ID, RELATED_ITEM_ID FROM TAGMAP WHERE EXPIRATION_DATE IS NULL AND CONNECT_BY_ISCYCLE = 0 START WITH ITEM_ID = 243 CONNECT BY NOCYCLE ITEM_ID = PRIOR RELATED_ITEM_ID and level <= 4 ORDER BY ITEM_ID, RELATED_ITEM_ID)) ORDER BY SITE_NAME",
            nativeQuery = true )
    Collection<SiteListItem> getDecendants(@Param("facilityId") Long facilityId);

}
