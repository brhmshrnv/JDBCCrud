package service.impl;

import model.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public User saveUser(User user) {
        User u = userRepository.saveUser(user);
        return u;
    }

    @Override
    public boolean saveUserProduct(int userId, int productId) {

        boolean check = userRepository.saveUserProduct(userId,productId);
        return check;
    }

    @Override
    public User updateUser(User user) {
        User u = userRepository.updateUser(user);
        return u;
    }

    @Override
    public boolean removeUser(int userId) {

        boolean check= userRepository.removeUser(userId);
        return check;
    }

    @Override
    public User findUserById(int userId) {
        User u = userRepository.findUserById(userId);
        return u;
    }

    @Override
    public User findUserProductById(int userId) {
        User u = userRepository.findUserById(userId);
        return u;
    }

    @Override
    public List<User> findUsers() {

        List<User>users = userRepository.findUsers();

        return users;
    }
}
