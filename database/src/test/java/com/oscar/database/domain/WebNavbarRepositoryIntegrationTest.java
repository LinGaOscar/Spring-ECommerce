package com.oscar.database.domain;

import com.oscar.database.config.DaoConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = DaoConfig.class)
public class WebNavbarRepositoryIntegrationTest {

    @Autowired
    private WebNavbarRepository webNavbarRepository;

    @Before
    public void init() {
    }

    @Test
    public void saveOne() {
        WebNavbar webNavbar = new WebNavbar();
        webNavbar.setSequence(0L);
        webNavbar.setTitle("測試");
        webNavbar.setUrl("");
        webNavbar.setPid("9999");
        webNavbar.setIsDelete("N");
        webNavbarRepository.save(webNavbar);

        String pidToSearch = "9999";
        WebNavbar foundWebNavbar = webNavbarRepository.findByPid(pidToSearch);

        // 驗證結果
        assertNotNull(foundWebNavbar);
        assertEquals(pidToSearch, foundWebNavbar.getPid());
    }

    @Test
    public void findByPid() {
        String pidToSearch = "0000";
        WebNavbar foundWebNavbar = webNavbarRepository.findByPid(pidToSearch);
        // 驗證結果
        assertNotNull(foundWebNavbar);
        assertEquals(pidToSearch, foundWebNavbar.getPid());
    }
}
