package pl.javastart.equipy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.javastart.equipy.dto.UserDTO;
import pl.javastart.equipy.service.UserService;

import java.util.List;

@RestController
@RequestMapping( "/api/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    List<UserDTO> findUsers(@PathVariable(required = false) String lastName) {
        if (lastName != null)
            return userService.findAllByLastName(lastName);
        else {
            return userService.findAll();
        }
    }

    @PostMapping("/user-add")
    ResponseEntity<UserDTO> saveNewUser(@RequestBody UserDTO user) {
        if (user.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST ,"Błąd ustawienia id ręcznie");
        }
        UserDTO newUser = userService.createNewUser(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDTO> getUser(@PathVariable (required = false) Long id) {
        return userService.
                findUserById(id).
                map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO user) {
        if(!id.equals(user.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Numer id uzytkownika nie jest identyczny do id sciezki");
        }else {
            UserDTO userDTO = userService.updateExistUser(user);
            return ResponseEntity.ok(userDTO);
        }
    }
}



