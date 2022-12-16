package com.dct.dasboardcontroltools.security.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dct.dasboardcontroltools.http.Http2Response;
import com.dct.dasboardcontroltools.http.ResponseFactory;
import com.dct.dasboardcontroltools.security.model.TokenInfo;
import com.dct.dasboardcontroltools.security.service.JwtService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/dct/auth")
public class AuthenticateController {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
    private UserDetailsService usuarioDetailsService;
	
	@Autowired
    private JwtService jwtService;
	
	@GetMapping("/getAccessToken")
    public ResponseEntity<?> authenticate(@RequestHeader(value = "Authorization") String request) {
        log.error("****** authenticate ********");
        // adminLuis:passwordSecretDCT -> YWRtaW5MdWlzOnBhc3N3b3JkU2VjcmV0RENU
        // userLuis:passwordSecretDCT -> dXNlckx1aXM6cGFzc3dvcmRTZWNyZXREQ1Q=
        
        try {
            String base64Credentials = request.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);

            String username = credentials.split(":")[0];
            String password = credentials.split(":")[1];

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(username);

            final String jwt = jwtService.generateToken(userDetails);

            TokenInfo tokenInfo = new TokenInfo(jwt);

            return new ResponseFactory<>().getResponse(tokenInfo, Http2Response.OK, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Credenciales incorrectas");
            e.printStackTrace();
            return new ResponseFactory<>().getResponse(Http2Response.DB_USER_NOT_FOUND, HttpStatus.UNAUTHORIZED);
        }
    }
}
