package interfaces;

import bioLabPOJOS.User;

public interface UserManager {

    void createUser(User user);

    User findUserByUsername(String username);

    boolean login(String username, String password);

    User loginAndReturnUser(String username, String password);

    void updateUser(User user);

    void deleteUser(int id);
}