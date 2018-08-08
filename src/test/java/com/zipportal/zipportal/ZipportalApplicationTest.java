package com.zipportal.zipportal;

import com.zipportal.zipportal.Controller.AssignmentController;
import com.zipportal.zipportal.Controller.PullRequestController;
import com.zipportal.zipportal.Controller.UserController;
import com.zipportal.zipportal.Controller.WebHookController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZipportalApplicationTest {

    @Autowired
    private AssignmentController assignController;
    @Autowired
    private PullRequestController prController;
    @Autowired
    private UserController userController;
    @Autowired
    private WebHookController webHookController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(assignController).isNotNull();
        assertThat(prController).isNotNull();
        assertThat(userController).isNotNull();
        assertThat(webHookController).isNotNull();
    }
}