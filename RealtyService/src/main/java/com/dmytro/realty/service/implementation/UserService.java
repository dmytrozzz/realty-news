package com.dmytro.realty.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmytro.realty.domain.User;
import com.dmytro.realty.repository.UserRepository;
import com.dmytro.realty.service.IUserService;

@Repository
@Transactional
@Service("userService")
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
	userRepository.save(user);
    }
}
