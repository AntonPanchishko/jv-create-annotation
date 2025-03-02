package core.basesyntax.dao;

import core.basesyntax.db.Storage;
import core.basesyntax.model.User;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public void addUser(User user) {
        Storage.users.add(user);
    }

    @Override
    public List<User> getAllUsers() {
        return Storage.users;
    }
}
