package com.TemizlikSirketi.proje.service;

import com.TemizlikSirketi.proje.model.UserModel;

import java.util.List;



public interface UserService {

	List<UserModel> getAllUser();
    UserModel saveUser(UserModel userModel);
    UserModel getUserById(Long id);
    UserModel updateUser(UserModel userModel);
    void deleteUserById(Long id);
    UserModel getUser(String name, Long password);
    List<UserModel> getUserByRole(boolean isAdmin);
}
