package kz.narxoz.sabak.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String Greeting(@RequestParam(name="name", required=false, defaultValue="World")  String name,Model model, @RequestParam String surname,@RequestParam String lastname) {
        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        model.addAttribute("lastname", lastname);
        return "Greeting";
    }

} 