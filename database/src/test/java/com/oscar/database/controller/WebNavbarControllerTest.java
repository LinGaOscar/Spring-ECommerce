package com.oscar.database.controller;

import com.oscar.database.DatabaseApplication;
import com.oscar.database.domain.WebNavbar;
import com.oscar.database.server.WebNavbarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DatabaseApplication.class)
public class WebNavbarControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private WebNavbarService webNavbarService;

    @Autowired
    private WebNavbarController webNavbarController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.webNavbarController).build();
    }

    @Test
    public void getOne() throws Exception {
        String pidToSearch = "0000";
        WebNavbar expectedWebNavbar = new WebNavbar();
        expectedWebNavbar.setPid(pidToSearch);

        // 模擬服務層的 findOneByPid 方法
        when(webNavbarService.findOneByPid(pidToSearch)).thenReturn(expectedWebNavbar);

        // 設定 MockMvc 要發送的 GET 請求
        mockMvc.perform(get("/webNavbar/{pid}", pidToSearch))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultData.pid").value(pidToSearch));
        //Body = {"statusCode":"200","status":"正常","resultData":{"id":null,"title":null,"sequence":null,"url":null,"pid":"0000","isDelete":null}}
    }
}