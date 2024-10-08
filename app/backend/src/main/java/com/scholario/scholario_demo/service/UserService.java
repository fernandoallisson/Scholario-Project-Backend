package com.scholario.scholario_demo.service;

import com.scholario.scholario_demo.entiity.User;
import com.scholario.scholario_demo.exception.user.UserException;
import com.scholario.scholario_demo.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> findAllUser(int pageNumber, int pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);

    Page<User> page = userRepository.findAll(pageable);

    return page.toList();
  }

  public User createUser(User user) {
    return userRepository.save(user);
  }

  public User findUserById(Long id) throws UserException{
    return userRepository.findById(id).orElseThrow(() -> UserException.notFound("User", id));
  }

  public User updateUser(Long id, User user) {
    User userFound = findUserById(id);

    userFound.setName(user.getName());
    userFound.setEmail(user.getEmail());
    userFound.setPassword(user.getPassword());
    userFound.setAddress(user.getAddress());
    userFound.setPhone(user.getPhone());
    userFound.setRole(user.getRole());


    return userRepository.save(userFound);
  }

  public void deleteUserById(Long id) {
    userRepository.deleteById(id);
  }
}
