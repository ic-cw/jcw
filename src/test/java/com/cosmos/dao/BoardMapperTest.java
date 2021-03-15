package com.cosmos.dao;

import org.junit.*;
import org.junit.runner.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import com.cosmos.mapper.*;

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BoardMapperTest {
	private static final Logger log = LoggerFactory.getLogger(BoardMapperTest.class);

	@Autowired
	private BoardMapper boardMapper;

	@Test
	public void testGetTime() {
		boardMapper.getList().forEach(board -> log.info(board.toString()));
	}
}
