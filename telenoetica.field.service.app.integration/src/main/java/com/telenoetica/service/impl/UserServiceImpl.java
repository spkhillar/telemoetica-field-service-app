/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.entities.Role;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.jpa.entities.UserRole;
import com.telenoetica.jpa.repositories.GenericQueryExecutorDAO;
import com.telenoetica.jpa.repositories.RoleDAO;
import com.telenoetica.jpa.repositories.UserDAO;
import com.telenoetica.jpa.repositories.UserRoleDAO;
import com.telenoetica.service.UserService;
import com.telenoetica.service.excel.ExcelFillerService;
import com.telenoetica.service.excel.ExcelLayoutService;
import com.telenoetica.service.util.ExcelRendererModel;
import com.telenoetica.service.util.ExcelWriter;
import com.telenoetica.service.util.ServiceUtil;

/**
 * The Class UserServiceImpl.
 * 
 * @author Shiv Prasad Khillar
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	// private static final Logger LOGGER =
	// Logger.getLogger(UserServiceImpl.class);
	/** The user dao. */
	@Autowired
	private UserDAO userDAO;

	/** The role dao. */
	@Autowired
	private RoleDAO roleDAO;

	/** The user role dao. */
	@Autowired
	private UserRoleDAO userRoleDAO;

	/** The excel layout service. */
	@Resource(name = "defaultExcelLayoutService")
	private ExcelLayoutService excelLayoutService;

	/** The excel filler service. */
	@Resource(name = "defaultExcelLayoutFillerService")
	private ExcelFillerService excelFillerService;

	/** The generic query executor dao. */
	@Autowired
	private GenericQueryExecutorDAO genericQueryExecutorDAO;

	/** The sha password encoder. */
	@Autowired
	private ShaPasswordEncoder shaPasswordEncoder;

	/**
	 * Retrieve.
	 * 
	 * @param id
	 *            the id
	 * @return the user
	 * @see com.telenoetica.service.BaseService#retrieve(java.lang.Long)
	 */
	@Override
	public User retrieve(Long id) {
		return userDAO.findOne(id);
	}

	/**
	 * Save or update.
	 * 
	 * @param user
	 *            the user
	 * @return the user
	 * @see com.telenoetica.service.BaseService#saveOrUpdate(com.telenoetica.jpa.
	 *      entities.BaseEntity)
	 */
	@Override
	public User saveOrUpdate(User user) {
		String encodedPassword = shaPasswordEncoder.encodePassword(
				user.getPassword(), user.getUserName());
		user.setPassword(encodedPassword);
		Role role = roleDAO.findOne(user.getRoleId());
		UserRole userRole = new UserRole(user, role);
		user.setUserRole(userRole);
		return userDAO.save(user);
	}

	/**
	 * Delete.
	 * 
	 * @param baseEntity
	 *            the base entity
	 * @see com.telenoetica.service.BaseService#delete(com.telenoetica.jpa.entities
	 *      .BaseEntity)
	 */
	@Override
	public void delete(User baseEntity) {
		// User user = userDAO.findOne(baseEntity.getId());
		// userRoleDAO.delete(user.getUserRole());
		userDAO.delete(baseEntity.getId());
	}

	/**
	 * Find all.
	 * 
	 * @param page
	 *            the page
	 * @param rows
	 *            the rows
	 * @param sortOrder
	 *            the sort order
	 * @param orderByField
	 *            the order by field
	 * @return the page
	 * @see com.telenoetica.service.UserService#findALL(int, int,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public Page<User> findALL(int page, int rows, String sortOrder,
			String orderByField) {
		if ("roleId".equals(orderByField)) {
			orderByField = "userRole.role.id";
		}
		return userDAO.findAll(ServiceUtil.getPage(page, rows, sortOrder,
				orderByField));
	}

	/**
	 * List roles.
	 * 
	 * @return the list
	 * @see com.telenoetica.service.UserService#listRoles()
	 */
	@Override
	public List<Role> listRoles() {
		return roleDAO.findAll();
	}

	/**
	 * Find all.
	 * 
	 * @return the list
	 * @see com.telenoetica.service.UserService#findALL()
	 */
	@Override
	public List<User> findALL() {
		return userDAO.findAll();
	}

	/**
	 * Export users.
	 * 
	 * @param filterPredicate
	 *            the filter predicate
	 * @param paramObject
	 *            the param object
	 * @param httpServletResponse
	 *            the http servlet response
	 * @param attachmentFileName
	 *            the attachment file name
	 * @see com.telenoetica.service.UserService#exportUsers(java.lang.String,
	 *      java.util.Map, javax.servlet.http.HttpServletResponse,
	 *      java.lang.String)
	 */
	@Override
	public void exportUsers(String filterPredicate,
			Map<String, Object> paramObject,
			HttpServletResponse httpServletResponse, String attachmentFileName) {

		// 1. Create new workbook
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 2. Create new worksheet
		HSSFSheet worksheet = workbook.createSheet("users");

		// 3.create coulmn headers
		@SuppressWarnings("serial")
		List<String> excelColumns = new ArrayList<String>() {
			{
				add("User Name");
				add("First Name");
				add("Last Name");
				add("Email");
				add("Enabled");
				add("Role");
				add("Created Date");
			}
		};
		List<User> userList = null;
		// step 5 get entities
		if (StringUtils.isBlank(filterPredicate)) {
			userList = findALL();
		} else {
			String ejbql = "from User where " + filterPredicate;
			userList = genericQueryExecutorDAO.executeQuery(ejbql, User.class,
					paramObject);
		}

		// step 6 populate values as per the headings
		List<List<Object>> targetValues = new ArrayList<List<Object>>();
		for (User user : userList) {
			List<Object> values = new ArrayList<Object>();
			values.add(ServiceUtil.checkAndReturnValue(user.getUserName()));
			values.add(ServiceUtil.checkAndReturnValue(user.getFirstName()));
			values.add(ServiceUtil.checkAndReturnValue(user.getLastName()));
			values.add(ServiceUtil.checkAndReturnValue(user.getEmail()));
			values.add(ServiceUtil.checkAndReturnValue(user.getEnabled()));
			values.add(ServiceUtil.checkAndReturnValue(user.getUserRole()
					.getRole().getName()));
			values.add(ServiceUtil.checkAndReturnValue(user.getCreatedAt()));
			targetValues.add(values);
		}

		// step 7 initialize renderer model
		ExcelRendererModel excelRendererModel = new ExcelRendererModel(
				worksheet, 5000, excelColumns, "Users");

		// step 8 invoke layout service
		excelLayoutService.buildReport(excelRendererModel);

		// step 9 fill report content
		excelFillerService.fillReport(excelRendererModel, targetValues);

		// step 10 write report
		ExcelWriter.write(httpServletResponse, workbook, attachmentFileName);

	}

	/**
	 * Update.
	 * 
	 * @param user
	 *            the user
	 * @return the user
	 * @see com.telenoetica.service.UserService#update(com.telenoetica.jpa.entities
	 *      .User)
	 */
	@Override
	public User update(User user) {

		User savedUser = userDAO.findOne(user.getId());

		BeanUtils.copyProperties(user, savedUser, new String[] { "id",
				"roleId", "password", "userRole", "version", "createdAt" });

		if (StringUtils.isNotBlank(user.getPassword())) {
			boolean samePassword = shaPasswordEncoder.isPasswordValid(
					savedUser.getPassword(), user.getPassword(),
					user.getUserName());
			if (!samePassword) {
				String encodedPassword = shaPasswordEncoder.encodePassword(
						user.getPassword(), user.getUserName());
				savedUser.setPassword(encodedPassword);
			}
		}
		savedUser = userDAO.save(savedUser);
		if (!user.getRoleId().equals(savedUser.getRoleId())) {
			userRoleDAO.delete(savedUser.getUserRole());
			Role role = roleDAO.findOne(user.getRoleId());
			UserRole userRole = new UserRole(savedUser, role);
			userRole = userRoleDAO.save(userRole);
			savedUser.setUserRole(userRole);
		}
		return savedUser;
	}

	/**
	 * Find all.
	 * 
	 * @param page
	 *            the page
	 * @param rows
	 *            the rows
	 * @param predicate
	 *            the predicate
	 * @param params
	 *            the params
	 * @return the page
	 * @see com.telenoetica.service.UserService#findALL(int, int,
	 *      java.lang.String, java.util.Map)
	 */
	@Override
	public Page<User> findALL(int page, int rows, String predicate,
			Map<String, Object> params) {
		String ejbql = "from User where " + predicate;
		return genericQueryExecutorDAO.executeQuery(ejbql, User.class, params,
				page, rows);
	}

	/**
	 * Find by user name.
	 * 
	 * @param userName
	 *            the user name
	 * @return the user
	 * @see com.telenoetica.service.UserService#findByUserName(java.lang.String)
	 */
	@Override
	public User findByUserName(String userName) {
		return userDAO.findByUserName(userName);
	}
}
