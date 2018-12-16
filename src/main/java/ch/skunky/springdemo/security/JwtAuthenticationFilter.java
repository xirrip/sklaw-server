//package ch.skunky.springdemo.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.security.SignatureException;
//import java.util.Arrays;
//
//import static ch.skunky.springdemo.security.JwtConstants.HEADER_STRING;
//import static ch.skunky.springdemo.security.JwtConstants.TOKEN_PREFIX;
//
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//        String header = req.getHeader(HEADER_STRING);
//        String username = null;
//        String authToken = null;
//        if (header != null && header.startsWith(TOKEN_PREFIX)) {
//            authToken = header.replace(TOKEN_PREFIX,"");
//            try {
//                username = jwtTokenUtil.getUsernameFromToken(authToken);
//            } catch (IllegalArgumentException e) {
//                logger.error("an error occured during getting username from token", e);
//            } catch (ExpiredJwtException e) {
//                logger.warn("the token is expired and not valid anymore", e);
//            } catch(SignatureException e){
//                logger.error("Authentication Failed. Username or Password not valid.");
//            }
//        } else {
//            logger.warn("couldn't find bearer string, will ignore the header");
//        }
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
//                logger.info("authenticated user " + username + ", setting security context");
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//
//        chain.doFilter(req, res);
//    }
//}