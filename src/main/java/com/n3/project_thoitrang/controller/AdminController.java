package com.n3.project_thoitrang.controller;

import com.n3.project_thoitrang.dto.FormLogin;
import com.n3.project_thoitrang.dto.FormRegister;
import com.n3.project_thoitrang.model.entity.User;
import com.n3.project_thoitrang.service.ILoginRegisterService;
import com.n3.project_thoitrang.service.IUserService;
import com.n3.project_thoitrang.service.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class AdminController {
    private final HttpSession session;
    private final IUserService userService;
    private final UploadFile uploadFile;
    private final ILoginRegisterService loginRegisterService;

    @GetMapping("admin/manage-account")
    public String viewUser(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            @RequestParam(name = "search", defaultValue = "") String search,
            Model model
    )
    {
        // set session
        session.setAttribute("active_user", "user");
        // set list user pagination
        model.addAttribute("userList", userService.findAllUser(page, size, search));
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("search", search);

        // totalPages
        Double totalPages = Math.ceil((double) userService.totalAllUser(search) / size);
        model.addAttribute("totalPages", totalPages);
        return "/admin/manage-account";
    }

    @GetMapping("admin/manage-account/sortUserList")
    public String sortByName(Model model,   @RequestParam(value = "sort", defaultValue = "asc") String sort) {
        List<User> user;
        if ("desc".equalsIgnoreCase(sort)) {
            user = userService.findAllByOrderByUsernameDesc();
        } else {
            user = userService.findAllByOrderByUsernameAsc();
        }
        model.addAttribute("userList", user);
        model.addAttribute("sort", sort);
        model.addAttribute("page",0);
        model.addAttribute("size",5);

        return "/admin/manage-account";
    }

    @RequestMapping("admin/manage-account/updateStatus")
    public String updateController(Model model,@RequestParam Long id) {
        User newUser = userService.findUserById(id);
//        System.out.println(newUser.getFullname());
        newUser.setStatus(!newUser.isStatus());
        userService.save(newUser);
        return "redirect:/admin/manage-account";
    }
    @RequestMapping("/dashboard")
    public String dashboard(){
        return "admin/dashboard";
    }

    @GetMapping("admin/manage-account/registerAdmin")
    public String viewRegisterAdmin(Model model) {
        model.addAttribute("formRegister", new FormRegister());
        return "admin/register-admin";
    }
    @PostMapping("admin/manage-account/registerAdmin")
    public String handleRegisterAdmin(@Valid @ModelAttribute("formRegister") FormRegister formRegister, BindingResult bindingResult,Model model,@RequestParam("avatarFile") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formRegister", formRegister);
            return "admin/register-admin";
        }
        String urlAvatar = uploadFile.uploadLocal(file);
        formRegister.setAvatar(urlAvatar);
        loginRegisterService.handleRegisterAdmin(formRegister);
        return "redirect:/admin/manage-account";
    }


}
