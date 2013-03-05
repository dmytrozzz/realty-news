package com.dmytro.realty.service.implementation;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmytro.realty.data.repository.UserRepository;
import com.dmytro.realty.domain.RealtyUser;
import com.dmytro.realty.security.RealtyUserDetails;
import com.dmytro.realty.service.IUserService;

@Repository
@Transactional
@Service("userService")
public class UserSecurityService implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	RealtyUser user = userRepository.findByLogin(username);
	if (user == null) {
	    throw new UsernameNotFoundException("Invalid username/password");
	}
	return new RealtyUserDetails(user);
    }

    @Override
    public void addUser(RealtyUser user) {
	user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
	userRepository.save(user);
    }

    @Override
    public RealtyUser findUserByLogin(String login) {
	return (RealtyUser) loadUserByUsername(login);
    }
}
