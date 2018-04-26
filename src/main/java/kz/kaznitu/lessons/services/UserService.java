package kz.kaznitu.lessons.services;

import kz.kaznitu.lessons.models.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}
