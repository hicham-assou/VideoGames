package be.helb.controller;

import be.helb.DAO.UserDao;
import be.helb.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserDao userDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userDao =userDao ;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/signup")
    public void singUp(@RequestBody User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @PostMapping("/singupyy")
    public String getSi(){
        return "ok";
    }

    @PostMapping("/login")
    public void theFakeLogin(@RequestBody User loginRequestModel){
        throw new IllegalStateException("Error");
    }
}
