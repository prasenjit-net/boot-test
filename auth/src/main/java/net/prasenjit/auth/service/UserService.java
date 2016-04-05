/*
 * Copyright (c) 2016 Prasenjit Purohit
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package net.prasenjit.auth.service;

import net.prasenjit.auth.domain.User;
import net.prasenjit.auth.model.UserData;
import net.prasenjit.auth.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by PRASEN on 4/3/2016.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
	private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOne(username);
        if (user == null) {
            throw new UsernameNotFoundException("user " + username + " not found");
        }
        return user;
    }
    
    public boolean registerUser(UserData userdata) {

		User user = userRepository.findOne(userdata.getUsername());

		if (user != null) {
			return false;
		}

		User objuser = new User();

		String encryptedpassword = passwordEncoder.encode(userdata.getPassword());

		objuser.setName(userdata.getName());
		objuser.setUsername(userdata.getUsername());
		objuser.setPassword(encryptedpassword);

		userRepository.save(objuser);

		return true;

	}

	public boolean changePassword(String userid, String oldpassword, String newpassword) {

		User user = userRepository.getOne(userid);

		if (user != null) {
			boolean ispasswordmatches = passwordEncoder.matches(oldpassword, user.getPassword());

			String encryptedpassword = passwordEncoder.encode(user.getPassword());

			if (ispasswordmatches) {

				user.setPassword(encryptedpassword);

				userRepository.save(user);

				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	public List<User> getAllUser() {

		return userRepository.findAll();

	}

	public boolean lockUser(String userid) {

		User user = userRepository.getOne(userid);

		if (user != null) {

			user.setAccountNonLocked(false);

			return true;
		}
		return false;

	}
	
	public boolean unLockuser(String userid) {

		User user = userRepository.getOne(userid);

		if (user != null) {

			user.setAccountNonLocked(true);

			return true;
		}
		return false;

	}
	
	public boolean disableUser(String userid) {

		User user = userRepository.getOne(userid);

		if (user != null) {

			user.setEnabled(false);

			return true;
		}
		return false;
	}
	
	public boolean enableUser(String userid) {

		User user = userRepository.getOne(userid);
		
		if (user != null) {

			user.setEnabled(true);

			return true;
		}
		return false;
	}
}
