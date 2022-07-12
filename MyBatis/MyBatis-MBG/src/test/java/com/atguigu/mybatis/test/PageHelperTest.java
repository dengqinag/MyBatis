package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PageHelperTest {

    @Test
    public void testPageHelper(){
        /**
         * limit index,pageSize
         * index:当前页的起始索引
         * pageSize：每页显示的条数
         * pageNum：当前页的页码
         * index = （pageNum - 1）* pageSize
         *
         * 使用Mybatis的分页插件实现分页功能：
         * 1.需要在查询之前开启分页
         * PageHelper.startPage(int pageNum,int PageSize)
         * 2.在查询之后获取分页相关信息
         * PageInfo<Emp> page = new PageInfo<>(list,5);
         * list表示分页数据
         * 5表示导航分页的数量
         */
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
//            Page<Object> page = PageHelper.startPage(2,4);
            PageHelper.startPage(4,4);
            List<Emp> list = mapper.selectByExample(null);
            PageInfo<Emp> page = new PageInfo<>(list,5);
            for (Emp emp:list) {
                System.out.println(emp);
            }
//            System.out.println(page);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
