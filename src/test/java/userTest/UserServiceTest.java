package userTest;

import com.wrdao.Application;
import com.wrdao.springboot.user.service.UserService;
import com.wrdao.springboot.user.vo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
//@EnableAutoConfiguration
public class UserServiceTest {
    //private MockMvc mvc;
    @Autowired
    private UserService userService;

    /*@Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new UserService()).build();
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }*/

    @Test
    public void getHello() throws Exception {
        User user = userService.findUserByName("123");
        System.out.println(user.toString());
    }
}
