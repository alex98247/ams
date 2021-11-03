package com.ams.configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import javax.sql.DataSource;

@Configuration
@EnableHazelcastHttpSession
public class ApplicationConfiguration {

    @Bean(destroyMethod = "shutdown")
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config();
        config.getNetworkConfig()
                .setPort(5701)
                .setPortAutoIncrement(true);

        GroupConfig group = new GroupConfig("ams_cluster", "strong_password");
        config.setGroupConfig(group);

        NetworkConfig network = config.getNetworkConfig();
        JoinConfig join = network.getJoin();
        join.getMulticastConfig()
                .setEnabled(false)
                .addTrustedInterface("localhost");
        join.getTcpIpConfig()
                .addMember("localhost")
                .setRequiredMember("localhost").setEnabled(true);
        return Hazelcast.newHazelcastInstance(config);
    }

    @Bean
    DataSource dataSource() {
        HikariConfig config = new HikariConfig("/hikari.properties");
        return new HikariDataSource(config);
    }

    @Bean
    RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }
}
