package core.db;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;

import next.support.context.model.User;

public class DataBase {
    private static Map<String, User> users = Maps.newHashMap();

    public static void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public static User findUserById(String userId) {
        return users.get(userId);
    }

    public static void updateUser(String user,User modifyUser){
        users.replace(user, modifyUser);
    }
    public static Collection<User> findAll() {
        return users.values();
    }
}
