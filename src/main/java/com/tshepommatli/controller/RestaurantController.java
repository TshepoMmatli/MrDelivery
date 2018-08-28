package com.tshepommatli.controller;

import com.tshepommatli.model.Restaurant;
import com.tshepommatli.model.User;
import com.tshepommatli.service.RestaurantService;
import com.tshepommatli.service.UserService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RestaurantController {
	
    @Autowired
    private RestaurantService restaurantService ;
    @Autowired
    private UserService userService;
    
    @RequestMapping(value="/restaurantOwners", method = RequestMethod.GET)
    public ModelAndView restaurantOwners(){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("restaurantOwners");
            return modelAndView;
    }

    @RequestMapping(value="/SaveRestaurant", method = RequestMethod.POST)
    public void addRestaurant(@RequestBody Restaurant restaurant){
            restaurantService.addRestaurant(restaurant);
    }
    
    @RequestMapping(value = "/GetRestaurants", method = RequestMethod.GET)
    public List<Restaurant> getRestaurants(){
        return restaurantService.getRestaurants(); 
    }
    
    @RequestMapping(value = "/GetPending", method = RequestMethod.GET)
    public List<Restaurant> GetPending(){
        return restaurantService.getPendingRestaurants();
    }
    
    @RequestMapping(value="/pendingRequest", method = RequestMethod.GET)
    public ModelAndView pendingRequest(){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("pendingRequest");
            return modelAndView;
    }

    @RequestMapping(value="/restaurants", method = RequestMethod.GET)
    public ModelAndView restaurants(){
            ModelAndView modelAndView = new ModelAndView();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            modelAndView.addObject("userName", "Hi, " + user.getName() + " " + user.getLastName());
            modelAndView.addObject("userID", user.getId());
            modelAndView.setViewName("restaurants");
            return modelAndView;
    }
    
    @RequestMapping(value="/findRestaurants", method = RequestMethod.GET)
    public ModelAndView findRestaurants(){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("findRestaurants");
            return modelAndView;
    }
    
    // Update a Restaurant
    @PutMapping("/updateRestaurant/{restaurantId}")
    public void updateRestaurant(@PathVariable(value = "restaurantId") Long restaurantId, 
                                                           @Valid @RequestBody Restaurant restaurantDetails) {
        Restaurant restaurant = restaurantService.findByRestaurantId(restaurantId);
        
        if(restaurant != null) {
            restaurant.setRequestStatus(true);
        }

        restaurantService.saveRestaurant(restaurant);
    }
    
    //Delete a Restaurant
    @DeleteMapping("/deleteRestaurant/{restaurantId}")
    public void deleteRestaurant(@PathVariable(value = "restaurantId") Long restaurantId) {
        Restaurant restaurant = restaurantService.findByRestaurantId(restaurantId);
        
        if(restaurant != null) {
           
            restaurantService.deleteRestaurant(restaurantId);
        }

    }
    

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "src/main/resources/static/images/";


    @PostMapping("/api/upload")

    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile) {
  
        if (uploadfile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {

            saveUploadedFiles(Arrays.asList(uploadfile));

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - " +
                uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
    }

    // Multiple file upload
    @PostMapping("/api/upload/multi")
    public ResponseEntity<?> uploadFileMulti(
            @RequestParam("extraField") String extraField,
            @RequestParam("files") MultipartFile[] uploadfiles) {

        // Get file name
        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {

            saveUploadedFiles(Arrays.asList(uploadfiles));

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - "
                + uploadedFileName, HttpStatus.OK);

    }


    @PostMapping("/api/upload/multi/model")
    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute Restaurant model) {

        try {

            saveUploadedFiles(Arrays.asList(model.getFiles()));

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);

    }

    //save file
    private void saveUploadedFiles(List<MultipartFile> files) throws IOException 
    {
        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }   
            
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        }
        
    }
    
}
