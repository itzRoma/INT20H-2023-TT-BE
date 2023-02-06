package org.sevenorganization.int20h2023ttbe.service;

import lombok.RequiredArgsConstructor;
import org.sevenorganization.int20h2023ttbe.domain.dto.AuthResponse;
import org.sevenorganization.int20h2023ttbe.domain.dto.RefreshTokenRequest;
import org.sevenorganization.int20h2023ttbe.domain.dto.SignInRequest;
import org.sevenorganization.int20h2023ttbe.domain.dto.SignUpRequest;
import org.sevenorganization.int20h2023ttbe.domain.entity.RefreshToken;
import org.sevenorganization.int20h2023ttbe.domain.entity.User;
import org.sevenorganization.int20h2023ttbe.security.service.JwtService;
import org.sevenorganization.int20h2023ttbe.security.userdetails.JwtUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    @Transactional
    public AuthResponse signUp(SignUpRequest signUpRequest) {
        User user = userService.saveUser(new User(
                signUpRequest.firstName(), signUpRequest.lastName(), signUpRequest.email(), signUpRequest.password()
        ));
        return generateAuthResponseFromUser(user);
    }

    private AuthResponse generateAuthResponseFromUser(User user) {
        JwtUserDetails jwtUserDetails = JwtUserDetails.fromUser(user);
        String accessToken = jwtService.generateAccessToken(jwtUserDetails);
        String refreshToken = jwtService.generateRefreshToken(jwtUserDetails);
        return new AuthResponse(accessToken, refreshToken);
    }

    @Transactional
    public AuthResponse signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.email(), signInRequest.password()
        ));
        User user = userService.findUserByEmail(signInRequest.email());
        return generateAuthResponseFromUser(user);
    }

    @Transactional
    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        RefreshToken refreshToken = refreshTokenService.findByToken(refreshTokenRequest.refreshToken());
        if (jwtService.validateRefreshToken(refreshToken.getToken())) {
            String accessToken = jwtService.generateAccessToken(JwtUserDetails.fromUser(refreshToken.getUser()));
            return new AuthResponse(accessToken, refreshToken.getToken());
        }
        return null;
    }
}
