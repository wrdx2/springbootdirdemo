package com.wrdao.springboot.util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JdbcTemplateConfig {
    public static JdbcTemplateConfig jdbcTemplateConfig;

    private String className;
    private String url;
    private String userName;
    private String password;

    public JdbcTemplateConfig(){};

    public JdbcTemplateConfig(String className,String url,String userName,String password){
        this.className = className;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }
    //单例模式
    public synchronized static JdbcTemplateConfig getInstance(){
        if(jdbcTemplateConfig == null){
            jdbcTemplateConfig = new JdbcTemplateConfig();
        }
        return jdbcTemplateConfig;
    }
    public JdbcTemplate inital(String className, String url, String userName, String password){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(className);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        // 创建JDBC模板
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        // 这里也可以使用构造方法
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

}
