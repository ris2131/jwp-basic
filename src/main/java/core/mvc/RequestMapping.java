package core.mvc;

import next.controller.*;


import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private Map<String, Controller> mappings = new HashMap<>();
    public void initMapping(){
        mappings.put("/", new HomeController());
        mappings.put("/users/form", new ForwardController("/user/form.jsp"));
        mappings.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        mappings.put("/users", new ListUserController());
        mappings.put("/users/login", new LoginController());
        mappings.put("/users/profile", new ProfileController());
        mappings.put("/users/logout", new LogoutController());
        mappings.put("/users/create", new CreateUserController());
        mappings.put("/users/update", new UpdateUserController());
        //mappings.put("/users/updateForm", new UpdateFormUserController());

    }
    public Controller findController(String name){
        return mappings.get(name);
    }
}
