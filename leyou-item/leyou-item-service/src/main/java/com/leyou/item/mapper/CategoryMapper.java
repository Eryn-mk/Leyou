package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

public interface CategoryMapper extends Mapper<Category> {
    @SpringBootApplication
    @EnableDiscoveryClient
    @MapperScan("com.leyou.item.mapper") // mapper接口的包扫描
    public class LeyouItemServiceApplication {

        public static void main(String[] args) {
            SpringApplication.run(LeyouItemServiceApplication.class, args);
        }
    }
}
