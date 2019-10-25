package java;

import com.demoJavaFX.dao.UserDAO;
import com.demoJavaFX.model.User;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    UserDAO dao=new UserDAO();

    @org.junit.jupiter.api.Test
    void save(User user) {
        user=new User("Vasya", "Lobin", "vasya", "password", 1);
        dao.save(user);
    }

    @org.junit.jupiter.api.Test
    void findAll() {
    }
}