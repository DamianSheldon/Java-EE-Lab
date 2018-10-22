package com.tenneshop.core.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.MapFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CorePersistenceConfig {
    @Autowired
    @Qualifier("webDS")
    DataSource webDS;

    @Autowired
    @Qualifier("webSecureDS")
    DataSource webSecureDS;

    @Autowired
    @Qualifier("webStorageDS")
    DataSource webStorageDS;

    @Bean
    public MapFactoryBean blMergedDataSources() throws Exception {
        MapFactoryBean mapFactoryBean = new MapFactoryBean();
        Map<String, DataSource> sourceMap = new HashMap<>();
        sourceMap.put("jdbc/web", webDS);
        sourceMap.put("jdbc/webSecure", webSecureDS);
        sourceMap.put("jdbc/cmsStorage", webStorageDS);
        mapFactoryBean.setSourceMap(sourceMap);

        return mapFactoryBean;
    }
}
