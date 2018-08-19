package sysTest.service;

import com.wrdao.Application;
import com.wrdao.springboot.sys.service.SysUserRoleService;
import com.wrdao.springboot.sys.vo.SysUserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SysUserRoleServiceTest {
    @Autowired
    SysUserRoleService sysUserRoleService;

    @Test
    public void getRoleIdListByUserId() throws Exception {
        SysUserVo sysUserVo = new SysUserVo();
        sysUserVo.setUserId("123");
        List<String> roleIdList = sysUserRoleService.getRoleIdListByUserId(sysUserVo);

        System.out.println(roleIdList);
    }
}
