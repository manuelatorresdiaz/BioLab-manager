package interfaces;

import bioLabPOJOS.User;
/**
 * Interface defining the security and identity management contract.
 * Handles user persistence, credential verification, and session initialization.
 */
public interface UserManager {
	/**
     * Persists a new user in the system.
     * @param user The User entity containing credentials and assigned role.
     */
    void createUser(User user);
    /**
     * Retrieves user details based on their unique login name.
     * @param username The security identifier.
     * @return The User object if found.
     */
    User findUserByUsername(String username);
    /**
     * Validates credentials for simple authentication checks.
     * @param username The login name.
     * @param password The raw password (to be verified against hashed storage).
     * @return True if credentials are valid.
     */
    boolean login(String username, String password);
    /**
     * Authenticates a user and retrieves their full profile, including Roles.
     * This is the primary method used by LoginGUI to establish session context.
     * @return The authenticated User object, or null if authentication fails.
     */
    User loginAndReturnUser(String username, String password);
    /**
     * Updates account details such as password or assigned roles.
     */
    void updateUser(User user);
    /**
     * Removes a user account from the system registry.
     */
    void deleteUser(int id);
}