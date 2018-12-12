package gov.utah.hs.ol.portal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.Arrays;

@Configuration
@EnableOAuth2Client
@PropertySource("classpath:openid-${targetEnvironment:dev}.properties")
public class OpenIdConnectConfig {

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Value("${security.oauth2.client.access-token-uri}")
    private String accessTokenUri;

    @Value("${security.oauth2.client.user-authorization-uri}")
    private String userAuthorizationUri;

    @Value("${security.oauth2.client.pre-established-redirect-uri}")
    private String preEstablishedRedirectUri;

    @Value("${security.oauth2.client.client-authentication-scheme}")
    private String clientAuthenticationScheme;

    @Value("${security.oauth2.client.scope}")
    private String scope;

    @Value("${security.oauth2.client.use-current-uri}")
    private Boolean useCurrentUri;

    @Value("${security.oauth2.client.id}")
    private String id;

    @Bean
    public OAuth2ProtectedResourceDetails authorizationCodeResourceDetails() {

        final AuthorizationCodeResourceDetails authorizationCodeResourceDetails = new AuthorizationCodeResourceDetails();
        authorizationCodeResourceDetails.setClientId(clientId);
        authorizationCodeResourceDetails.setClientSecret(clientSecret);
        authorizationCodeResourceDetails.setAccessTokenUri(accessTokenUri);
        authorizationCodeResourceDetails.setUserAuthorizationUri(userAuthorizationUri);
        authorizationCodeResourceDetails.setScope(Arrays.asList(scope.split(",")));
        authorizationCodeResourceDetails.setPreEstablishedRedirectUri(preEstablishedRedirectUri);
        authorizationCodeResourceDetails.setUseCurrentUri(useCurrentUri);

        authorizationCodeResourceDetails.setClientAuthenticationScheme(AuthenticationScheme.valueOf(clientAuthenticationScheme));
        authorizationCodeResourceDetails.setId(id);

        return authorizationCodeResourceDetails;
    }

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate(@Qualifier("oauth2ClientContext") OAuth2ClientContext clientContext) {
        final OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(authorizationCodeResourceDetails(), clientContext);
        final AccessTokenProviderChain providerChain = new AccessTokenProviderChain(Arrays.asList(new AuthorizationCodeAccessTokenProvider()));
        restTemplate.setAccessTokenProvider(providerChain);
        return restTemplate;
    }

}
