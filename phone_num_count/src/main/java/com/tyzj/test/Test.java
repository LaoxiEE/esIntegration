package com.tyzj.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.tyzj.handler.ReadPhoneCountSingleFile;
import com.tyzj.mapper.PhoneMapper;
import com.tyzj.pojo.Pojo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class Test {
    private static SqlSession session = null;
    private static SqlSessionFactory build;
    private static List<Pojo> phoneList = null;

    public static void init() {
        InputStream is;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
            build = new SqlSessionFactoryBuilder().build(is);
            session = build.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void destory() {
        if (session != null) {
            session.close();
        }
    }

    public static void main(String[] args) {

        init();
        session.close();
        session = build.openSession(ExecutorType.BATCH, true);
        phoneList = ReadPhoneCountSingleFile.readFilesDir("D:\\CompanyFile\\user_login");
        System.out.println(phoneList);
        session.getMapper(PhoneMapper.class).InsertPhoneList(phoneList);
        session.commit();
        destory();
    }

}
