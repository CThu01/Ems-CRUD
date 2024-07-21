package com.ems.backend.model.repo;

import org.springframework.stereotype.Repository;

import com.ems.backend.model.entity.MyUser;

@Repository
public interface UserRepo extends BaseRepo<MyUser, Integer>{

}
