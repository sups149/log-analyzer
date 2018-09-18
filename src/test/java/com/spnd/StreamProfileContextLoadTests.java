package com.spnd;

import com.spnd.config.LogAnalyzerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { LogAnalyzerConfig.class })
@ActiveProfiles("stream")
public class StreamProfileContextLoadTests {

	@Test
	public void contextLoads() {

	}

}
