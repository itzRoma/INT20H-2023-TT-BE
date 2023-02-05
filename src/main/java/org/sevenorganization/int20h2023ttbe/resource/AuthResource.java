package org.sevenorganization.int20h2023ttbe.resource;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(
            value = "Sign up",
            notes = "By calling this endpoint new user account will be created and new pair of JWT tokens will be returned."
    )
    public ResponseEntity<AuthResponse> signUn(
            @ApiParam(value = "Sign up info", name = "signUpRequest", type = "SignUpRequest", required = true)
            @RequestBody SignUpRequest signUpRequest
    ) {
        return new ResponseEntity<>(authService.signUp(signUpRequest), HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    @ApiOperation(value = "Sign in", notes = "By calling this endpoint new pair of JWT tokens will be returned.")
    public ResponseEntity<AuthResponse> signIn(
            @ApiParam(value = "Sign up info", name = "signInRequest", type = "SignInRequest", required = true)
            @RequestBody SignInRequest signInRequest
    ) {
        return new ResponseEntity<>(authService.signIn(signInRequest), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    @ApiOperation(
            value = "Refresh token",
            notes = "By calling this endpoint a new JWT access token will be returned, as well as an old refresh one."
    )
    public ResponseEntity<AuthResponse> refreshToken(
            @ApiParam(value = "Refresh token", name = "refreshTokenRequest", type = "RefreshTokenRequest", required = true)
            @RequestBody RefreshTokenRequest refreshTokenRequest
    ) {
        return new ResponseEntity<>(authService.refreshToken(refreshTokenRequest), HttpStatus.OK);
    }
}
