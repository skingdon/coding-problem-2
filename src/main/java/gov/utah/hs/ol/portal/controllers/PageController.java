package gov.utah.hs.ol.portal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class PageController {

    @GetMapping(value = "/OLEntryPage")
    @ResponseStatus(HttpStatus.OK)
    public String headerPage(){
        return "welcome";
    }

    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    public String index(){
        return "welcome";
    }

    @GetMapping("/landing")
    @ResponseStatus(HttpStatus.OK)
    public String landing() {
        return "landing";
    }

    @GetMapping("/sites")
    @ResponseStatus(HttpStatus.OK)
    public String sites() {
        return "sites";
    }

    @GetMapping("/licenses")
    @ResponseStatus(HttpStatus.OK)
    public String licenses() {
        return "licenses";
    }

    @GetMapping("/license")
    @ResponseStatus(HttpStatus.OK)
    public String license() {
        return "license";
    }

    @GetMapping("/expiringLicenses")
    @ResponseStatus(HttpStatus.OK)
    public String expiringLicenses() {
        return "expiringLicenses";
    }

    @GetMapping("/violations")
    @ResponseStatus(HttpStatus.OK)
    public String violations() {
        return "violations";
    }

    @GetMapping("/applications")
    @ResponseStatus(HttpStatus.OK)
    public String applications() {
        return "applications";
    }

    @GetMapping("/loginError")
    @ResponseStatus(HttpStatus.OK)
    public String loginError() {
        return "login_error";
    }

    @GetMapping("/application/{page}")
    @ResponseStatus(HttpStatus.OK)
    public String applicationPage(@PathVariable(name = "page", required = false) String page) {
        switch (page) {
            case "new" : return "application_progress";
            case "site" : return "application_site";
            case "initial": return "initial_questions";
            case "renewal": return "renewal_questions";
            case "governance": return "program_governance";
            case "documents": return "application_documents";
            default : return "application_progress";
        }
    }
}
