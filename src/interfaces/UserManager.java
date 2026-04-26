package interfaces;

import bioLabPOJOS.User;

public interface UserManager {
    public void createUser(User user);
    public User findUserByEmail(String email);
}
