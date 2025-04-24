package com.sud.life.estatement.bposapi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/sud/eStatement")
public class BposApiController {
    @GetMapping("/test")
    public String testApp(){
        log.debug("test::testApp invoked");
        return "working";
   }





}
