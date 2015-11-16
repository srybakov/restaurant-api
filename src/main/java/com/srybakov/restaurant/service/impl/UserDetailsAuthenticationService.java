package com.srybakov.restaurant.service.impl;

import com.srybakov.restaurant.ApplicationConstants;
import com.srybakov.restaurant.domain.model.UserRole;
import com.srybakov.restaurant.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
@Service
public class UserDetailsAuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        com.srybakov.restaurant.domain.model.User user = userRepository.findByName(username);
        if (user == null || user.getRoles().isEmpty()){
            throwAuthException(username);
        }
        List<GrantedAuthority> authorities = buildUserAuthorities(user.getRoles());
        return buildUserForAuthentication(user, authorities);

    }

    private User buildUserForAuthentication(com.srybakov.restaurant.domain.model.User user,
                                            List<GrantedAuthority> authorities) {
        return new User(user.getName(), user.getPassword(), authorities);
    }

    private List<GrantedAuthority> buildUserAuthorities(Set<UserRole> userRoles) {
        Set<GrantedAuthority> setAuthorities = new HashSet<GrantedAuthority>();
        for (UserRole userRole : userRoles) {
            setAuthorities.add(new SimpleGrantedAuthority(userRole.getName()));
        }
        return new ArrayList<GrantedAuthority>(setAuthorities);
    }

    private static void throwAuthException(String username){
        throw new UsernameNotFoundException(String.format(ApplicationConstants.AUTH_FAILED_MESSAGE, username));
    }
}