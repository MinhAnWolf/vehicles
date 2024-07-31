package com.example.portal.security;

import com.example.business.entity.Users;
import com.example.business.repository.UsersRepository;
import com.example.business.utils.TokenUtils;
import com.example.common.constant.ApiConstant;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Component
public class Filter extends OncePerRequestFilter {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String reqURI = request.getRequestURI();
        if (reqURI.contains(ApiConstant.API_LOGIN)
                || reqURI.contains(ApiConstant.API_REGISTER)) {
            filterChain.doFilter(request, response);
            return;
        }
        String idUser = request.getHeader("id");
        String authorizationHeader = request.getHeader("Authorization");

        if (Objects.isNull(idUser) || Objects.isNull(authorizationHeader)) {
            unAuthorized(response);
            return;
        }

        Optional<Users> usersFetch = usersRepository.findById(Long.valueOf(idUser));
        String token = authorizationHeader.substring(7);
        Claims claims = tokenUtils.decodeToken(token);
        if (usersFetch.isPresent() && usersFetch.get().getUsername().equals(claims.getSubject())) {
            if(reqURI.contains(ApiConstant.API_VEHICLES + ApiConstant.API_ADD)) {
                if (isAdmin(usersFetch.get().getRole())) {
                    filterChain.doFilter(request, response);
                } else {
                    unAuthorized(response);
                }
                return;
            }
            filterChain.doFilter(request, response);
        } else {
            unAuthorized(response);
        }
    }

    private void unAuthorized(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Access denied");
    }

    private boolean isAdmin(String role) throws IOException {
        return role.equals("ADMIN");
    }
}


