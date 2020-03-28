package com.amao.springboot;

import com.amao.springboot.dao.DepartmentMapper;
import com.amao.springboot.domain.Department;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class SpringbootDruidApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    DepartmentMapper departmentMapper;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void contextLoads() throws SQLException {

        System.out.println(dataSource.getClass());

        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();

    }

    @Test
    void test(){
        Department department = departmentMapper.getDepartmentById(1);
        System.out.println(department);
    }

    @Test
    public void testLog(){
        //日志的级别；
        //由低到高 trace<debug<info<warn<error
        //可以调整输出的日志级别；日志就只会在这个级别以以后的高级别生效
        logger.trace("这是trace日志");
        logger.debug("这是debug日志");
        logger.info("这是info日志");
        logger.warn("这是warn日志");
        logger.error("这是error日志");
    }

}
