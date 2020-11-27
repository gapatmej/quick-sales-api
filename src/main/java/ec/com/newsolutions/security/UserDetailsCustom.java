package ec.com.newsolutions.security;

import ec.com.newsolutions.service.dto.WorkspaceDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserDetailsCustom extends User {

    private final WorkspaceDTO workspaceDTO;

    public UserDetailsCustom(String username, String password, Collection<? extends GrantedAuthority> authorities, WorkspaceDTO workspaceDTO) {
        super(username, password, authorities);
        this.workspaceDTO = workspaceDTO;
    }

    public WorkspaceDTO getWorkspaceDTO() {
        return workspaceDTO;
    }

}
