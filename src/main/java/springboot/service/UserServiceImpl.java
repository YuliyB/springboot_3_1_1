package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.model.User;
import springboot.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User userDB = userRepository.findById(user.getId()).orElse(null);
        userDB.setAge(user.getAge());
        userDB.setName(user.getName());
        userDB.setLastName(user.getLastName());
        return userRepository.save(userDB);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }


}
