package pl.javastart.equipy.mapper;

import pl.javastart.equipy.model.User;
import pl.javastart.equipy.dto.UserDTO;

public class UserMapper {

    public static UserDTO toDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPesel(user.getPesel());;
        return userDTO;
    }

    public static User toUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPesel(userDTO.getPesel());
        return user;
    }

}
