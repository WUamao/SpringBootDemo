package com.amao.springboot;

import com.amao.springboot.domain.Department;
import com.amao.springboot.domain.Employee;
import com.amao.springboot.mapper.DepartmentMapper;
import com.amao.springboot.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootCacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;  //操作k-v都是字符串的

    @Autowired
    RedisTemplate redisTemplate; //k-v都是对象的

    @Autowired
    RedisTemplate myRedisTemplate;

    @Autowired
    RedisTemplate deptRedisTemplate;

//    @Autowired
//    RedisTemplate<Object, Employee> employeeRedisTemplate;

    /**
     * Redis常见的五大数据类型
     *  String（字符串）、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
     *  stringRedisTemplate.opsForValue()[String（字符串）]
     *  stringRedisTemplate.opsForList()[List（列表）]
     *  stringRedisTemplate.opsForSet()[Set（集合）]
     *  stringRedisTemplate.opsForHash()[Hash（散列）]
     *  stringRedisTemplate.opsForZSet()[ZSet（有序集合）]
     */
    @Test
    void contextLoads1() {
        //给redis中保存数据
        //stringRedisTemplate.opsForValue().append("msg","helloo");
//        String msg = stringRedisTemplate.opsForValue().get("msg");
//        System.out.println(msg);
//        stringRedisTemplate.opsForList().leftPush("mylist","你好");
//        stringRedisTemplate.opsForList().leftPush("mylist","张三");
        List<String> list = stringRedisTemplate.opsForList().range("mylist", 0, 7);
        for (String s : list) {
            System.out.println(s);
        }
    }

    @Test
    void contextLoads2() {
        Employee employee = employeeMapper.getEmpById(1);
        //默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
        //redisTemplate.opsForValue().set("emp",employee);
        //1、将数据以json的方式保存
        //(1)自己将对象转为json
        //(2)redisTemplate默认的序列化规则；改变默认的序列化规则；
        //System.out.println(redisTemplate.opsForValue().get("emp"));
//        myRedisTemplate.opsForValue().set("emp",employee);
        Object emp = myRedisTemplate.opsForValue().get("emp");
        System.out.println(emp);
    }

    @Test
    void contextLoads3() {
        Department deptartment = departmentMapper.getDeptById(1);

        //myRedisTemplate.opsForValue().set("dept",deptartment);

        Object dept = myRedisTemplate.opsForValue().get("dept");
        System.out.println(dept);
    }

    @Test
    void contextLoads4() {
        Department deptartment = departmentMapper.getDeptById(1);

       // deptRedisTemplate.opsForValue().set("dept",deptartment);

        Department dept = (Department) deptRedisTemplate.opsForValue().get("dept");
        System.out.println(dept);
    }

}
