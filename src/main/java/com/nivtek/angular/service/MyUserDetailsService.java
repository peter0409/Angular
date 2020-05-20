package com.nivtek.angular.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nivtek.angular.dao.UserRepository;
import com.nivtek.angular.entity.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repo.findByUserName(username);

		if (user == null)
			throw new UsernameNotFoundException("User 404");

		return new UserDetailsImpl(user);
	}

}
