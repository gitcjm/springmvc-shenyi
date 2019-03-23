package com.db.Mapper;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserMapper {
   List<UserEntity> selectAll();
}
