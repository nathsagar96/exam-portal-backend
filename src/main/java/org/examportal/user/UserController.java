package org.examportal.user;

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
public class UserController {

  private final UserService userService;

  @GetMapping("/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public UserDto findById(@PathVariable final Long userId) {
    return userService.findById(userId);
  }

  @PutMapping("/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public UserDto update(@PathVariable final Long userId, @RequestBody @Valid final UserDto userDto) {
    return userService.update(userId, userDto);
  }

  @DeleteMapping("/{userId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable final Long userId) {
    userService.deleteById(userId);
  }
}