package com.hlogg.hloggbe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class TestResource {

    @RequestMapping("/")
    public String hello(){
        return "hlogg-be :)";
    }

    @RequestMapping("/warn")
    public String canYouSeeYourInputMessageInTheLog(@RequestParam String msg){
        Logger bladeLogger = Logger.getLogger("BladeLogger");
        bladeLogger.log(Level.WARNING, msg);

        return msg;
    }

    @RequestMapping("/save-string-to-psql")
    public String saveStringToPsql(@RequestParam String string){

        return string;
    }

    @RequestMapping("/loff")
    public String getAllLoffBlades(){

        String s = LoffBladeRepo.readBlades();

        return s;
    }

    @RequestMapping("/loffFirst")
    public String getFirst(){

        LoffBladeRepo repo = new LoffBladeRepo();
        LoffBlade loffBlade = repo.getFirst();

        return loffBlade.toString();
    }
}
