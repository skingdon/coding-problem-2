package gov.utah.hs.ol.portal.services;

import gov.utah.hs.ol.portal.repositories.PortalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;*/
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PortalUserDetailsService /*implements UserDetailsService*/ {

    @Autowired
    private PortalUserRepository repository;

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository
                .findByUsername(username)
                .map(u -> new User(
                    u.getUsername(),
                    u.getPassword(),
                    u.isActive(),
                    u.isActive(),
                    u.isActive(),
                    u.isActive(),
                        AuthorityUtils.createAuthorityList(
                            u.getPortalRoles()
                                .stream()
                                .map(r -> "ROLE_" + r.getName().toUpperCase())
                                .collect(Collectors.toList())
                                .toArray(new String[]{}))))
                    .orElseThrow(() -> new UsernameNotFoundException("No user with the name " + username + "was found in the database"))
                ;
    }*/

}
