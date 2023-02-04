package org.sevenorganization.int20h2023ttbe.resource;

import lombok.RequiredArgsConstructor;
import org.sevenorganization.int20h2023ttbe.domain.dto.UserDto;
import org.sevenorganization.int20h2023ttbe.service.UserService;
import org.sevenorganization.int20h2023ttbe.util.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserResource {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> readAllUsers() {
        final List<UserDto> userDtos = userService.findAllUsers().stream()
                .map(userMapper::userToUserDto)
                .toList();
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> readUserById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userMapper.userToUserDto(userService.findUserById(userId)));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") Long userId, @RequestBody UserDto userDto) {
        final UserDto updated = userMapper.userToUserDto(userService.updateUser(
                userService.findUserById(userId),
                userMapper.userDtoToUser(userDto))
        );
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userService.findUserById(userId));
        return ResponseEntity.ok().build();
    }
}
