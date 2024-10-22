package org.examportal.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;

  public UserResponseDto findById(Long userId) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new IllegalStateException("User not found with id " + userId));

    return userMapper.toDto(user);
  }

  public UserResponseDto update(Long userId, UserRequestDto request) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new IllegalStateException("User not found with id " + userId));

    user.setUsername(request.username());
    user.setEmail(request.email());
    user.setPassword(passwordEncoder.encode(request.password()));
    user.setRole(Role.valueOf(request.role()));

    return userMapper.toDto(user);
  }

  public void deleteById(Long userId) {
    userRepository
        .findById(userId)
        .orElseThrow(() -> new IllegalStateException("User not found with id " + userId));
    userRepository.deleteById(userId);
  }
}
