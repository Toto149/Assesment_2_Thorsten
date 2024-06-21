package controller;

import lombok.RequiredArgsConstructor;
import model.Wanderer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import service.PlanerService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PlanerController {

    private final PlanerService service;

    @GetMapping("/wanderer/get")
    public List<Wanderer> getAllWanderer(){
        return service.getAllWanderer();
    }
}
