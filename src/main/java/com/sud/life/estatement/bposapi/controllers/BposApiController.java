package com.sud.life.estatement.bposapi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sud/estatement")
public class BposApiController {


    @GetMapping("/test")
    public String testApp(){
        log.debug("test::testApp invoked");
        return "working";
   }



}
