package ru.itis.security.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.itis.models.Auth;
import ru.itis.repositories.AuthRepository;
import ru.itis.security.details.UserDetailsImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component("customSuccessfulAuthenticationHandler")
public class SuccessfulAuthenticationHandler implements AuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private AuthRepository authRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String cookieValue = UUID.randomUUID().toString();

        Cookie cookie = new Cookie("Auth", cookieValue);
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Auth auth = Auth.builder()
                .cookieValue(cookieValue)
                .user(userDetails.getUser())
                .build();

        authRepository.save(auth);
        redirectStrategy.sendRedirect(request, response, "/profile");
    }
}