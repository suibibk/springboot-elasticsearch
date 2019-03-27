package cn.myforever.es.dao;

import org.springframework.data.repository.CrudRepository;

import cn.myforever.es.entity.User;

public interface UserDao extends CrudRepository<User, String>{

}
