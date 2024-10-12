package edu.eci.arep.controllers;

import edu.eci.arep.models.Properties;
import edu.eci.arep.services.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for Properties
 * @author: Andrés Arias
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
public class PropertiesController {

    private final PropertiesService propertiesService;

    @Autowired
    public PropertiesController(PropertiesService propertiesService){
        this.propertiesService = propertiesService;
    }

    /**
     * Get all properties
     * @return List of properties
     */
    @GetMapping("/properties")
    public List<Properties> getProperties(){
        return propertiesService.getAllProperties();
    }

    /**
     * Add a new property
     * @param properties property to add
     */
    @PostMapping("/properties")
    public ResponseEntity<Properties> addProperty(@RequestBody Properties properties){
        propertiesService.addProperty(properties);
        return new ResponseEntity<>(properties, HttpStatus.CREATED);
    }

    /**
     * Update a property
     * @param id id of the property
     * @param properties property to update
     */
    @PutMapping("/properties/{id}")
    public void updateProperty(@PathVariable Long id, @RequestBody Properties properties){
        propertiesService.updateProperty(id, properties);
    }

    /**
     * Delete a property
     * @param id id of the property
     */
    @DeleteMapping("/properties/{id}")
    public void deleteProperty(@PathVariable Long id){
        propertiesService.deleteProperty(id);
    }

}
