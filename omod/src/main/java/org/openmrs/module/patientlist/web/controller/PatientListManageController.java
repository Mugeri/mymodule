/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.patientlist.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.*;
import org.openmrs.api.PatientService;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.module.idgen.service.IdentifierSourceService;
import org.openmrs.validator.PatientIdentifierValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * The main controller.
 */
@Controller
public class  PatientListManageController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@RequestMapping(value = "/module/patientlist/listPatients.form", method = RequestMethod.GET)
	public void manage(ModelMap model) {
		model.addAttribute("user", Context.getAuthenticatedUser());
		List<Patient> patientList=Context.getPatientService().getAllPatients();
		model.addAttribute("patientList", patientList);

	}
	@RequestMapping(value = "/module/patientlist/addPatient.form", method = RequestMethod.GET)
	public void registrationForm(ModelMap model) {
		model.addAttribute("user", Context.getAuthenticatedUser());

	}

	@RequestMapping(value="/module/patientlist/addPatient.form", method = RequestMethod.POST)
	public static Patient addPatient(@RequestParam("first")String first,@RequestParam("middle")String middle,@RequestParam("last")String last,
									 @RequestParam("gender")String gender,@RequestParam("dob") String dob,@RequestParam("nationalId") String nationalId) throws ParseException {
		PersonService personService = Context.getPersonService();
		PatientService patientService = Context.getPatientService();

		//change dob from string to date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		java.util.Date date = sdf.parse(dob);
		java.sql.Date sqlDate = new Date(date.getTime());

		//Initially create person
		Person person = new Person();
		PersonName name = new PersonName();
		name.setGivenName(first);
		name.setMiddleName(middle);
		name.setFamilyName(last);
		person.addName(name);
		person.setGender(gender);
		person.setBirthdate(sqlDate);
		personService.savePerson(person);
		Context.evictFromSession(person);

		//Then try create patient from person
		String TRIAL_KEY = "patientlist.idType";
		String TRIAL_ID = Context.getAdministrationService().getGlobalProperty(TRIAL_KEY);
		Patient patient = new Patient(person);
		PatientIdentifier openmrsId = new PatientIdentifier();
		//generating openmrs id
		PatientIdentifierType openmrsIdType = patientService.getPatientIdentifierTypeByName(TRIAL_ID);
	//	String generated = Context.getService(IdentifierSourceService.class).generateIdentifier(openmrsIdType, "PatientList");
		openmrsId.setIdentifierType(openmrsIdType);
		openmrsId.setDateCreated(new java.util.Date());
		openmrsId.setLocation(Context.getLocationService().getDefaultLocation());
		openmrsId.setIdentifier("nationalId");
		openmrsId.setVoided(false);
		PatientIdentifierValidator.validateIdentifier(openmrsId);
		patient.addIdentifier(openmrsId);
		patientService.savePatient(patient);
		return patient;
		}
}
