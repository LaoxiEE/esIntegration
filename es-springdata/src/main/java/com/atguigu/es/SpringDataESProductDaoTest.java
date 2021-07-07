package com.atguigu.es;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataESProductDaoTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void save() {

        Product product = new Product();
        product.setId(1L);
        product.setTitle("huawei");
        product.setPrice(4999.9);
        product.setCategory("phone");
        product.setImages("http://www.xc.huawei/xc.jpg");
        productDao.save(product);
    }

}
