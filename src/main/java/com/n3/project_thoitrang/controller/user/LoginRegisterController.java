package com.n3.project_thoitrang.controller.user;

import com.n3.project_thoitrang.dto.FormLogin;
import com.n3.project_thoitrang.dto.FormRegister;
import com.n3.project_thoitrang.model.entity.Role;
import com.n3.project_thoitrang.model.entity.User;
import com.n3.project_thoitrang.service.ILoginRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/login-register")
@RequiredArgsConstructor
public class LoginRegisterController {
private final ILoginRegisterService loginRegisterService;

    @GetMapping("/login")
    public String viewLogin(Model model)
    {
        FormLogin formLogin = new FormLogin();
        model.addAttribute("formLogin", formLogin);
        return "login/login";
    }

    @PostMapping("/login")
    public String handleLogin(@Valid @ModelAttribute("formLogin") FormLogin formLogin, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("formLogin", formLogin);
            return "login/login";
        }
        User user = loginRegisterService.handleLogin(formLogin);
        if (user != null)
        {
            if (user.getRole().stream().anyMatch(roles -> roles.getRoleName().equals(Role.RoleName.USER)))
            {
                return "index";
            }
            else if (user.getRole().stream().anyMatch(roles -> roles.getRoleName().equals(Role.RoleName.ADMIN)))
            {
                return "admin/dashboard";
            }
            return "redirect:/";
        }
        model.addAttribute("error", "username or password is incorrect");
        model.addAttribute("formLogin", formLogin);
        return "login/login";
    }

    @GetMapping("/register")
    public String viewRegister(Model model)
    {
        model.addAttribute("formRegister", new FormRegister());
        return "login/register";
    }

    @PostMapping("/register")
    public String handleRegister(@Valid @ModelAttribute("formRegister") FormRegister formRegister, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("formRegister", formRegister);
            return "login/register";
        }
        loginRegisterService.handleRegister(formRegister);
        FormLogin formLogin = new FormLogin();
        model.addAttribute("formLogin", formLogin);
        return "/login/login";
    }

    @GetMapping("/logout")
    public String handleLogout()
    {
        loginRegisterService.handleLogout();
        return "redirect:/";
    }
}
