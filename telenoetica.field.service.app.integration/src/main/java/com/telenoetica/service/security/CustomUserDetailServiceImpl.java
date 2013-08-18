/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.entities.Role;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.jpa.entities.UserRole;
import com.telenoetica.service.UserService;

/**
 * The Class CustomUserDetailServiceImpl.
 * 
 * @author Shiv Prasad Khillar
 */
@Service("customUserDetailService")
@Transactional(readOnly = true)
public class CustomUserDetailServiceImpl implements UserDetailsService {

  /** The user service. */
  @Autowired
  private UserService userService;

  /**
   * Load user by username.
   *
   * @param username the username
   * @return the user details
   * @throws UsernameNotFoundException the username not found exception
   * @see org.springframework.security.core.userdetails.UserDetailsService#
   * loadUserByUsername(java.lang.String)
   */
  @Override
  public UserDetails loadUserByUsername(final String username)
      throws UsernameNotFoundException {

    try {
      User user = userService.findByUserName(username);

      boolean enabled = true;
      boolean accountNonExpired = true;
      boolean credentialsNonExpired = true;
      boolean accountNonLocked = true;

      return new org.springframework.security.core.userdetails.User(
        user.getUserName(), user.getPassword(), enabled,
        accountNonExpired, credentialsNonExpired, accountNonLocked,
        getAuthorities(user.getUserRole()));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  /**
   * Gets the authorities.
   * 
   * @param userRole
   *            the user role
   * @return the authorities
   */
  private Collection<? extends GrantedAuthority> getAuthorities(
    final UserRole userRole) {

    Role role = userRole.getRole();
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
    return authorities;
  }

}
