package ute.shop.services;

import ute.shop.entity.*;


public interface IUserService {
	User findbyEmail(String email);
	User login(String email, String password);
	void insert(User user);
	boolean register(String firstname,String lastname,String email, String password, String phone,String idcard);
	boolean checkExistEmail(String email);
	boolean checkExistPhone(String phone);
	User findPassword(String email, String password);
}
