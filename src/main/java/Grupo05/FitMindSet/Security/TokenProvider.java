package Grupo05.FitMindSet.Security;

import Grupo05.FitMindSet.domain.Entity.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class TokenProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.validity-in-seconds}")
    private long jwtValidityInSeconds;

    private Key key;
    private JwtParser jwtParser;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
    }

    // Método para generar un token de acceso
    public String createAccessToken(Authentication authentication) {
        String role = authentication.getAuthorities()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No role found"))
                .getAuthority();  // Obtenemos el rol del usuario

        return Jwts.builder()
                .setSubject(authentication.getName())  // Usamos el nombre de usuario como subject
                .claim("role", role)  // Añadimos el rol como claim
                .signWith(key, SignatureAlgorithm.HS512)  // Firmamos el token con la clave y el algoritmo HS512
                .setExpiration(new Date(System.currentTimeMillis() + jwtValidityInSeconds * 1000))  // Establecemos la expiración
                .compact();
    }

    // Generar token de verificación de correo electrónico
    public String createEmailVerificationToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getCorreo())  // El subject es el correo electrónico
                .signWith(key, SignatureAlgorithm.HS512)  // Firmamos el token
                .setExpiration(new Date(System.currentTimeMillis() + 15 * 60 * 1000))  // Válido por 15 minutos
                .compact();
    }

    // Autenticación basada en el token JWT
    public Authentication getAuthentication(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();  // Parseamos el token

        String role = claims.get("role").toString();  // Extraemos el rol del token
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));  // Creamos la lista de autoridades

        User principal = new User(claims.getSubject(), "", authorities);  // Creamos el objeto de usuario
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);  // Retornamos el token de autenticación
    }

    // Obtener el correo electrónico desde el token
    public String getEmailFromToken(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();  // Parseamos el token
        return claims.getSubject();  // El subject es el correo
    }

    // Validar el token JWT
    public boolean validateToken(String token) {
        try {
            jwtParser.parseClaimsJws(token);  // Si el token es válido, simplemente retorna true
            return true;
        } catch (JwtException e) {
            // Si el token no es válido, capturamos la excepción y retornamos false
            return false;
        }
    }

    // Generar un token para restablecimiento de contraseña
    public String createPasswordResetToken(String email) {
        return Jwts.builder()
                .setSubject(email)  // El subject es el correo del usuario
                .signWith(key, SignatureAlgorithm.HS512)  // Firmamos el token
                .setExpiration(new Date(System.currentTimeMillis() + 15 * 60 * 1000))  // Válido por 15 minutos
                .compact();
    }

}


