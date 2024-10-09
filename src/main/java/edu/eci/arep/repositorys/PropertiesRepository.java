package edu.eci.arep.repositorys;

import java.util.List;
import java.util.Optional;

import edu.eci.arep.models.Properties;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for Properties
 * Author: Andrés Arias
 */
@Repository
public interface PropertiesRepository extends CrudRepository<Properties, Long> {

    /**
     * Method to get all properties
     * @return List of properties
     */
    List<Properties> findAll();

    /**
     * Method to get a property by id
     * @param id id of the property
     * @return  Properties
     */
    Optional<Properties> findById(long id);

}
