package com.baffintech.bandcraft.controller;

import org.springframework.stereotype.*;
import lombok.extern.slf4j.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Slf4j
@Controller
public class IndexController {

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView response = new ModelAndView("index");
        return response;
    }
}

