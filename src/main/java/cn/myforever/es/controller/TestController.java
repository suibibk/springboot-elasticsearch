package cn.myforever.es.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.myforever.es.dao.UserDao;
import cn.myforever.es.entity.User;

@RestController
public class TestController {
	@Autowired
	private UserDao userDao;
	@RequestMapping("/es")
	public User es(HttpServletRequest request) {
		String id = request.getParameter("id");
		User user = new User();
		user.setId(id);
		user.setName("林文华");
		user.setAge(20);
		userDao.save(user);
		//下面这里返回结果
		Optional<User> us = userDao.findById(id);
		System.out.println(us.get());
		return us.get();
	}
}
