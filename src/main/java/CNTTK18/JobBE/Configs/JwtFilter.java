package CNTTK18.JobBE.Configs;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import CNTTK18.JobBE.Services.JwtService;
import CNTTK18.JobBE.Services.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final ApplicationContext context;

    public JwtFilter(JwtService jwtService, ApplicationContext context) {
        this.jwtService = jwtService;
        this.context = context;
    }

    @Override
    protected void doFilterInternal(@SuppressWarnings("null") HttpServletRequest request, @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") FilterChain filterChain) throws ServletException, IOException 
    {

        //Take token from request header
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer"))
        {
            //remove Bearer from token
            token = authHeader.substring(7);
            //Extract username from token
            username = jwtService.extractUserName(token);
        }

        //getAuthentication to confirm that user is not authenticated
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            //Take userDetails from current context => get User from username
            UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails))
            {
                UsernamePasswordAuthenticationToken authToken = 
                                       new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                //Add request details to authToken
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //Confirm that user is authenticated
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response); // pass the request
    }
}
