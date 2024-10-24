package org.examportal.user;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

  private final UserService userService;

  @GetMapping("/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public UserResponseDto findById(@PathVariable final Long userId) {
    return userService.findById(userId);
  }

  @PutMapping("/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public UserResponseDto update(
      @PathVariable final Long userId, @RequestBody @Valid final UserRequestDto request) {
    return userService.update(userId, request);
  }

  @DeleteMapping("/{userId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable final Long userId) {
    userService.deleteById(userId);
  }
}
