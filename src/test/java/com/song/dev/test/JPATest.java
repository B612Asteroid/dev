package com.song.dev.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.song.dev.item.ItemBean;
import com.song.dev.item.ItemBeanRepository;

@SpringBootTest
public class JPATest {

	@Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Autowired
    ItemBeanRepository itemRepository;
    
    @Test
    public void test() {
    	try {
			Connection conn = dataSource.getConnection();
			 DatabaseMetaData metaData = conn.getMetaData();
			 System.out.println(metaData.getDriverName());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void insertTest() {
    	try {
			ItemBean item = new ItemBean();
			item.setName("mini pencil");
			item.setPrice(500);
			
			itemRepository.save(item);
			
			List<ItemBean> items = itemRepository.findAll();
			
			System.out.println(items);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
}
