package CNTTK18.JobBE.Configs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import CNTTK18.JobBE.Exception.InvalidTokenException;
import CNTTK18.JobBE.Services.JwtService;
import CNTTK18.JobBE.Services.MyUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
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

        try {
            //Take token from request header
            String authHeader = request.getHeader("Authorization");
            String token = null;
            String username = null;

            if (authHeader != null && authHeader.startsWith("Bearer"))
            {
                //remove Bearer from token
                token = authHeader.substring(7);
                //Extract username from token
                try {
                    username = jwtService.extractUserName(token);
                }
                catch (UsernameNotFoundException e)
                {
                    handleAuthError(response, HttpStatus.UNAUTHORIZED, e.getMessage());
                    return;
                }
                catch (InvalidTokenException e)
                {
                    handleAuthError(response, HttpStatus.UNAUTHORIZED, e.getMessage());
                    return;
                }
                catch (ExpiredJwtException e)
                {
                    handleAuthError(response, HttpStatus.UNAUTHORIZED, e.getMessage());
                    return;
                }
                catch (MalformedJwtException e)
                {
                    handleAuthError(response, HttpStatus.BAD_REQUEST, e.getMessage());
                    return;
                }
                catch (Exception e)
                {
                    handleAuthError(response, HttpStatus.UNAUTHORIZED, e.getMessage());
                    return;
                }
            }

            //getAuthentication to confirm that user is not authenticated
            if (username != null && username != "" && SecurityContextHolder.getContext().getAuthentication() == null)
            {
                //Take userDetails from current context => get User from username
                UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authToken = 
                                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                //Add request details to authToken
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //Confirm that user is authenticated
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            filterChain.doFilter(request, response); // pass the request
        }
        catch (Exception e) {
            handleAuthError(response, HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
        }
    }

    // Phương thức hỗ trợ để xử lý lỗi xác thực và trả về response phù hợp
    private void handleAuthError(HttpServletResponse response, HttpStatus status, String message) throws JsonProcessingException, IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");
        
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("status", status.value());
        errorDetails.put("error", status.getReasonPhrase());
        errorDetails.put("message", message);
        
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(errorDetails));
    }
}
