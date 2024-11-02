package dbinterfaces;
import entities.User;
import java.util.Optional;

public interface UserRepository {
    void store (User user);
    User load (String userID);
}
