/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.web.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telenoetica.jpa.entities.CallOutVisit;
import com.telenoetica.jpa.entities.Client;
import com.telenoetica.jpa.entities.DieselVendor;
import com.telenoetica.jpa.entities.DieselVisit;
import com.telenoetica.jpa.entities.Fault;
import com.telenoetica.jpa.entities.MaintenanceVisit;
import com.telenoetica.jpa.entities.MaintenanceVisitCategory;
import com.telenoetica.jpa.entities.RoutineVisit;
import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.entities.Spare;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.service.AndroidApplicationDownloadHistoryService;
import com.telenoetica.service.AndroidHomeService;
import com.telenoetica.service.CallOutVisitService;
import com.telenoetica.service.ClientService;
import com.telenoetica.service.DieselVendorService;
import com.telenoetica.service.DieselVisitService;
import com.telenoetica.service.FaultService;
import com.telenoetica.service.MaintenanceVisitCategoryService;
import com.telenoetica.service.MaintenanceVisitService;
import com.telenoetica.service.RoutineVisitService;
import com.telenoetica.service.SiteService;
import com.telenoetica.service.SpareService;
import com.telenoetica.util.model.HomeAndroidObject;
import com.telenoetica.util.model.HomeDataObject;
import com.telenoetica.web.rest.RestResponse;
import com.telenoetica.web.rest.WebHomeData;

/**
 * Handles requests for the application home page.
 * 
 * @author Shiv Prasad Khillar
 */
@Controller
public class HomeController extends BaseController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/** The fault service. */
	@Autowired
	private FaultService faultService;

	/** The site service. */
	@Autowired
	private SiteService siteService;

	/** The spare service. */
	@Autowired
	private SpareService spareService;

	/** The client service. */
	@Autowired
	private ClientService clientService;

	/** The maintenance visit category service. */
	@Autowired
	private MaintenanceVisitCategoryService maintenanceVisitCategoryService;

	/** The call out visit service. */
	@Autowired
	private CallOutVisitService callOutVisitService;

	/** The diesel visit service. */
	@Autowired
	private DieselVisitService dieselVisitService;

	/** The routine visit service. */
	@Autowired
	private RoutineVisitService routineVisitService;

	/** The maintenance visit service. */
	@Autowired
	private MaintenanceVisitService maintenanceVisitService;

	/** The android home service. */
	@Autowired
	private AndroidHomeService androidHomeService;

	@Autowired
	private DieselVendorService dieselVendorService;

	@Autowired
	private AndroidApplicationDownloadHistoryService androidApplicationDownloadHistoryService;

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @param locale
	 *            the locale
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(final Locale locale, final Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		return "home";
	}

	/**
	 * Gets the routine visit.
	 * 
	 * @return the routine visit
	 */
	@RequestMapping(value = "/rt", method = RequestMethod.GET)
	@ResponseBody
	public CallOutVisit getRoutineVisit() {
		User user = new User();
		Site site = new Site();

		user.setEmail("shivprasad.khillar@gmial.com");
		user.setEnabled(true);
		user.setFirstName("Shiv");
		user.setLastName("khillar");
		user.setUserName("spkhillar");
		user.setId(1L);

		site.setName("Bellandur");
		site.setId(2L);

		CallOutVisit callOutVisit = new CallOutVisit();
		callOutVisit.setAccessCode("AAA");

		callOutVisit.setUser(user);
		callOutVisit.setSite(site);
		callOutVisit.setTimeComplainReceived(new Date());
		callOutVisit.settimeFaultResolved(new Date());
		callOutVisit.setTimeReachedToSite(new Date());

		return callOutVisit;
	}

	/**
	 * Home objects.
	 * 
	 * @return the home data object
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	private HomeDataObject homeObjects() {
		List<Fault> faults = faultService.getFaults();
		List<Site> sites = siteService.getSites();
		List<Client> clients = clientService.getClients();
		List<Spare> spares = spareService.getSpares();
		List<DieselVendor> dieselVendor = dieselVendorService.getDieselVendor();

		List<MaintenanceVisitCategory> maintenanceCategories = maintenanceVisitCategoryService
				.getCategories();
		HomeDataObject homeDataObject = new HomeDataObject(sites, spares,
				clients, faults, maintenanceCategories, dieselVendor);
		return homeDataObject;
	}

	/**
	 * android rest login.
	 * 
	 * @return the home data object
	 */
	@RequestMapping(value = "/rest/auth/{userDeviceId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	private RestResponse restAuth(@PathVariable final String userDeviceId) {
		androidApplicationDownloadHistoryService.createOrUpdateDownloadForUser(
				userDeviceId, getCurrentLoggedinUserName());
		return new RestResponse(0, "Logged In");
	}

	/**
	 * android rest login.
	 * 
	 * @return the home data object
	 */
	@RequestMapping(value = "/rest/webHomeData", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public WebHomeData chartData() {
		Page<RoutineVisit> routineVisits = routineVisitService.findALL(1, 5,
				"desc", "createdAt");
		Page<CallOutVisit> callOutVisits = callOutVisitService.findALL(1, 5,
				"desc", "createdAt");
		Page<DieselVisit> dieselVisits = dieselVisitService.findALL(1, 5,
				"desc", "createdAt");
		Page<MaintenanceVisit> maintenanceVisits = maintenanceVisitService
				.findALL(1, 5, "desc", "createdAt");
		List<Integer> chartData = androidHomeService.getchartData();
		WebHomeData webHomeData = new WebHomeData(chartData,
				maintenanceVisits.getContent(), callOutVisits.getContent(),
				routineVisits.getContent(), dieselVisits.getContent());
		return webHomeData;
	}

	/**
	 * Under construction.
	 * 
	 * @return the string
	 */
	@RequestMapping(value = "/underconstruction", method = RequestMethod.GET)
	public String underConstruction() {
		return "under.construction";
	}

	/**
	 * Android home data.
	 * 
	 * @return the home android object
	 */
	@RequestMapping(value = "/rest/home", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public HomeAndroidObject androidHomeData() {
		Date start = new Date();
		HomeAndroidObject homeAndroidObject = androidHomeService
				.getAndroidHomeObject();
		System.err.println("...." + homeAndroidObject);
		Date end = new Date();
		long total = end.getTime() - start.getTime();
		System.err.println(total + "-----HomeAndroidObject.......End-----"
				+ new Date());
		return homeAndroidObject;
	}

	/**
	 * Help.
	 * 
	 * @return the string
	 */
	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public String help() {
		return "app.help";
	}

	/**
	 * Export android app.
	 * 
	 * @param httpServletResponse
	 *            the http servlet response
	 */
	@RequestMapping(value = "/downloadAndroidApp")
	@ResponseBody
	public void exportAndroidApp(final HttpServletResponse httpServletResponse) {
		boolean downloaded = androidHomeService
				.exportAndroidApp(httpServletResponse);
		if (downloaded) {
			androidApplicationDownloadHistoryService
					.createOrUpdateDownloadForUser(null,
							getCurrentLoggedinUserName());
		}
	}

	/**
	 * Export android app.
	 * 
	 * @param httpServletResponse
	 *            the http servlet response
	 */
	@RequestMapping(value = "help/downloadAndroidAppHelp")
	@ResponseBody
	public void exportAndroidAppHelp(
			final HttpServletResponse httpServletResponse) {
		androidHomeService.exportAndroidAppHelp(httpServletResponse);
	}

}
