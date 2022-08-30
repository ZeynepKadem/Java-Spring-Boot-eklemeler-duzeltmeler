package com.TemizlikSirketi.proje.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TemizlikSirketi.proje.model.UserModel;
import com.TemizlikSirketi.proje.repository.UserRepository;
import com.TemizlikSirketi.proje.service.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
    private UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserModel> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserModel saveUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Override
    public UserModel getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public UserModel updateUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserModel getUser(String email, Long tcNo) {
        return userRepository.findByUsername(email, tcNo);
    }

    @Override
    public List<UserModel> getUserByRole(boolean isAdmin) {
        return userRepository.getUsernameByisAdmin(isAdmin);
    }
}
