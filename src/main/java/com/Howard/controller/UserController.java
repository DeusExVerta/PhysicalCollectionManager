package com.Howard.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.Howard.entity.User;
import com.Howard.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/*
 * Controller for handling user management related endpoints.
 */

@Slf4j
@Controller
@AllArgsConstructor
public class UserController {

	private UserService userService;
	
    /*
     * method handling home-page requests redirecting those to login.
     */
    @GetMapping("/")
    public String home(){
    	log.info("Base url redirect");
        return "login";
    }
    
    /*
     * method handling explicit requests for login
     */
    @GetMapping("/login")
    public String loginPage()
    {
    	return "login";
    }
    
    
    /*
     * method handling requests for the registration page.
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    
    /*
     * method handling requests to save a new user.
     */
    @PostMapping("/register/save")
    public String registerUser(@Valid @ModelAttribute("user") User user,BindingResult result,
            Model model) 
    {
    	User existingUser = userService.findByEmail(user.getEmail());
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with this email");
        }
    	if(result.hasErrors()){
            model.addAttribute("user", user);
            return "/register";
        }
    	userService.save(user);
    	return "redirect:/register?success";
    }   
}