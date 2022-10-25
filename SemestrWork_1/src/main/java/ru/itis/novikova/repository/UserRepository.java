package ru.itis.novikova.repository;


import ru.itis.novikova.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
	void save(UserDTO user);
	Optional<UserDTO> findUserByLogin(String login);
	void updateAvatar(UserDTO userDTO);
	UserDTO findById(int id);
	UserDTO findByNick(String nick);
	List<UserDTO> findAll();
}
