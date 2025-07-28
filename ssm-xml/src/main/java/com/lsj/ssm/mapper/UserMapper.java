package com.lsj.ssm.mapper;

import com.lsj.ssm.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserMapper {

    User selectOne(Integer id);

    List<User> findAllUser();

    int insertUser(User user);

    int insertBatch(@Param("entities") List<User> entities);

    int insertOrUpdateBatch(@Param("entities") List<User> entities);

    int updateUser(User user);

    int deleteUserById(Integer id);
}
