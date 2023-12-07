package com.oscar.database.service;

import com.oscar.database.DatabaseApplication;
import com.oscar.database.domain.WebNavbar;
import com.oscar.database.domain.WebNavbarRepository;
import com.oscar.database.server.WebNavbarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DatabaseApplication.class)
public class WebNavbarServiceTest {
    @Autowired
    private WebNavbarService webNavbarService;

    @MockBean
    private WebNavbarRepository webNavbarRepository;

    @Before
    public void init() {
    }

    @Test
    public void findByPid() {
        // 模擬 findByPid 方法的行為
        String pidToSearch = "0000";
        WebNavbar expectedWebNavbar = new WebNavbar();
        expectedWebNavbar.setPid(pidToSearch);
        when(webNavbarRepository.findByPid(pidToSearch)).thenReturn(expectedWebNavbar);

        // 呼叫服務層的 findByPid 方法
        WebNavbar foundWebNavbar = webNavbarService.findOneByPid(pidToSearch);

        // 驗證結果
        assertNotNull(foundWebNavbar);
        assertEquals(pidToSearch, foundWebNavbar.getPid());
    }
}
