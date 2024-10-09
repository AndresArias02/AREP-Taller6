package edu.eci.arep.repositorys;

import edu.eci.arep.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * User Repository class
 * Author: Andrés Arias
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Boolean existsByEmail(String email);

    User findByEmail(String email);
}
