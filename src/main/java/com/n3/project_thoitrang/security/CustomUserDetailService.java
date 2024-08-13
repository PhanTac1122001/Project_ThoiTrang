package com.n3.project_thoitrang.security;

import com.n3.project_thoitrang.model.entity.Role;
import com.n3.project_thoitrang.model.entity.User;
import com.n3.project_thoitrang.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUsersByUserName(username);
        if(user!=null){
            CustomUserDetail customUserDetail = CustomUserDetail.builder()
                    .userName(user.getUsername())
                    .email(user.getEmail())
                    .address(user.getAddress())
                    .fullName(user.getFullname())
                    .password(user.getPassword())
                    .authorities(mapToUserAuthrities(user.getRole()))
                    .build();
            return customUserDetail;
        }else{
            throw new UsernameNotFoundException("Username not exist");
        }
    }

    private List<GrantedAuthority> mapToUserAuthrities(List<Role> role) {
        return role.stream().map(role1 -> new SimpleGrantedAuthority(role1.getRoleName().name())).collect(Collectors.toList());
//        return role.stream().map(role1->new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }
}
