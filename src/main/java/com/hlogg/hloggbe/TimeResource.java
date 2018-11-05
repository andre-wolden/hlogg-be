package com.hlogg.hloggbe;

import org.springframework.web.bind.annotation.*;

@RestController
public class TimeResource {

    @CrossOrigin
    @RequestMapping(value = "/now", method = RequestMethod.GET)
    public TimeThing getNow(){
        return new TimeThing();
    }
}
