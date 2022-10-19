package com.Howard.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Howard.entity.User;
import com.Howard.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@AllArgsConstructor
public class UserController {

	private UserService userService;
	
    // handler method to handle home page request
    @GetMapping("/")
    public String home(){
    	log.info("Base url redirect");
        return "login";
    }
    
    
    @GetMapping("/login")
    public String loginPage()
    {
    	return "login";
    }
        
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    
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
    
    @GetMapping("/users")
    public String users(Model model, @RequestParam("page") Optional<Integer> page){
        model.addAttribute("users", userService.findAllUsers(PageRequest.of(page.orElse(1)-1, 10)));
        return "users";
    }
    
    
}