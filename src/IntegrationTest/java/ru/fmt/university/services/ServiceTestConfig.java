package ru.fmt.university.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"ru.fmt.university.dao", "ru.fmt.university.service"})
public class ServiceTestConfig {
}
