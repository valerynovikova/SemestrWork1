package ru.itis.novikova.service;

import ru.itis.novikova.dto.UserDTO;

import java.util.List;

public interface UserService {
	void updateAvatar(UserDTO userDTO);

	UserDTO findUserByLogin(String login);

	UserDTO findUserById(int id);

	UserDTO findUserByNick(String id);

	List<UserDTO> findAll();
}
