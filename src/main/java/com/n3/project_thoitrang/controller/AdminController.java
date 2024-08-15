package com.n3.project_thoitrang.controller;

import com.n3.project_thoitrang.model.entity.User;
import com.n3.project_thoitrang.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class AdminController {
    private final HttpSession session;
    private final IUserService userService;

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

    @GetMapping("/sortUserList")
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

    @RequestMapping("/updateStatus")
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
}
