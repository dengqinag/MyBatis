package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.pojo.EmpExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MBGTest {

    @Test
    public void testMBG(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            //查询所有数据
//            List<Emp> list = mapper.selectByExample(null);
//            for (Emp emp:list){
//                System.out.println(emp);
//            }
            //根据条件查询
//            EmpExample example = new EmpExample();
//            example.createCriteria().andEmpNameEqualTo("周八").andAgeGreaterThan(20);
//            example.or().andDidIsNotNull();
//            List<Emp> list = mapper.selectByExample(example);
//            for (Emp emp:list) {
//                System.out.println(emp);
//            }
            //修改
            mapper.updateByPrimaryKey(new Emp(19,"admin",23,null,null,2));
            //修改，此时如果字段赋值为null，字段保留原来的值
            mapper.updateByPrimaryKeySelective(new Emp(19,"admin",23,null,null,2));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
