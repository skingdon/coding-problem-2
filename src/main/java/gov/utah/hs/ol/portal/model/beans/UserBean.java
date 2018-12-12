package gov.utah.hs.ol.portal.model.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserBean {

    private String userId;
    private String name;
    private String fullName;
    private String role;
    private String email;
    private String firstName;
    private String lastName;
    private String region;
    private Long regionId;
    private String ein;

}
