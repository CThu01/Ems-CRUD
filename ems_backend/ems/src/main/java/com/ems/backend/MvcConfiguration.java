package com.ems.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ems.backend.model.repo.BaseRepoImpl;

@Configuration
@EnableJpaRepositories(
		repositoryBaseClass = BaseRepoImpl.class
		)
public class MvcConfiguration implements WebMvcConfigurer{

}
