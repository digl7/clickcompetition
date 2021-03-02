package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.security;


import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Player;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.PlayerRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.hibernate.Hibernate;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.EntityNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class JWTAuthorizationFilter extends OncePerRequestFilter {
    // OJO: NO @Autowired
    private PlayerRepository playerRepository;

    /*******************************************************************************************
     * Se modifica el constructor para recibir el contexto porque por medidas de seguridad
     * los filtros se ejecutan en un contexto diferente a la aplicación principal
     * y se obtiene el repositorio de usuarios del contexto en vez de @Autowired
     */
    public JWTAuthorizationFilter(ApplicationContext applicationContext) {
        this.playerRepository = applicationContext.getBean(PlayerRepository.class);
    }

    /*******************************************************************************************
     Filtro simple comprobando 'OK' en authorization,
     SÓLO para demostrar mecanismo de autenticación
     */
    protected void simpleDemoFilter(HttpServletRequest request) {
        String encabezado = request.getHeader("Authorization");
        if(encabezado != null && encabezado.equals("OK"))
            simpleSpringAuthentication();
        else
            SecurityContextHolder.clearContext();
    }

    /*******************************************************************************************
     Autenticación simple sin recuperar información del token,
     SÓLO para demostrar mecanismo de autenticación
     */
    private void simpleSpringAuthentication() {
        List<String> authoritiesText = new ArrayList<>(Arrays.asList("ROLE_ADMIN", "ROLE_GOD"));

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken("lalalala", null, sinLambdas(authoritiesText));
    }

    // Lista de permisos sin 'lambdas'
    private List<SimpleGrantedAuthority> sinLambdas(List<String> textList) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for( String text: textList ) {
            authorities.add(new SimpleGrantedAuthority(text));
        }
        return authorities;
    }

    // Lista de permisos usando 'lambdas'
    private List<SimpleGrantedAuthority> conLambdas(List<String> textList) {
        return textList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    /******************************************************************************************/

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // TODO: No keys hardcoded!!!
        if(httpServletRequest.getHeader("Authorization")!=null) {
            if (!httpServletRequest.getHeader("Authorization").startsWith("Bearer ")) {
                SecurityContextHolder.clearContext();
            } else {
                String jwtToken = httpServletRequest.getHeader("Authorization").replace("Bearer ", "");
                try {
                    Claims claims = Jwts.parser().setSigningKey("pestillo".getBytes()).parseClaimsJws(jwtToken).getBody();
                    String username = claims.getSubject();
                    Player player = playerRepository.findPlayerByUsername(username)
                            .orElseThrow(EntityNotFoundException::new);
                    if(!player.getToken().equals(jwtToken))
                        throw new Exception();
                    Hibernate.initialize(player.getRole());
                    setUpSpringAuthentication(player);
                } catch (Exception e) {
                    SecurityContextHolder.clearContext();
                }
            }
        } else {
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void setUpSpringAuthentication(Player player) {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(player.getRole());
        Hibernate.initialize(player.getRole());
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(player, null,
                        roles);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
