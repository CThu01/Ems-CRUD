package com.ems.backend.model.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

import com.ems.backend.model.entity.MyUser;

@NoRepositoryBean
public interface BaseRepo<T,ID> extends JpaRepositoryImplementation<T, ID>{

	Optional<MyUser> findByUserName(String username);
}
