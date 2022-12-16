package com.dct.dasboardcontroltools.security.service;

import java.util.Map;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String, String> usuarios = Map.of(
                "userLuis", "USER",
                "adminLuis", "ADMIN"
        );
//		Map<String, String> usuarios = Map.of(
//                "noseLuis", "USER",
//                "adnoseLuis", "ADMIN"
//        );

        var rol = usuarios.get(username);
        if(rol != null) {
            User.UserBuilder userBuilder = User.withUsername(username);

//          "passwordSecretDCT" => [BCrypt]*12 => "$2y$12$1drmqbnl7oOhqmU3ypQ.COgKajbcIJ67BzFa0i1sFddcwQxUs8BzW"
            String encryptPassword = "$2y$12$1drmqbnl7oOhqmU3ypQ.COgKajbcIJ67BzFa0i1sFddcwQxUs8BzW";

            userBuilder.password(encryptPassword).roles(rol);
            return userBuilder.build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

}
