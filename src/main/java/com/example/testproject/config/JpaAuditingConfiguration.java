package com.example.testproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//이렇게 클래스를 만들어서 붙이는 방법을 더 권장한다
@Configuration
@EnableJpaAuditing //
public class JpaAuditingConfiguration {

}
