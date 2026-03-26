package com.study.spring.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan("com.study.spring.**.dao") // 모든 도메인의 dao 패키지 스캔
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        
        // 1. 데이터소스 설정 (PostgreSQL)
        sessionFactory.setDataSource(dataSource);
        
        // 2. Mapper XML 위치 설정
        sessionFactory.setMapperLocations(
            new PathMatchingResourcePatternResolver().getResources("classpath:sql/mapper/**/*.xml")
        );
        
        // 3. MyBatis 전역 설정 파일 위치 설정
        sessionFactory.setConfigLocation(
            new PathMatchingResourcePatternResolver().getResource("classpath:sql/mybatis-config.xml")
        );
        
        return sessionFactory.getObject();
    }
    
}
