package gov.utah.hs.ol.portal.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "PORTAL_USER")
@Data
public class PortalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "PORTAL_USER_SEQ")
    @SequenceGenerator(name = "PORTAL_USER_SEQ", sequenceName = "PORTAL_USER_SEQ")
    private Long id;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "GIVEN_NAME")
    private String givenName;

    @Column(name = "FAMILY_NAME")
    private String familyName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ACTIVE")
    private boolean active;

    @Column(name = "OPEN_ID_ROLE")
    private String openIdRole;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<PortalRole> portalRoles;

}
