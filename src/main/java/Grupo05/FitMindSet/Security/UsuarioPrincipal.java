package Grupo05.FitMindSet.Security;

import Grupo05.FitMindSet.domain.Entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UsuarioPrincipal implements UserDetails {

    private Long id;
    private String correo;
    private String contrasena;
    private Collection<?extends GrantedAuthority> authorities;


    public UsuarioPrincipal(Long id, String correo, String contrasena, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.correo = correo;
        this.contrasena = contrasena;
        this.authorities = authorities;
    }
    public static UsuarioPrincipal create(Usuario usuario) {
        String roleName = usuario.getRol().getName().name();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+roleName);
        return new UsuarioPrincipal(
                usuario.getId(),
                usuario.getCorreo(),
                usuario.getContrasena(),
                Collections.singleton(authority)
        );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return contrasena;

    }
    @Override
    public String getUsername() {
        return correo;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;

    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
