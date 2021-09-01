package pl.javastart.equipy.service;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.model.User;
import pl.javastart.equipy.dto.UserDTO;
import pl.javastart.equipy.mapper.UserMapper;
import pl.javastart.equipy.exception.DuplicatePeselException;
import pl.javastart.equipy.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        return userRepository.
                findAll().
                stream().
                map(UserMapper::toDTO).
                collect(Collectors.toList());
    }

    public List<UserDTO> findAllByLastName(String lastName) {
        return userRepository
                .findAllByLastNameContainingIgnoreCase(lastName)
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO createNewUser(UserDTO user) {
        Optional<User> byPesel = userRepository.findByPesel(user.getPesel());
        byPesel.ifPresent(user1 -> {
                    throw new DuplicatePeselException("Użytkownik o podanym numrze pesel istnieje");
                }
        );
        User userEntity = UserMapper.toUser(user);
        User saveUser = userRepository.save(userEntity);
        return UserMapper.toDTO(saveUser);
    }

    public Optional<UserDTO> findUserById(Long id) {
        return userRepository
                .findById(id)
                .map(UserMapper::toDTO);
    }

    public UserDTO updateExistUser(UserDTO user) {
        Optional<User> byPesel = userRepository.findByPesel(user.getPesel());
        byPesel.ifPresent(user1 -> {
                    if (!user.getId().equals(user1.getId())) //porownuje id uzytkownika do wszystkich uzytkownikow ktorzy znajduja sie w bazie
                        throw new DuplicatePeselException("Użytkownik o podanym numerze pesel juz istnieje");
                }
        );
        User userEntity = UserMapper.toUser(user);
        User saveUser = userRepository.save(userEntity);
        return UserMapper.toDTO(saveUser);
    }
}






