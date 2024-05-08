package org.zerock.petmilyproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/homeless")
public class HomelessController {

    @GetMapping("/homeless")
    public void homeless(){

    }

    @GetMapping("/homelessDetail")
    public void homelessDetail(@RequestParam String data){

    }

}
