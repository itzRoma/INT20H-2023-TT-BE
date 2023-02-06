package org.sevenorganization.int20h2023ttbe.resource;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "Get all users in application", notes = "By calling this endpoint all users will be returned.")
    public ResponseEntity<List<UserDto>> findAllUsers() {
        final List<UserDto> userDtos = userService.findAllUsers().stream()
                .map(userMapper::userToUserDto)
                .toList();
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "Get user by id", notes = "By calling this endpoint a user with the provided id will be returned.")
    public ResponseEntity<UserDto> findUserById(
            @ApiParam(value = "User id", name = "userId", type = "Long", required = true)
            @PathVariable("userId") Long userId
    ) {
        return ResponseEntity.ok(userMapper.userToUserDto(userService.findUserById(userId)));
    }

    @PutMapping("/{userId}")
    @ApiOperation(
            value = "Update user",
            notes = "By calling this endpoint user with the provided id will be updated using the provided info."
    )
    public ResponseEntity<UserDto> updateUser(
            @ApiParam(value = "User id", name = "userId", type = "Long", required = true)
            @PathVariable("userId") Long userId,
            @ApiParam(value = "New info", name = "userDto", type = "UserDto", required = true)
            @RequestBody UserDto userDto
    ) {
        final UserDto updated = userMapper.userToUserDto(userService.updateUser(
                userService.findUserById(userId),
                userMapper.userDtoToUser(userDto))
        );
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{userId}")
    @ApiOperation(value = "Delete user", notes = "By calling this endpoint user with the provided id will be deleted.")
    public ResponseEntity<Void> deleteUser(
            @ApiParam(value = "User id", name = "userId", type = "Long", required = true)
            @PathVariable("userId") Long userId
    ) {
        userService.deleteUser(userService.findUserById(userId));
        return ResponseEntity.ok().build();
    }
}
