package com.ruben.crud2.dto;

import com.ruben.crud2.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserMapper {

    IUserMapper mapper = Mappers.getMapper(IUserMapper.class);

    @Mapping(source="name", target="nameSafe")
    @Mapping(source="lastname", target="lastnameSafe")
    @Mapping(source="username", target="usernameSafe")
    @Mapping(source="email", target="emailSafe")
    @Mapping(source="role", target="roleSafe")
    UserDTO userToUserDTO(User user);

    @Mapping(source="nameSafe", target="name")
    @Mapping(source="lastnameSafe", target="lastname")
    @Mapping(source="usernameSafe", target="username")
    @Mapping(source="emailSafe", target="email")
    @Mapping(source="roleSafe", target="role")
    User userDTOToUser(UserDTO userDTO);

}
