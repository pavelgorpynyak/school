package ruhogwartsschool.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ruhogwartsschool.service.InfoService;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final InfoService infoService;

    public InfoController( InfoService infoService ) {
        this.infoService = infoService;
    }

    @GetMapping
    public void testParallelStream() {

    }
}
