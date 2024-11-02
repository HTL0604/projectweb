package ute.shop.dao;

import java.util.List;

import ute.shop.entity.User;

public interface IUserDao {
	int count();
	List<User> findAll(int page, int pagesize);
	void insert(User user);
	void update(User user);
	void delete(int userid) throws Exception;
	User findbyEmail(String email);
	boolean checkExistEmail(String email);
	boolean checkExistPhone(String phone);
}
