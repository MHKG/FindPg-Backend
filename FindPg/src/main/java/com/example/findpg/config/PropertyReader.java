package com.example.findpg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:/mysql.properties"})
public class PropertyReader {}
