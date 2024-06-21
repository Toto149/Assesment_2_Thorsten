package controller;

import lombok.RequiredArgsConstructor;
import model.Wanderer;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PlanerController {

    private final Service service;

    @GetMapping("wanderer/get")
    public List<Wanderer> getAllWanderer(){
        return service.getAllWanderer();
    }
}
