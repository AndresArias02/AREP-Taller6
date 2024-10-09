package edu.eci.arep.services;

import edu.eci.arep.models.Properties;
import edu.eci.arep.repositorys.PropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Properties
 * Author: Andrés Arias
 */
@Service
public class PropertiesService {

    private final PropertiesRepository propertiesRepository;

    @Autowired
    public PropertiesService(PropertiesRepository propertiesRepository) {
        this.propertiesRepository = propertiesRepository;
    }

    /**
     * Method to get all properties
     * @return List of properties
     */
    public List<Properties> getAllProperties() {
        return propertiesRepository.findAll();
    }

    /**
     * Method to get a property by id
     * @param id id of the property
     * @return Properties
     */
    public Properties getPropertyById(long id){
        Optional<Properties> properties = propertiesRepository.findById(id);
        if(properties.isPresent()){
            return properties.get();
        }
        return null;
    }

    /**
     * Method to add a new property
     * @param properties property to add
     */
    public void addProperty(Properties properties){
        propertiesRepository.save(properties);
    }

    /**
     * Method to update a property
     * @param id id of the property
     * @param properties property to update
     */
    public void updateProperty(Long id, Properties properties){
        Optional<Properties> propertyToUpdate = propertiesRepository.findById(id);
        if(propertyToUpdate.isPresent()){
            Properties existingProperty = propertyToUpdate.get();

            // Update the property with the new values if they are not empty
            existingProperty.setAddress(properties.getAddress() != null && !properties.getAddress().isEmpty() ? properties.getAddress() : existingProperty.getAddress());
            existingProperty.setPrice(properties.getPrice() != null && !properties.getPrice().isEmpty() ? properties.getPrice() : existingProperty.getPrice());
            existingProperty.setSize(properties.getSize() != null && !properties.getSize().isEmpty() ? properties.getSize() : existingProperty.getSize());
            existingProperty.setDescription(properties.getDescription() != null && !properties.getDescription().isEmpty() ? properties.getDescription() : existingProperty.getDescription());

            // Save the updated property
            propertiesRepository.save(existingProperty);
        }
    }

    /**
     * Method to delete a property
     * @param id id of the property
     */
    public void deleteProperty(long id){
        propertiesRepository.deleteById(id);
    }
}
