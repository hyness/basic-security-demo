package org.hyness.basic.security.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasicSecurityDemoApplication.class)
@WebAppConfiguration
public class BasicSecurityDemoApplicationTests {

	@Test
	public void contextLoads() {
	}

}
