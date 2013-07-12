package org.twi.imageshare.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/terms")
public class TermsController {

	@RequestMapping(method = RequestMethod.GET)
	public String statusView() {
		return "termsPage";
	}

}
