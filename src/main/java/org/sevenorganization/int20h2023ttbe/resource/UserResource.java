package org.sevenorganization.int20h2023ttbe.resource;

import lombok.RequiredArgsConstructor;
import org.sevenorganization.int20h2023ttbe.domain.dto.UserCreationRequest;
import org.sevenorganization.int20h2023ttbe.domain.dto.UserDto;
import org.sevenorganization.int20h2023ttbe.service.UserService;
import org.sevenorganization.int20h2023ttbe.util.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserResource {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserCreationRequest userCreationRequest) {
        final UserDto created = userMapper.userToUserDto(userService.createUser(
                userMapper.userCteationRequestToUser(userCreationRequest))
        );
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> readAllUsers() {
        final List<UserDto> userDtos = userService.readAllUsers().stream()
                .map(userMapper::userToUserDto)
                .toList();
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> readUserById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userMapper.userToUserDto(userService.readUserById(userId)));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") Long userId, @RequestBody UserDto userDto) {
        final UserDto updated = userMapper.userToUserDto(userService.updateUser(
                userService.readUserById(userId),
                userMapper.userDtoToUser(userDto))
        );
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userService.readUserById(userId));
        return ResponseEntity.ok().build();
    }
}
