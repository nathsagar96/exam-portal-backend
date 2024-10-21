package org.examportal.security;

import lombok.RequiredArgsConstructor;
import org.examportal.user.Role;
import org.examportal.user.User;
import org.examportal.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  @Transactional
  public AuthResponse register(RegisterRequest request) {

    if (userRepository.findByUsername(request.username()).isPresent()) {
      throw new IllegalStateException(
          "User with username " + request.username() + " already exists");
    }

    if (userRepository.findByEmail(request.email()).isPresent()) {
      throw new IllegalStateException("User with email " + request.email() + " already exists");
    }

    User user =
        User.builder()
            .username(request.username())
            .email(request.email())
            .password(passwordEncoder.encode(request.password()))
            .role(Role.valueOf(request.role()))
            .build();

    User savedUser = userRepository.save(user);

    final String token = jwtService.generateToken(savedUser);
    return new AuthResponse(token);
  }

  public AuthResponse authenticate(AuthRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.username(), request.password()));
    var User =
        userRepository
            .findByUsername(request.username())
            .orElseThrow(
                () ->
                    new IllegalStateException(
                        "User with username " + request.username() + " not found"));
    final String token = jwtService.generateToken(User);
    return new AuthResponse(token);
  }
}
