package be.helb.controller;

import be.helb.model.Platform;
import be.helb.service.PlatformService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlatformController {
    PlatformService platformService;

    public PlatformController(PlatformService platformService){
        this.platformService = platformService;
    }

    @GetMapping("/get all platforms")
    public List<Platform> getAllPlatforms(){
        List<Platform> platformList = platformService.getAllPlatforms();
        return platformList;
    }
}
