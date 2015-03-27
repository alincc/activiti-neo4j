package org.activiti.neo4j;

import org.activiti.neo4j.entity.User;

/**
 * Created by capiali on 27/03/2015.
 */
public interface IdentityService {
	void saveUser(User user);
	User newUser(String username);
	void deleteUser(String username);
}
