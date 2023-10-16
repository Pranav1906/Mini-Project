package org.ncu.hirewheels.controller;
import org.springframework.web.bind.annotation.RestController;
import org.ncu.hirewheels.entities.Vehicle;
import org.ncu.hirewheels.requests.UpdateAvailabilityRequest;
import org.ncu.hirewheels.requests.VehicleRequest;
import org.ncu.hirewheels.services.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/hirewheels/v1")
public class AdminController {

	
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    
	@Autowired
    private AdminService adminService;


    @PostMapping(value = "/vehicles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> AddVehicle(@RequestBody VehicleRequest vehicleRequest) {
    	

    	// Log the request body
        logger.info("Received request body: {}",  vehicleRequest);
    	
        
        
        // Convert VehicleRequest to Vehicle entity and save it to the database
        Vehicle vehicle = adminService.convertToVehicleEntity(vehicleRequest);  
        
        
    	// Log the request body
//        logger.info("Final vehicle body: {}",  vehicle);
        
    	adminService.registerVehicle(vehicle);

        // Return a success response with HTTP status code 201 (Created)
        return new ResponseEntity<>("Vehicle added successfully", HttpStatus.CREATED);
    }
    
 // Update the availability status of the specified vehicle by ID
    @PutMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> updateVehicleAvailability(
            @PathVariable Long id,
            @RequestBody UpdateAvailabilityRequest request) {
        // Check if the request contains a valid availabilityStatus
        if (request != null && request.getAvailabilityStatus() != null) {
            // Call the service method to update vehicle availability
            Vehicle updatedVehicle = adminService.changeAvailability(id, request.getAvailabilityStatus());

            // Check if the vehicle was updated successfully
            if (updatedVehicle != null) {
                // Return the updated vehicle with HTTP status ACCEPTED
                return new ResponseEntity<>(updatedVehicle, HttpStatus.ACCEPTED);
            } else {
                // Return HTTP status NOT_FOUND if the vehicle was not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            // Return HTTP status BAD_REQUEST if the request is invalid
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    
}

