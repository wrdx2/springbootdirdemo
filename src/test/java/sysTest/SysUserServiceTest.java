package sysTest;

import com.wrdao.Application;
import com.wrdao.springboot.sys.service.SysUserService;
import com.wrdao.springboot.sys.vo.SysUserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SysUserServiceTest {
    @Autowired
    SysUserService sysUserService;

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
    public void getHello() throws Exception{
        SysUserVo sysUserVo = sysUserService.findByUsername("123");
        System.out.println(sysUserVo.toString());
    }

    @Test
    public void save() throws  Exception {
        SysUserVo sysUserVo = new SysUserVo();
        sysUserVo.setUsername("234");
        sysUserVo = sysUserService.save(sysUserVo);
        System.out.println(sysUserVo.toString());
    }
}
