package response;

import enums.RoleUser;
import model.User;
import repository.BaseRepository;

import java.util.List;

public abstract class UserRepository implements BaseRepository<User, String, List<User>> {
    protected abstract List<User> getUsers(RoleUser roleUser);

}
