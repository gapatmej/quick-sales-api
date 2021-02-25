package ec.com.newsolutions.security.jwt;

import ec.com.newsolutions.service.dto.OrganizationDTO;
import org.apache.commons.io.IOUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Filters incoming requests and installs a Spring Security principal if a header corresponding to a valid user is
 * found.
 */
public class JWTFilter extends GenericFilterBean {

    private static final List<String> urisNotFilterOrganization = new ArrayList<String>(){
        {
            add("api/user");
        }
    };
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String PATTERN_ORGANIZATION_REPLACE = "\"organizationId\":[^,}]*";
    public static final String AUTHORIZATION_TOKEN = "access_token";

    private final TokenProvider tokenProvider;

    public JWTFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = resolveToken(httpServletRequest);
        if (StringUtils.hasText(jwt) && this.tokenProvider.validateToken(jwt)) {
            Authentication authentication = this.tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            if(validateRequestURIForOrganizationFilter(httpServletRequest)){
                OrganizationDTO organizationDTO = (OrganizationDTO) this.tokenProvider.getClaimnWorkspaceById(jwt,"organization");
                if(!StringUtils.isEmpty(organizationDTO)){
                    XSSRequestWrapper wrappedRequest = new XSSRequestWrapper(
                        (HttpServletRequest) servletRequest);

                    String body = IOUtils.toString(wrappedRequest.getReader());
                    body = body.replaceAll(PATTERN_ORGANIZATION_REPLACE,"\"organizationId\": "+organizationDTO.getId().toString());
                    wrappedRequest.resetInputStream(body.getBytes());
                    servletRequest = wrappedRequest;
                }
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    private boolean validateRequestURIForOrganizationFilter(HttpServletRequest httpServletRequest){

        if(urisNotFilterOrganization.contains(httpServletRequest.getRequestURL()) ||
            (!"POST".equals(httpServletRequest.getMethod()) && !"PUT".equals(httpServletRequest.getMethod())))
            return false;

        return true;

    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        String jwt = request.getParameter(AUTHORIZATION_TOKEN);
        if (StringUtils.hasText(jwt)) {
            return jwt;
        }
        return null;
    }
}
