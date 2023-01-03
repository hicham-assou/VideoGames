package be.helb.service;

import be.helb.DAO.PlatformDAO;
import be.helb.model.Game;
import be.helb.model.Platform;
import org.springframework.stereotype.Service;

;import java.util.List;
import java.util.Optional;

@Service
public class PlatformService {

    private PlatformDAO platformDAO;
    public PlatformService(PlatformDAO platformDAO) {
        this.platformDAO = platformDAO;
    }

    public List<Platform> getAllPlatforms(){
        List<Platform> platformList = platformDAO.findAll();
        return platformList;
    }
}
