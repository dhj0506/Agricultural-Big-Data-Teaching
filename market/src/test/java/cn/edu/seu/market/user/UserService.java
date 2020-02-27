package cn.edu.seu.market.user;

import cn.edu.seu.market.user.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserService {
    @Autowired
    private IUserService userService;

    @Test
    public void countUser() {
        userService.count();
    }

}
