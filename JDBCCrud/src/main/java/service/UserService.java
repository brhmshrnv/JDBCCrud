package service;

import model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    boolean saveUserProduct(int userId, int productId);
    User updateUser(User user);
    boolean removeUser(int userId);
    User findUserById(int userId);
    User findUserProductById(int userId);
    List<User> findUsers();
}
