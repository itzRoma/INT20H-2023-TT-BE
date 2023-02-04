package org.sevenorganization.int20h2023ttbe.resource;

import lombok.RequiredArgsConstructor;
import org.sevenorganization.int20h2023ttbe.domain.dto.AuthResponse;
import org.sevenorganization.int20h2023ttbe.domain.dto.RefreshTokenRequest;
import org.sevenorganization.int20h2023ttbe.domain.dto.SignInRequest;
import org.sevenorganization.int20h2023ttbe.domain.dto.SignUpRequest;
import org.sevenorganization.int20h2023ttbe.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthResource {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> signUn(@RequestBody SignUpRequest signUpRequest) {
        return new ResponseEntity<>(authService.signUp(signUpRequest), HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> signIn(@RequestBody SignInRequest signInRequest) {
        return new ResponseEntity<>(authService.signIn(signInRequest), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return new ResponseEntity<>(authService.refreshToken(refreshTokenRequest), HttpStatus.OK);
    }
}
