package org.launchcode.PlatePlanner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String index(Model model) {
        return "index";
    }

}