package gov.utah.hs.ol.portal.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "PORTAL_ROLE")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PortalRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "PORTAL_ROLE_SEQ")
    @SequenceGenerator(name = "PORTAL_ROLE_SEQ", sequenceName = "PORTAL_ROLE_SEQ")
    private Long id;

    @NotNull
    @Column(name = "ROLE_NAME")
    private String name;

    public PortalRole(String name) {
        this.name = name;
    }

}
