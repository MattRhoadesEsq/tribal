package com.tribal.application;

import com.tribal.application.dto.UserDTO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class UserRepository {


    /**
     * Add a new user to the repository with the specified email.
     * @param email
     * @return
     */
    public User add(String email) {
        User u = new User(email);
        theUsers.add(u);
        return u;
    }

    /**
     * Get a user from a repository by id.
     *
     * Return null if the id does not exist.
     *
     * @param id
     * @return
     */
    public User getById(String id) {
        for (User u : theUsers) {
            if (id.equals(""+ u.getId())) {
                return u;
            }
        }
        return null;
    }

    /**
     * Get a user from a repository by email.
     *
     * Return null if the id does not exist.
     *
     * @param email
     * @return
     */
    public User getByEmail(String email) {
        for (User u : theUsers) {
            if (email.equals(u.getEmail())) {
                return u;
            }
        }
        return null;
    }

    /**
     * Get all users.
     *
     * @return
     */
    public Set<User> getAll() {
        return theUsers;
    }

    /**
     * Delete a user from a repository by id.
     *
     * @param id
     * @return
     */
    public void delete(String id) {
        for (Iterator<User> iterator = theUsers.iterator(); iterator.hasNext();) {
            User u =  iterator.next();
            if (id.equals("" + u.getId())) {
                iterator.remove();
            }
        }
    }

    private static final Logger logger = LogManager.getLogger(UserRepository.class);
    private static UserRepository theRepository = null;
    private Set<User> theUsers = new HashSet<>();


    public static UserRepository instance() {
        if (theRepository != null) {
            return theRepository;
        }
        theRepository = new UserRepository();
        return theRepository;
    }


    private UserRepository() {
        logger.debug("new " + this.getClass().getCanonicalName());
    }
}
