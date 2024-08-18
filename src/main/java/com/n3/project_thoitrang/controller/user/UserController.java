package com.n3.project_thoitrang.controller.user;

import com.n3.project_thoitrang.model.entity.Role;
import com.n3.project_thoitrang.model.entity.User;
import com.n3.project_thoitrang.repository.IRoleRepository;
import com.n3.project_thoitrang.security.CustomUserDetail;
import com.n3.project_thoitrang.service.impl.UploadFileImpl;
import com.n3.project_thoitrang.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private HttpSession session;
    @Autowired
    private UploadFileImpl uploadFile;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private IRoleRepository roleRepository;

    @GetMapping("/edit-user")
    public String editUser(Model model) {
        User user = (User) session.getAttribute("data_user");
        model.addAttribute("useredit", user);
        return "/general/edit-user-detail";
    }

    @PostMapping("/edit-user")
    public String newUser(@ModelAttribute User newUser,
                          @RequestParam("newAvatar") MultipartFile newAvatar,
                          Model model) {
        if (newAvatar != null && !newAvatar.isEmpty()) {
            String avatarUrl = uploadFile.uploadLocal(newAvatar);
            newUser.setAvatar(avatarUrl);
        }
        List<Role> role = new ArrayList<>();
        role.add(roleRepository.findRolesByRoleName(Role.RoleName.USER));
        newUser.setRole(role);

        //Thieu save database
        userService.save(newUser);
        session.setAttribute("data_user", newUser);
        return "redirect:/login-register/detail";
    }
}
