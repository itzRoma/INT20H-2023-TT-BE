package org.sevenorganization.int20h2023ttbe.util.mapper;

import org.mapstruct.Mapper;
import org.sevenorganization.int20h2023ttbe.domain.dto.UserCreationRequest;
import org.sevenorganization.int20h2023ttbe.domain.dto.UserDto;
import org.sevenorganization.int20h2023ttbe.domain.entity.User;

@Mapper
public interface UserMapper {

    User userCteationRequestToUser(UserCreationRequest userCreationRequest);

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
