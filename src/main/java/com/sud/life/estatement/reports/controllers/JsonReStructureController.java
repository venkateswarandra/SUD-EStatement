package com.sud.life.estatement.reports.controllers;


import com.sud.life.estatement.reports.entity.DataSourceNew;
import com.sud.life.estatement.reports.entity.DataSourceOld;
import com.sud.life.estatement.reports.entity.RootWrapper;
import com.sud.life.estatement.reports.services.JsonReStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/re-structure")
public class JsonReStructureController {

    @Autowired
    private JsonReStructureService jsonReStructureService;

    @PostMapping("/old-to-new")
    public DataSourceNew ausChange(@RequestBody RootWrapper rootWrapper){
        DataSourceOld dataSourceOld = rootWrapper.getDataSource();
        System.out.println(dataSourceOld);
        return jsonReStructureService.ausChange(dataSourceOld);
    }

}
