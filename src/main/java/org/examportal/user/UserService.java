package org.examportal.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserDto findById(Long userId) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new IllegalStateException("User not found with id " + userId));

    return new UserDto(user.getId(), user.getUsername(), user.getEmail(), null, user.getRole());
  }

  public UserDto update(Long userId, @Valid UserDto userDto) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new IllegalStateException("User not found with id " + userId));

    user.setEmail(userDto.email());
    user.setPassword(passwordEncoder.encode(userDto.password()));
    user.setRole(userDto.role());

    return new UserDto(user.getId(), user.getUsername(), user.getEmail(), null, user.getRole());
  }

  public void deleteById(Long userId) {
    userRepository
        .findById(userId)
        .orElseThrow(() -> new IllegalStateException("User not found with id " + userId));
    userRepository.deleteById(userId);
  }
}
