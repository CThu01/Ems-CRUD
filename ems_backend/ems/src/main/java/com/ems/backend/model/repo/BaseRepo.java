package com.ems.backend.model.repo;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

import com.ems.backend.model.entity.MyUserAccount;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@NoRepositoryBean
public interface BaseRepo<T,ID> extends JpaRepositoryImplementation<T, ID>{

	
	<R> List<R> findAll(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc);
	
}
