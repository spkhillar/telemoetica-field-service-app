package com.telenoetica.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telenoetica.jpa.entities.MaintenanceVisit;
import com.telenoetica.jpa.entities.Site;
import com.telenoetica.service.MaintenanceVisitService;
import com.telenoetica.service.SiteService;
import com.telenoetica.service.SpareUtilizationReportService;
import com.telenoetica.service.util.ServiceUtil;

@Service("spareUtilizationReportService")
public class SpareUtilizationReportServiceImpl extends AbstractBaseService
		implements SpareUtilizationReportService {

	private static final Logger LOGGER = Logger
			.getLogger(SpareUtilizationReportService.class);

	@Autowired
	private MaintenanceVisitService maintenanceVisitService;

	private int topGasketkitPM;
	private int bottomgasketkitPM;
	private int cylinderheadgasketPM;
	private int frontoilsealPM;
	private int backOilsealPM;
	private int valvesealPM;
	private int pistonPM;
	private int pistonassemblyPM;
	private int fuelLiftpumpPM;
	private int waterPumpPM;
	private int connectingrodbearingPM;
	private int mainBearingPM;
	private int trustwasherPM;
	private int radiatorCoolantPM;
	private int tophosePM;
	private int bottomHosePM;
	private int highwatertempPM;
	private int lowoilPressureSwitchPM;
	private int radiatorHoseClipsPM;
	private int fanBeltPM;
	private int airFilterPM;
	private int fuelFilterPM;
	private int oilFilterPM;
	private int injectorpumprotorPM;
	private int kickStarterPM;
	private int chargingAlternatorPM;
	private int injectorpumpbrushPM;
	private int injectorpumpcamrollerPM;
	private int relay12voltPM;
	private int radiatorPM;
	private int batteryPM;
	private int fuelfilterhousingPM;
	private int engineOilPM;

	private int topGasketkitCM;
	private int bottomgasketkitCM;
	private int cylinderheadgasketCM;
	private int frontoilsealCM;
	private int backOilsealCM;
	private int valvesealCM;
	private int pistonCM;
	private int pistonassemblyCM;
	private int fuelLiftpumpCM;
	private int waterPumpCM;
	private int connectingrodbearingCM;
	private int mainBearingCM;
	private int trustwasherCM;
	private int radiatorCoolantCM;
	private int tophoseCM;
	private int bottomHoseCM;
	private int highwatertempCM;
	private int lowoilPressureSwitchCM;
	private int radiatorHoseClipsCM;
	private int fanBeltCM;
	private int airFilterCM;
	private int fuelFilterCM;
	private int oilFilterCM;
	private int injectorpumprotorCM;
	private int kickStarterCM;
	private int chargingAlternatorCM;
	private int injectorpumpbrushCM;
	private int injectorpumpcamrollerCM;
	private int relay12voltCM;
	private int radiatorCM;
	private int batteryCM;
	private int fuelfilterhousingCM;
	private int engineOilCM;

	private int totalTopGasketkit;
	private int totalBottomgasketkit;
	private int totalCylinderheadgasket;
	private int totalFrontoilseal;
	private int totalBackOilseal;
	private int totalValveseal;
	private int totalPiston;
	private int totalPistonassembly;
	private int totalFuelLiftpump;
	private int totalWaterPump;
	private int totalConnectingrodbearing;
	private int totalMainBearing;
	private int totalTrustwasher;
	private int totalRadiatorCoolant;
	private int totalTophose;
	private int totalBottomHose;
	private int totalHighwatertemp;
	private int totalLowoilPressureSwitch;
	private int totalRadiatorHoseClips;
	private int totalFanBelt;
	private int totalAirFilter;
	private int totalFuelFilter;
	private int totalOilFilter;
	private int totalInjectorpumprotor;
	private int totalKickStarter;
	private int totalChargingAlternator;
	private int totalInjectorpumpbrush;
	private int totalInjectorpumpcamroller;
	private int totalRelay12volt;
	private int totalRadiator;
	private int totalBattery;
	private int totalFuelfilterhousing;
	private int totalEngineOil;

	int totalCmVisitCounter;
	int totalPmVisitCounter;

	@Autowired
	private SiteService siteService;

	@Override
	public String createNewReport(final Date forDate) throws Exception {
		LOGGER.debug("Service SpareUtilizationReportService Started");
		List<Site> siteList = siteService.getSites();
		String configuredFileName = systemConfiguration
				.getSpareUtilizationReportTemplate();
		LOGGER.debug("SpareUtilizationReport template is : "
				+ configuredFileName);
		InputStream is = this.getClass()
				.getResourceAsStream(configuredFileName);
		// create a POIFSFileSystem object to read the data

		POIFSFileSystem fs = new POIFSFileSystem(is);

		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		resetVisitFieldsTotal();
		setSheetTwoData(workbook.getSheetAt(1), siteList, forDate);
		// get total for sheet 1
		Map<String, String> sortedMap = getSpareFinalMap();
		setSheetOneData(workbook.getSheetAt(0), sortedMap);
		String reportName = closeReport(workbook);
		sendEmail(reportName);
		return reportName;
	}

	private void setSheetOneData(final HSSFSheet sheet,
			final Map<String, String> sortedMap) {
		HSSFRow row;
		HSSFCell cell;
		int rNum = 3;
		row = sheet.getRow(rNum++);
		cell = row.getCell(3);
		cell.setCellValue(totalCmVisitCounter + totalPmVisitCounter);
		row = sheet.getRow(rNum++);
		cell = row.getCell(3);
		cell.setCellValue(totalPmVisitCounter);
		row = sheet.getRow(rNum++);
		cell = row.getCell(3);
		cell.setCellValue(totalCmVisitCounter);
		row = sheet.getRow(rNum++);
		row = sheet.getRow(rNum++);
		for (int i = 1; i < 11; i++) {
			row = sheet.getRow(rNum++);
			if (sortedMap.size() > i - 1) {
				cell = row.getCell(2);
				cell.setCellValue((String) sortedMap.keySet().toArray()[sortedMap
						.size() - i]);
				cell = row.getCell(3);
				cell.setCellValue((Integer) sortedMap.values().toArray()[sortedMap
						.size() - i]);
			}
		}
	}

	private void setSheetTwoData(final HSSFSheet sheet,
			final List<Site> siteList, final Date forDate) {
		HSSFRow row;
		HSSFCell cell;
		int rNum = 2;
		int cmVisitCounter = 0;
		int pmVisitCounter = 0;
		resetVisitFields();
		for (int i = 0; i < siteList.size(); i++) {
			Site siteName = siteList.get(i);
			List<MaintenanceVisit> maintenanceVisitL = maintenanceVisitService
					.findBySiteAndCreatedAtBetween(siteName, forDate);
			int rNumPrev = rNum;
			String siteid = "";
			for (int j = 0; j < maintenanceVisitL.size(); j++) {
				MaintenanceVisit maintenanceVisit = maintenanceVisitL.get(j);
				siteid = maintenanceVisit.getSiteId();
				if (maintenanceVisit.getCategoryOfMaintenance()
						.startsWith("PM")) {
					countPMVisits(maintenanceVisit);
					pmVisitCounter = pmVisitCounter + 1;
					totalPmVisitCounter = totalPmVisitCounter + pmVisitCounter;
					pmVisitCounter = 0;

				} else {
					countCMVisits(maintenanceVisit);
					cmVisitCounter = cmVisitCounter + 1;
					totalCmVisitCounter = totalCmVisitCounter + cmVisitCounter;
					cmVisitCounter = 0;
				}
			}
			HashMap sparePMMap = new HashMap();
			HashMap spareCMMap = new HashMap();
			sparePMMap = preparePMSpareMap(sparePMMap);
			spareCMMap = prepareCMSpareMap(spareCMMap);
			if ((sparePMMap.size() != 0) || (spareCMMap.size() != 0)) {
				row = sheet.createRow(rNum++);
				cell = row.createCell(0);
				cell.setCellValue(ServiceUtil.checkAndReturnValue(siteid));
				cell = row.createCell(1);
				cell.setCellValue(cmVisitCounter);
				cell = row.createCell(2);
				cell.setCellValue(pmVisitCounter);
				cell = row.createCell(3);
				if (sparePMMap.size() > 0) {
					cell.setCellValue((String) sparePMMap.keySet().toArray()[0]);
				}
				cell = row.createCell(4);
				if (sparePMMap.size() > 0) {
					cell.setCellValue((Integer) sparePMMap.values().toArray()[0]);
				}
				cell = row.createCell(5);
				if (spareCMMap.size() > 0) {
					cell.setCellValue((String) spareCMMap.keySet().toArray()[0]);
				}
				cell = row.createCell(6);
				if (spareCMMap.size() > 0) {
					cell.setCellValue((Integer) spareCMMap.values().toArray()[0]);
				}
				int maxCount = 0;
				if (sparePMMap.size() > spareCMMap.size()) {
					maxCount = sparePMMap.size();
				} else {
					maxCount = spareCMMap.size();
				}
				for (int k = 1; k < maxCount; k++) {

					row = sheet.createRow(rNum++);
					cell = row.createCell(0);
					cell = row.createCell(1);
					cell = row.createCell(2);
					cell = row.createCell(3);
					if (sparePMMap.size() > k) {
						cell.setCellValue((String) sparePMMap.keySet()
								.toArray()[k]);
					} else {
						cell.setCellValue("");
					}
					cell = row.createCell(4);
					if (sparePMMap.size() > k) {
						cell.setCellValue((Integer) sparePMMap.values()
								.toArray()[k]);
					} else {
						cell.setCellValue("");

					}
					cell = row.createCell(5);
					if (spareCMMap.size() > k) {
						cell.setCellValue((String) spareCMMap.keySet()
								.toArray()[k]);
					} else {
						cell.setCellValue("");

					}
					cell = row.createCell(6);
					if (spareCMMap.size() > k) {
						cell.setCellValue((Integer) spareCMMap.values()
								.toArray()[k]);
					} else {
						cell.setCellValue("");

					}

				}
			}
			if ((rNumPrev != rNum) && ((rNum - rNumPrev) > 1)) {
				sheet.groupRow(rNumPrev + 1, rNum);
				sheet.setRowGroupCollapsed(rNumPrev + 1, true);
			}
			resetVisitFields();
		}
	}

	public String closeReport(final HSSFWorkbook workbook) throws Exception {
		String reportName = addTimeInFileName(systemConfiguration
				.getSparesUtilizationReportFileName());

		String reportFilePath = systemConfiguration
				.getSparesUtilizationReportDirectory()
				+ File.separator
				+ reportName;
		File file = new File(reportFilePath);
		// write the new changes to a new file
		FileOutputStream fos = new FileOutputStream(file);

		LOGGER.debug("RETURNED FILE PATH: " + file.getAbsolutePath());
		workbook.write(fos);
		fos.flush();
		fos.close();
		return reportFilePath;
	}

	private String addTimeInFileName(String name) {
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int seconds = cal.get(Calendar.SECOND);

		name += month + "_" + cal.get(Calendar.DAY_OF_MONTH) + "_"
				+ cal.get(Calendar.YEAR) + "_" + hour + "_" + minute + "_"
				+ seconds + ".xls";
		LOGGER.debug("Creating new excel doc named: " + name);
		return name;
	}

	private void countPMVisits(final MaintenanceVisit mv) {

		ArrayList<String> spareUsed = new ArrayList<String>();
		spareUsed.add(mv.getSparesUsedItemsReplaced1());
		spareUsed.add(mv.getSparesUsedItemsReplaced2());
		spareUsed.add(mv.getSparesUsedItemsReplaced3());
		spareUsed.add(mv.getSparesUsedItemsReplaced4());
		spareUsed.add(mv.getSparesUsedItemsReplaced5());
		spareUsed.add(mv.getSparesUsedItemsReplaced6());
		spareUsed.add(mv.getCosumablesUsed1());
		spareUsed.add(mv.getCosumablesUsed2());
		spareUsed.add(mv.getCosumablesUsed3());
		spareUsed.add(mv.getCosumablesUsed4());
		spareUsed.add(mv.getCosumablesUsed5());
		spareUsed.add(mv.getCosumablesUsed6());
		topGasketkitPM = Collections.frequency(spareUsed, "Top Gasket Kit");
		totalTopGasketkit = totalTopGasketkit + topGasketkitPM;
		bottomgasketkitPM = Collections.frequency(spareUsed,
				"Bottom Gasket Kit");
		totalBottomgasketkit = totalBottomgasketkit + bottomgasketkitPM;
		cylinderheadgasketPM = Collections.frequency(spareUsed,
				"Cylinder Head Gasket");
		totalCylinderheadgasket = totalCylinderheadgasket
				+ cylinderheadgasketPM;
		frontoilsealPM = Collections.frequency(spareUsed, "Front Oil Seal");
		totalFrontoilseal = totalFrontoilseal + frontoilsealPM;
		backOilsealPM = Collections.frequency(spareUsed, "Back Oil Seal");
		totalBackOilseal = totalBackOilseal + backOilsealPM;
		valvesealPM = Collections.frequency(spareUsed, "Valve Seal");
		totalValveseal = totalValveseal + valvesealPM;
		pistonPM = Collections.frequency(spareUsed, "Piston");
		totalPiston = totalPiston + pistonPM;
		pistonassemblyPM = Collections.frequency(spareUsed, "Piston Assembly");
		totalPistonassembly = totalPistonassembly + pistonassemblyPM;
		fuelLiftpumpPM = Collections.frequency(spareUsed,
				"Fuel Lift Pump(Ecoplus)");
		totalFuelLiftpump = totalFuelLiftpump + fuelLiftpumpPM;
		waterPumpPM = Collections.frequency(spareUsed, "Water Pump");
		totalWaterPump = totalWaterPump + waterPumpPM;
		connectingrodbearingPM = Collections.frequency(spareUsed,
				"Connecting Rod Bearing");
		totalConnectingrodbearing = totalConnectingrodbearing
				+ connectingrodbearingPM;
		mainBearingPM = Collections.frequency(spareUsed, "Main Bearing");
		totalMainBearing = totalMainBearing + mainBearingPM;
		trustwasherPM = Collections.frequency(spareUsed, "Trust Washer");
		totalTrustwasher = totalTrustwasher + trustwasherPM;
		radiatorCoolantPM = Collections
				.frequency(spareUsed, "Radiator Coolant");
		totalRadiatorCoolant = totalRadiatorCoolant + radiatorCoolantPM;
		tophosePM = Collections.frequency(spareUsed, "Top Hose");
		totalTophose = totalTophose + tophosePM;
		bottomHosePM = Collections.frequency(spareUsed, "Bottom Hose");
		totalBottomHose = totalBottomHose + bottomHosePM;
		highwatertempPM = Collections.frequency(spareUsed,
				"High Water Temp:Switch(Big)");
		totalHighwatertemp = totalHighwatertemp + highwatertempPM;
		lowoilPressureSwitchPM = Collections.frequency(spareUsed,
				"Low Oil Pressure Switch");
		totalLowoilPressureSwitch = totalLowoilPressureSwitch
				+ lowoilPressureSwitchPM;
		radiatorHoseClipsPM = Collections.frequency(spareUsed,
				"Radiator Hose Clips");
		totalRadiatorHoseClips = totalRadiatorHoseClips + radiatorHoseClipsPM;
		fanBeltPM = Collections.frequency(spareUsed, "Fan Belt");
		totalFanBelt = totalFanBelt + fanBeltPM;
		airFilterPM = Collections.frequency(spareUsed, "Air Filter");
		totalAirFilter = totalAirFilter + airFilterPM;
		fuelFilterPM = Collections.frequency(spareUsed, "Fuel Filter");
		totalFuelFilter = totalFuelFilter + fuelFilterPM;
		oilFilterPM = Collections.frequency(spareUsed, "Oil Filter");
		totalOilFilter = totalOilFilter + oilFilterPM;
		injectorpumprotorPM = Collections.frequency(spareUsed,
				"Injector Pump Rotor(NewModel)");
		totalInjectorpumprotor = totalInjectorpumprotor + injectorpumprotorPM;
		kickStarterPM = Collections.frequency(spareUsed, "Kick Starter");
		totalKickStarter = totalKickStarter + kickStarterPM;
		chargingAlternatorPM = Collections.frequency(spareUsed,
				"Charging Alternator");
		totalChargingAlternator = totalChargingAlternator
				+ chargingAlternatorPM;
		injectorpumpbrushPM = Collections.frequency(spareUsed,
				"Injector Pump Brush");
		totalInjectorpumpbrush = totalInjectorpumpbrush + injectorpumpbrushPM;
		injectorpumpcamrollerPM = Collections.frequency(spareUsed,
				"Injector Pump Cam Roller");
		totalInjectorpumpcamroller = totalInjectorpumpcamroller
				+ injectorpumpcamrollerPM;
		relay12voltPM = Collections.frequency(spareUsed, "Relay 12 volt");
		totalRelay12volt = totalRelay12volt + relay12voltPM;
		radiatorPM = Collections.frequency(spareUsed, "Radiator");
		totalRadiator = totalRadiator + radiatorPM;
		batteryPM = Collections.frequency(spareUsed, "Battery(100Amps)");
		totalBattery = totalBattery + batteryPM;
		fuelfilterhousingPM = Collections.frequency(spareUsed,
				"Fuel Filter Housing");
		totalFuelfilterhousing = totalFuelfilterhousing + fuelfilterhousingPM;
		engineOilPM = Collections.frequency(spareUsed, "Engine Oil");
		totalEngineOil = totalEngineOil + engineOilPM;

	}

	private void countCMVisits(final MaintenanceVisit mv) {

		ArrayList<String> spareUsed = new ArrayList<String>();
		spareUsed.add(mv.getSparesUsedItemsReplaced1());
		spareUsed.add(mv.getSparesUsedItemsReplaced2());
		spareUsed.add(mv.getSparesUsedItemsReplaced3());
		spareUsed.add(mv.getSparesUsedItemsReplaced4());
		spareUsed.add(mv.getSparesUsedItemsReplaced5());
		spareUsed.add(mv.getSparesUsedItemsReplaced6());
		spareUsed.add(mv.getCosumablesUsed1());
		spareUsed.add(mv.getCosumablesUsed2());
		spareUsed.add(mv.getCosumablesUsed3());
		spareUsed.add(mv.getCosumablesUsed4());
		spareUsed.add(mv.getCosumablesUsed5());
		spareUsed.add(mv.getCosumablesUsed6());

		topGasketkitCM = Collections.frequency(spareUsed, "Top Gasket Kit");
		totalTopGasketkit = totalTopGasketkit + topGasketkitCM;
		bottomgasketkitCM = Collections.frequency(spareUsed,
				"Bottom Gasket Kit");
		totalBottomgasketkit = totalBottomgasketkit + bottomgasketkitCM;
		cylinderheadgasketCM = Collections.frequency(spareUsed,
				"Cylinder Head Gasket");
		totalCylinderheadgasket = totalCylinderheadgasket
				+ cylinderheadgasketCM;
		frontoilsealCM = Collections.frequency(spareUsed, "Front Oil Seal");
		totalFrontoilseal = totalFrontoilseal + frontoilsealCM;
		backOilsealCM = Collections.frequency(spareUsed, "Back Oil Seal");
		totalBackOilseal = totalBackOilseal + backOilsealCM;
		valvesealCM = Collections.frequency(spareUsed, "Valve Seal");
		totalValveseal = totalValveseal + valvesealCM;
		pistonCM = Collections.frequency(spareUsed, "Piston");
		totalPiston = totalPiston + pistonCM;
		pistonassemblyCM = Collections.frequency(spareUsed, "Piston Assembly");
		totalPistonassembly = totalPistonassembly + pistonassemblyCM;
		fuelLiftpumpCM = Collections.frequency(spareUsed,
				"Fuel Lift Pump(Ecoplus)");
		totalFuelLiftpump = totalFuelLiftpump + fuelLiftpumpCM;
		waterPumpCM = Collections.frequency(spareUsed, "Water Pump");
		totalWaterPump = totalWaterPump + waterPumpCM;
		connectingrodbearingCM = Collections.frequency(spareUsed,
				"Connecting Rod Bearing");
		totalConnectingrodbearing = totalConnectingrodbearing
				+ connectingrodbearingCM;
		mainBearingCM = Collections.frequency(spareUsed, "Main Bearing");
		totalMainBearing = totalMainBearing + mainBearingCM;
		trustwasherCM = Collections.frequency(spareUsed, "Trust Washer");
		totalTrustwasher = totalTrustwasher + trustwasherCM;
		radiatorCoolantCM = Collections
				.frequency(spareUsed, "Radiator Coolant");
		totalRadiatorCoolant = totalRadiatorCoolant + radiatorCoolantCM;
		tophoseCM = Collections.frequency(spareUsed, "Top Hose");
		totalTophose = totalTophose + tophoseCM;
		bottomHoseCM = Collections.frequency(spareUsed, "Bottom Hose");
		totalBottomHose = totalBottomHose + bottomHoseCM;
		highwatertempCM = Collections.frequency(spareUsed,
				"High Water Temp:Switch(Big)");
		totalHighwatertemp = totalHighwatertemp + highwatertempCM;
		lowoilPressureSwitchCM = Collections.frequency(spareUsed,
				"Low Oil Pressure Switch");
		totalLowoilPressureSwitch = totalLowoilPressureSwitch
				+ lowoilPressureSwitchCM;
		radiatorHoseClipsCM = Collections.frequency(spareUsed,
				"Radiator Hose Clips");
		totalRadiatorHoseClips = totalRadiatorHoseClips + radiatorHoseClipsCM;
		fanBeltCM = Collections.frequency(spareUsed, "Fan Belt");
		totalFanBelt = totalFanBelt + fanBeltCM;
		airFilterCM = Collections.frequency(spareUsed, "Air Filter");
		totalAirFilter = totalAirFilter + airFilterCM;
		fuelFilterCM = Collections.frequency(spareUsed, "Fuel Filter");
		totalFuelFilter = totalFuelFilter + fuelFilterCM;
		oilFilterCM = Collections.frequency(spareUsed, "Oil Filter");
		totalOilFilter = totalOilFilter + oilFilterCM;
		injectorpumprotorCM = Collections.frequency(spareUsed,
				"Injector Pump Rotor(NewModel)");
		totalInjectorpumprotor = totalInjectorpumprotor + injectorpumprotorCM;
		kickStarterCM = Collections.frequency(spareUsed, "Kick Starter");
		totalKickStarter = totalKickStarter + kickStarterCM;
		chargingAlternatorCM = Collections.frequency(spareUsed,
				"Charging Alternator");
		totalChargingAlternator = totalChargingAlternator
				+ chargingAlternatorCM;
		injectorpumpbrushCM = Collections.frequency(spareUsed,
				"Injector Pump Brush");
		totalInjectorpumpbrush = totalInjectorpumpbrush + injectorpumpbrushCM;
		injectorpumpcamrollerCM = Collections.frequency(spareUsed,
				"Injector Pump Cam Roller");
		totalInjectorpumpcamroller = totalInjectorpumpcamroller
				+ injectorpumpcamrollerCM;
		relay12voltCM = Collections.frequency(spareUsed, "Relay 12 volt");
		totalRelay12volt = totalRelay12volt + relay12voltCM;
		radiatorCM = Collections.frequency(spareUsed, "Radiator");
		totalRadiator = totalRadiator + radiatorCM;
		batteryCM = Collections.frequency(spareUsed, "Battery(100Amps)");
		totalBattery = totalBattery + batteryCM;
		fuelfilterhousingCM = Collections.frequency(spareUsed,
				"Fuel Filter Housing");
		totalFuelfilterhousing = totalFuelfilterhousing + fuelfilterhousingCM;
		engineOilCM = Collections.frequency(spareUsed, "Engine Oil");
		totalEngineOil = totalEngineOil + engineOilCM;
	}

	private HashMap preparePMSpareMap(final HashMap sparePMMap) {
		if (topGasketkitPM != 0) {
			sparePMMap.put("topGasketkit", topGasketkitPM);
		}
		if (bottomgasketkitPM != 0) {
			sparePMMap.put("Bottomgasketkit", bottomgasketkitPM);
		}
		if (cylinderheadgasketPM != 0) {
			sparePMMap.put("Cylinderheadgasket", cylinderheadgasketPM);
		}
		if (frontoilsealPM != 0) {
			sparePMMap.put("Frontoilseal", frontoilsealPM);
		}
		if (backOilsealPM != 0) {
			sparePMMap.put("BackOilseal", backOilsealPM);
		}
		if (valvesealPM != 0) {
			sparePMMap.put("Valveseal", valvesealPM);
		}
		if (pistonPM != 0) {
			sparePMMap.put("Piston", pistonPM);
		}
		if (pistonassemblyPM != 0) {
			sparePMMap.put("Pistonassembly", pistonassemblyPM);
		}
		if (fuelLiftpumpPM != 0) {
			sparePMMap.put("FuelLiftpump(Ecoplus)", fuelLiftpumpPM);
		}
		if (waterPumpPM != 0) {
			sparePMMap.put("WaterPump", waterPumpPM);
		}
		if (connectingrodbearingPM != 0) {
			sparePMMap.put("Connectingrodbearing", connectingrodbearingPM);
		}
		if (mainBearingPM != 0) {
			sparePMMap.put("MainBearing", mainBearingPM);
		}
		if (trustwasherPM != 0) {
			sparePMMap.put("Trustwasher", trustwasherPM);
		}
		if (radiatorCoolantPM != 0) {
			sparePMMap.put("RadiatorCoolant", radiatorCoolantPM);
		}
		if (tophosePM != 0) {
			sparePMMap.put("Tophose", tophosePM);
		}
		if (bottomHosePM != 0) {
			sparePMMap.put("BottomHose", bottomHosePM);
		}
		if (highwatertempPM != 0) {
			sparePMMap.put("Highwatertemp:Switch(Big)", highwatertempPM);
		}
		if (lowoilPressureSwitchPM != 0) {
			sparePMMap.put("LowoilPressureSwitch", lowoilPressureSwitchPM);
		}
		if (radiatorHoseClipsPM != 0) {
			sparePMMap.put("RadiatorHoseClips", radiatorHoseClipsPM);
		}
		if (fanBeltPM != 0) {
			sparePMMap.put("FanBelt", fanBeltPM);
		}
		if (airFilterPM != 0) {
			sparePMMap.put("AirFilter", airFilterPM);
		}
		if (fuelFilterPM != 0) {
			sparePMMap.put("FuelFilter", fuelFilterPM);
		}
		if (oilFilterPM != 0) {
			sparePMMap.put("OilFilter", oilFilterPM);
		}
		if (injectorpumprotorPM != 0) {
			sparePMMap.put("Injectorpumprotor(NewModel)", injectorpumprotorPM);
		}
		if (kickStarterPM != 0) {
			sparePMMap.put("KickStarter", kickStarterPM);
		}
		if (chargingAlternatorPM != 0) {
			sparePMMap.put("ChargingAlternator", chargingAlternatorPM);
		}
		if (injectorpumpbrushPM != 0) {
			sparePMMap.put("Injectorpumpbrush", injectorpumpbrushPM);
		}
		if (injectorpumpcamrollerPM != 0) {
			sparePMMap.put("Injectorpumpcamroller", injectorpumpcamrollerPM);
		}
		if (relay12voltPM != 0) {
			sparePMMap.put("Relay12volt", relay12voltPM);
		}
		if (radiatorPM != 0) {
			sparePMMap.put("Radiator", radiatorPM);
		}
		if (batteryPM != 0) {
			sparePMMap.put("Battery(100Amps)", batteryPM);
		}
		if (fuelfilterhousingPM != 0) {
			sparePMMap.put("Fuelfilterhousing", fuelfilterhousingPM);
		}
		if (engineOilPM != 0) {
			sparePMMap.put("EngineOil", engineOilPM);
		}

		return sparePMMap;

	}

	private HashMap prepareCMSpareMap(final HashMap spareCMMap) {
		if (topGasketkitCM != 0) {
			spareCMMap.put("topGasketkit", topGasketkitCM);
		}
		if (bottomgasketkitCM != 0) {
			spareCMMap.put("Bottomgasketkit", bottomgasketkitCM);
		}
		if (cylinderheadgasketCM != 0) {
			spareCMMap.put("Cylinderheadgasket", cylinderheadgasketCM);
		}
		if (frontoilsealCM != 0) {
			spareCMMap.put("Frontoilseal", frontoilsealCM);
		}
		if (backOilsealCM != 0) {
			spareCMMap.put("BackOilseal", backOilsealCM);
		}
		if (valvesealCM != 0) {
			spareCMMap.put("Valveseal", valvesealCM);
		}
		if (pistonCM != 0) {
			spareCMMap.put("Piston", pistonCM);
		}
		if (pistonassemblyCM != 0) {
			spareCMMap.put("Pistonassembly", pistonassemblyCM);
		}
		if (fuelLiftpumpCM != 0) {
			spareCMMap.put("FuelLiftpump(Ecoplus)", fuelLiftpumpCM);
		}
		if (waterPumpCM != 0) {
			spareCMMap.put("WaterPump", waterPumpCM);
		}
		if (connectingrodbearingCM != 0) {
			spareCMMap.put("Connectingrodbearing", connectingrodbearingCM);
		}
		if (mainBearingCM != 0) {
			spareCMMap.put("MainBearing", mainBearingCM);
		}
		if (trustwasherCM != 0) {
			spareCMMap.put("Trustwasher", trustwasherCM);
		}
		if (radiatorCoolantCM != 0) {
			spareCMMap.put("RadiatorCoolant", radiatorCoolantCM);
		}
		if (tophoseCM != 0) {
			spareCMMap.put("Tophose", tophoseCM);
		}
		if (bottomHoseCM != 0) {
			spareCMMap.put("BottomHose", bottomHoseCM);
		}
		if (highwatertempCM != 0) {
			spareCMMap.put("Highwatertemp:Switch(Big)", highwatertempCM);
		}
		if (lowoilPressureSwitchCM != 0) {
			spareCMMap.put("LowoilPressureSwitch", lowoilPressureSwitchCM);
		}
		if (radiatorHoseClipsCM != 0) {
			spareCMMap.put("RadiatorHoseClips", radiatorHoseClipsCM);
		}
		if (fanBeltCM != 0) {
			spareCMMap.put("FanBelt", fanBeltCM);
		}
		if (airFilterCM != 0) {
			spareCMMap.put("AirFilter", airFilterCM);
		}
		if (fuelFilterCM != 0) {
			spareCMMap.put("FuelFilter", fuelFilterCM);
		}
		if (oilFilterCM != 0) {
			spareCMMap.put("OilFilter", oilFilterCM);
		}
		if (injectorpumprotorCM != 0) {
			spareCMMap.put("Injectorpumprotor(NewModel)", injectorpumprotorCM);
		}
		if (kickStarterCM != 0) {
			spareCMMap.put("KickStarter", kickStarterCM);
		}
		if (chargingAlternatorCM != 0) {
			spareCMMap.put("ChargingAlternator", chargingAlternatorCM);
		}
		if (injectorpumpbrushCM != 0) {
			spareCMMap.put("Injectorpumpbrush", injectorpumpbrushCM);
		}
		if (injectorpumpcamrollerCM != 0) {
			spareCMMap.put("Injectorpumpcamroller", injectorpumpcamrollerCM);
		}
		if (relay12voltCM != 0) {
			spareCMMap.put("Relay12volt", relay12voltCM);
		}
		if (radiatorCM != 0) {
			spareCMMap.put("Radiator", radiatorCM);
		}
		if (batteryCM != 0) {
			spareCMMap.put("Battery(100Amps)", batteryCM);
		}
		if (fuelfilterhousingCM != 0) {
			spareCMMap.put("Fuelfilterhousing", fuelfilterhousingCM);
		}
		if (engineOilCM != 0) {
			spareCMMap.put("EngineOil", engineOilCM);
		}

		return spareCMMap;

	}

	private Map getSpareFinalMap() {

		HashMap spareFinalMap = new HashMap();
		if (totalTopGasketkit != 0) {
			spareFinalMap.put("topGasketkit", totalTopGasketkit);
		}
		if (totalBottomgasketkit != 0) {
			spareFinalMap.put("Bottomgasketkit", totalBottomgasketkit);
		}
		if (totalCylinderheadgasket != 0) {
			spareFinalMap.put("Cylinderheadgasket", totalCylinderheadgasket);
		}
		if (totalFrontoilseal != 0) {
			spareFinalMap.put("Frontoilseal", totalFrontoilseal);
		}
		if (totalBackOilseal != 0) {
			spareFinalMap.put("BackOilseal", totalBackOilseal);
		}
		if (totalValveseal != 0) {
			spareFinalMap.put("Valveseal", totalValveseal);
		}
		if (totalPiston != 0) {
			spareFinalMap.put("Piston", totalPiston);
		}
		if (totalPistonassembly != 0) {
			spareFinalMap.put("Pistonassembly", totalPistonassembly);
		}
		if (totalFuelLiftpump != 0) {
			spareFinalMap.put("FuelLiftpump(Ecoplus)", totalFuelLiftpump);
		}
		if (totalWaterPump != 0) {
			spareFinalMap.put("WaterPump", totalWaterPump);
		}
		if (totalConnectingrodbearing != 0) {
			spareFinalMap
					.put("Connectingrodbearing", totalConnectingrodbearing);
		}
		if (totalMainBearing != 0) {
			spareFinalMap.put("MainBearing", totalMainBearing);
		}
		if (totalTrustwasher != 0) {
			spareFinalMap.put("Trustwasher", totalTrustwasher);
		}
		if (totalRadiatorCoolant != 0) {
			spareFinalMap.put("RadiatorCoolant", totalRadiatorCoolant);
		}
		if (totalTophose != 0) {
			spareFinalMap.put("Tophose", totalTophose);
		}
		if (totalBottomHose != 0) {
			spareFinalMap.put("BottomHose", totalBottomHose);
		}
		if (totalHighwatertemp != 0) {
			spareFinalMap.put("Highwatertemp:Switch(Big)", totalHighwatertemp);
		}
		if (totalLowoilPressureSwitch != 0) {
			spareFinalMap
					.put("LowoilPressureSwitch", totalLowoilPressureSwitch);
		}
		if (totalRadiatorHoseClips != 0) {
			spareFinalMap.put("RadiatorHoseClips", totalRadiatorHoseClips);
		}
		if (totalFanBelt != 0) {
			spareFinalMap.put("FanBelt", totalFanBelt);
		}
		if (totalAirFilter != 0) {
			spareFinalMap.put("AirFilter", totalAirFilter);
		}
		if (totalFuelFilter != 0) {
			spareFinalMap.put("FuelFilter", totalFuelFilter);
		}
		if (totalOilFilter != 0) {
			spareFinalMap.put("OilFilter", totalOilFilter);
		}
		if (totalInjectorpumprotor != 0) {
			spareFinalMap.put("Injectorpumprotor(NewModel)",
					totalInjectorpumprotor);
		}
		if (totalKickStarter != 0) {
			spareFinalMap.put("KickStarter", totalKickStarter);
		}
		if (totalChargingAlternator != 0) {
			spareFinalMap.put("ChargingAlternator", totalChargingAlternator);
		}
		if (totalInjectorpumpbrush != 0) {
			spareFinalMap.put("Injectorpumpbrush", totalInjectorpumpbrush);
		}
		if (totalInjectorpumpcamroller != 0) {
			spareFinalMap.put("Injectorpumpcamroller",
					totalInjectorpumpcamroller);
		}
		if (totalRelay12volt != 0) {
			spareFinalMap.put("Relay12volt", totalRelay12volt);
		}
		if (totalRadiator != 0) {
			spareFinalMap.put("Radiator", totalRadiator);
		}
		if (totalBattery != 0) {
			spareFinalMap.put("Battery(100Amps)", totalBattery);
		}
		if (totalFuelfilterhousing != 0) {
			spareFinalMap.put("Fuelfilterhousing", totalFuelfilterhousing);
		}
		if (totalEngineOil != 0) {
			spareFinalMap.put("EngineOil", totalEngineOil);
		}
		Map<String, String> sortedSpareFinalMap = sortByComparator(spareFinalMap);
		return sortedSpareFinalMap;
	}

	private static Map sortByComparator(final Map unsortMap) {

		List list = new LinkedList(unsortMap.entrySet());
		Collections.sort(list, new Comparator() {
			@Override
			public int compare(final Object o1, final Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
			}
		});
		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	private void resetVisitFields() {

		topGasketkitPM = 0;
		bottomgasketkitPM = 0;
		cylinderheadgasketPM = 0;
		frontoilsealPM = 0;
		backOilsealPM = 0;
		valvesealPM = 0;
		pistonPM = 0;
		pistonassemblyPM = 0;
		fuelLiftpumpPM = 0;
		waterPumpPM = 0;
		connectingrodbearingPM = 0;
		mainBearingPM = 0;
		trustwasherPM = 0;
		radiatorCoolantPM = 0;
		tophosePM = 0;
		bottomHosePM = 0;
		highwatertempPM = 0;
		lowoilPressureSwitchPM = 0;
		radiatorHoseClipsPM = 0;
		fanBeltPM = 0;
		airFilterPM = 0;
		fuelFilterPM = 0;
		oilFilterPM = 0;
		injectorpumprotorPM = 0;
		kickStarterPM = 0;
		chargingAlternatorPM = 0;
		injectorpumpbrushPM = 0;
		injectorpumpcamrollerPM = 0;
		relay12voltPM = 0;
		radiatorPM = 0;
		batteryPM = 0;
		fuelfilterhousingPM = 0;
		engineOilPM = 0;

		topGasketkitCM = 0;
		bottomgasketkitCM = 0;
		cylinderheadgasketCM = 0;
		frontoilsealCM = 0;
		backOilsealCM = 0;
		valvesealCM = 0;
		pistonCM = 0;
		pistonassemblyCM = 0;
		fuelLiftpumpCM = 0;
		waterPumpCM = 0;
		connectingrodbearingCM = 0;
		mainBearingCM = 0;
		trustwasherCM = 0;
		radiatorCoolantCM = 0;
		tophoseCM = 0;
		bottomHoseCM = 0;
		highwatertempCM = 0;
		lowoilPressureSwitchCM = 0;
		radiatorHoseClipsCM = 0;
		fanBeltCM = 0;
		airFilterCM = 0;
		fuelFilterCM = 0;
		oilFilterCM = 0;
		injectorpumprotorCM = 0;
		kickStarterCM = 0;
		chargingAlternatorCM = 0;
		injectorpumpbrushCM = 0;
		injectorpumpcamrollerCM = 0;
		relay12voltCM = 0;
		radiatorCM = 0;
		batteryCM = 0;
		fuelfilterhousingCM = 0;
		engineOilCM = 0;

	}

	/**
	 * This methood will be called before start counting site visit data.
	 */
	private void resetVisitFieldsTotal() {

		totalTopGasketkit = 0;
		totalBottomgasketkit = 0;
		totalCylinderheadgasket = 0;
		totalFrontoilseal = 0;
		totalBackOilseal = 0;
		totalValveseal = 0;
		totalPiston = 0;
		totalPistonassembly = 0;
		totalFuelLiftpump = 0;
		totalWaterPump = 0;
		totalConnectingrodbearing = 0;
		totalMainBearing = 0;
		totalTrustwasher = 0;
		totalRadiatorCoolant = 0;
		totalTophose = 0;
		totalBottomHose = 0;
		totalHighwatertemp = 0;
		totalLowoilPressureSwitch = 0;
		totalRadiatorHoseClips = 0;
		totalFanBelt = 0;
		totalAirFilter = 0;
		totalFuelFilter = 0;
		totalOilFilter = 0;
		totalInjectorpumprotor = 0;
		totalKickStarter = 0;
		totalChargingAlternator = 0;
		totalInjectorpumpbrush = 0;
		totalInjectorpumpcamroller = 0;
		totalRelay12volt = 0;
		totalRadiator = 0;
		totalBattery = 0;
		totalFuelfilterhousing = 0;
		totalEngineOil = 0;

		totalCmVisitCounter = 0;
		totalPmVisitCounter = 0;
	}

}
