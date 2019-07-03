package org.txlcn.demo.common.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Configuration
public class DruidConfig {


    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    @Bean(
            destroyMethod = "close",
            initMethod = "init"
    )
    //在同样的DataSource中，首先使用被标注的DataSource
    public DruidDataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.url);
        datasource.setUsername(this.username);
        datasource.setPassword(this.password);
        datasource.setDriverClassName(this.driverClassName);
        datasource.setInitialSize(5);
        datasource.setMaxActive(20);
        WallConfig wallConfig = new WallConfig();
        wallConfig.setMultiStatementAllow(true);
        List<Filter> list = datasource.getProxyFilters();
        if (list != null && !list.isEmpty()) {
            Iterator iterator = list.iterator();

            while(iterator.hasNext()) {
                Filter filter = (Filter)iterator.next();
                if (filter instanceof WallFilter) {
                    ((WallFilter)filter).setConfig(wallConfig);
                    break;
                }
            }
        } else {
            WallFilter wallFilter = new WallFilter();
            wallFilter.setConfig(wallConfig);
            List<Filter> filters1 = new ArrayList();
            filters1.add(wallFilter);
            datasource.setProxyFilters(filters1);
        }

        return datasource;
    }
}
