package me.shaivil.passwordgenapi.controllers;

import me.shaivil.passwordgenapi.services.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class GeneratorController {

    @Autowired
    private PasswordService passwordService;

    @GetMapping("/generatePassword")
    public ResponseEntity genPassword(@RequestParam("length") int length, @RequestParam("includeUpper") boolean includeUpper, @RequestParam("useNumbers") boolean useNumbers, @RequestParam("useSpecial") boolean useSpecial ){
        String password = passwordService.generatePassword(length, includeUpper, useNumbers, useSpecial);

        if (password != null){
            return ResponseEntity.ok(password);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please make sure you have all the params");
    }
}
