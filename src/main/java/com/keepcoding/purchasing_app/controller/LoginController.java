package com.keepcoding.purchasing_app.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.keepcoding.purchasing_app.dto.LoginUserDto;
import com.keepcoding.purchasing_app.dto.RegisterUserDto;
import com.keepcoding.purchasing_app.entity.Role;
import com.keepcoding.purchasing_app.entity.User;
import com.keepcoding.purchasing_app.service.AuthenticationService;
import com.keepcoding.purchasing_app.service.RoleService;
import com.keepcoding.purchasing_app.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final UserService userService;
	private final RoleService roleService;
	private final AuthenticationService authenticationService;
	//private final RoleService roleService;
	//private final UserService userService;
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	// Redirección después del inicio de sesión exitoso
	@PostMapping("/login")
	public String loginAuthentication(@ModelAttribute LoginUserDto loginUserDto) {
	    try {
	        // Llama al servicio de autenticación
	        User authenticatedUser = authenticationService.authenticate(loginUserDto);
	        
	        // Redirige a la página por defecto después de iniciar sesión exitosamente
	        return "redirect:/default";
	        
	    } catch (RuntimeException e) {
	        // Si ocurre un error de autenticación, redirige de nuevo al login con un mensaje de error
	        return "redirect:/login?error=true";
	    }
	}
    
    @GetMapping("/public/new_user")
    public String viewNewUserForm(Model model) {
    	model.addAttribute("user", new User());
    	model.addAttribute("allRoles", roleService.listAllRoles());
    	return "new_user";
    }
    
    @PostMapping("/public/new_user") 
    public String createUser(
    		@RequestParam String username, 
    		@RequestParam String password, 
    		@RequestParam Set<Long> roles, Model model) { 
    	
    	// Crear el objeto RegisterUserDto 
    	RegisterUserDto registerUserDto = new RegisterUserDto(username, password); 
    	
    	// Registrar el nuevo usuario 
    	User registeredUser = authenticationService.signup(registerUserDto); 
    	
    	// Asignar roles al usuario 
    	Set<Role> userRoles = roles.stream() 
    			.map(roleService::findRoleById) 
    			.collect(Collectors.toSet()); 
    	registeredUser.setRoles(userRoles); 
    	
    	// Guardar el nuevo usuario en la base de datos 
    	userService.saveUser(registeredUser); 
    	
    	return "redirect:/login"; 
    	}

    // Cerrar sesión
    @GetMapping("/logout")
    public String logoutPage() {
        return "redirect:/login?logout";  // Redirige al login con mensaje de logout exitoso
    }
}
