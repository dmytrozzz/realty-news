package com.dmytro.realty.service.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmytro.realty.data.repository.CriteriaRepository;
import com.dmytro.realty.data.repository.ParametersRepository;
import com.dmytro.realty.data.repository.UserRepository;
import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyParameters;
import com.dmytro.realty.domain.RealtyUser;
import com.dmytro.realty.domain.search.enums.OperationType;
import com.dmytro.realty.security.RealtyUserDetails;
import com.dmytro.realty.service.IUserService;

@Repository
@Transactional
@Service("userService")
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParametersRepository parametersRepository;

    @Autowired
    private CriteriaRepository criteriaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	RealtyUser user = userRepository.findByLogin(username);
	if (user == null) {
	    throw new UsernameNotFoundException("Invalid username/password");
	}
	return new RealtyUserDetails(user);
    }

    public void saveUser(RealtyUser user) {
	if (!userRepository.exists(user.getId()))
	    user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
	for (RealtyCriteria criteria : user.getCriteriaCollection()) {
	    // Check unique parameters
	    RealtyParameters p = criteria.getParameters();
	    RealtyParameters dataP = parametersRepository.findByParameters(p.getFromPrice(), p.getToPrice());
	    if (dataP != null)
		p.setId(dataP.getId());

	    // Check unique criteria
	    Collection<RealtyCriteria> dataCList = criteriaRepository.nativeFindBy(criteria.getProductType().name(),
		    p.getId());
	    for (RealtyCriteria dataC : dataCList) {
		Collection<String> operations = criteriaRepository.findByCriteriaId(dataC.getId());
		if (operations.size() == dataC.getOperations().size() && operations.containsAll(dataC.getOperations())) {
		    criteria.setId(dataC.getId());
		}
	    }
	}
	user.setId(userRepository.save(user).getId());
    }

    @Override
    public RealtyUser findUserByLogin(String login) {
	return userRepository.findByLogin(login);
    }
}
