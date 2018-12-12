package gov.utah.hs.ol.portal.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.utah.hs.ol.portal.model.beans.UserBean;
import gov.utah.hs.ol.portal.model.entities.PortalUser;
import gov.utah.hs.ol.portal.services.PortalUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OpenIdConnectAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final Logger log = LoggerFactory.getLogger(OpenIdConnectAuthenticationFilter.class);

    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;

    @Autowired
    private PortalUserService portalUserService;

    public OpenIdConnectAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
        setAuthenticationManager(new NoOpAuthenticationManager());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        final OAuth2AccessToken accessToken;
        try {
            accessToken = oAuth2RestTemplate.getAccessToken();
        }
        catch (OAuth2Exception e) {
            throw new BadCredentialsException("Could not obtain Access Token", e);
        }

        try {

            final String idToken = accessToken.getAdditionalInformation().get("id_token").toString();
            final Jwt tokenDecoded = JwtHelper.decode(idToken);

            final PortalUser user = getUser(tokenDecoded);

            final HttpSession session = request.getSession();
            session.setAttribute("user", user);

            final List<GrantedAuthority> authList = createUserAuthority(user);
            final PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(user, user.getOpenIdRole(), authList);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return authentication;

        }
        catch (InvalidTokenException e) {
            throw new BadCredentialsException("Could not obtain user details from Access Token", e);
        }
    }

    private PortalUser getUser(Jwt tokenDecoded) throws IOException {

        final Map<String, String> authInfo = new ObjectMapper().readValue(tokenDecoded.getClaims(), Map.class);

        final String username = authInfo.get("sub");
        PortalUser portalUser = portalUserService.getByUsername(username);
        if (portalUser == null) {
            portalUser = new PortalUser();
        }

        final String givenName = authInfo.get("given_name");
        final String familyName = authInfo.get("family_name");
        final String email = authInfo.get("email");
        final String openIdRole = authInfo.get("OLPortal:Role");

        portalUser.setUsername(username);
        portalUser.setGivenName(givenName);
        portalUser.setFamilyName(familyName);
        portalUser.setEmail(email);
        portalUser.setOpenIdRole(openIdRole);

        portalUserService.save(portalUser);

        return portalUser;
    }

    private List<GrantedAuthority> createUserAuthority(PortalUser user) {

        final GrantedAuthority ga = new SimpleGrantedAuthority(user.getOpenIdRole());
        final List<GrantedAuthority> authList = new ArrayList<>();
        authList.add(ga);

        return authList;
    }

    private static class NoOpAuthenticationManager implements AuthenticationManager {
        @Override
        public Authentication authenticate(Authentication authentication) {
            throw new UnsupportedOperationException("No authentication should be done with this AuthenticationManager");
        }
    }

}
