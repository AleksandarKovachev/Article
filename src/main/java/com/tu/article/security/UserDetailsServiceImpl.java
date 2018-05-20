package com.tu.article.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tu.article.entity.Role;
import com.tu.article.entity.User;
import com.tu.article.service.UserService;

/**
 * Implementation for user details service defining login functionality
 * 
 * @author aleksandar.kovachev
 *
 */
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getActiveUserByUsername(username);

		if (user == null)
			throw new UsernameNotFoundException(username);

		List<GrantedAuthority> authorities = new ArrayList<>();

		for (Role role : user.getRoles()) {
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getCode());
			authorities.add(simpleGrantedAuthority);
		}

		return new UserDetailsImpl(user.getUsername(), user.getPassword(), user.getId(), authorities);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
