package com.tshepommatli.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.tshepommatli.model.User;
import com.tshepommatli.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {	
    @Autowired
    private UserService userService ;

    @RequestMapping(value={"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index(){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("index");
            return modelAndView;
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView login(){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            return modelAndView;
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
            ModelAndView modelAndView = new ModelAndView();
            User user = new User();
            modelAndView.addObject("user", user);
            modelAndView.setViewName("registration");
            return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
                bindingResult.rejectValue("email", "error.user",
                "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
                modelAndView.setViewName("registration");
        } else {
                userService.saveUser(user);
                modelAndView.addObject("successMessage", "User has been registered successfully");
                modelAndView.addObject("user", new User());
                modelAndView.setViewName("registration");
        }
        return modelAndView;
    }

    @RequestMapping(value="/adminHome", method = RequestMethod.GET)
    public ModelAndView adminHome(){
            ModelAndView modelAndView = new ModelAndView();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            modelAndView.addObject("userName",user.getName() + " " + user.getLastName());
            modelAndView.addObject("email",user.getEmail());
            modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
            modelAndView.setViewName("adminHome");
            return modelAndView;
    }

    @RequestMapping(value="/customerHome", method = RequestMethod.GET)
    public ModelAndView customerHome(){
            ModelAndView modelAndView = new ModelAndView();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            modelAndView.addObject("userName", "Hi, " + user.getName() + " " + user.getLastName());
            modelAndView.addObject("userID", user.getId());
            modelAndView.setViewName("customerHome");
            return modelAndView;
    }

    @RequestMapping(value="/default")
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response){

        //Retrieve the logged on user role
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        String targetUrl = null;
        if(role.contains("admin"))
        {
            targetUrl = "redirect:/adminHome";
        }
        else if(role.contains("customer"))
        {
            targetUrl = "redirect:/customerHome";
        }

        return targetUrl;
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logout(){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("logout");
            return modelAndView;
    }

    @RequestMapping(value = "/viewUser", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(String email)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        return user;
    }
}
