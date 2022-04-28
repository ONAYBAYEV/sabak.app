package kz.narxoz.sabak.app.controller;


import kz.narxoz.sabak.app.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import kz.narxoz.sabak.app.repository.UserRepository;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    UserRepository repository;

    @GetMapping("/")
    public String showUserList(@RequestParam(name="email", required=false, defaultValue="") String email,
                               @RequestParam(name="email1", required=false, defaultValue="") String email1,

                               @RequestParam(name="name", required=false, defaultValue="") String name,
                               @RequestParam(name="name1", required=false, defaultValue="") String name1,
                               @RequestParam(name="id", required=true, defaultValue="") Long id,
                               @RequestParam(name = "surname", required = false, defaultValue = "") String surname,

                               Model model) {
        List<User> users = repository.findAll();
        // 1 esep
        if(!email.isEmpty()){
            users=repository.findByEmailContainingOrderByNameDesc(email);
        }
        // 9 esep
        if(!email1.isEmpty()){
            users=repository.emailLike();
        }
        // 2 esep
        if(!name.isEmpty()){
            users=repository.findTop2ByNameStartingWith(name);
        }

        // 3 esep
        if(!surname.isEmpty()){
            users = repository.findBySurnameContaining(surname);}

        // 4 esep
        if(id!=null){
            users=repository.findByIdOrderById(id);
        }

        // 8 esep
        if(!name1.isEmpty()){
            users=repository.EqualNameSurname(name1);
        }

        model.addAttribute("users", users);
        return "index";
    }
    // 5 esep ----
    @GetMapping("/5")
    public String showUserList(@RequestParam(name = "id", required = false, defaultValue = "") Long id,
                               Model model){
        List<User> users = repository.findshowlastUsers();
        model.addAttribute("users", users);
        return "index";
    }
    // 6 esep ---------
    @GetMapping("/sort")
    public String sortedByname(@RequestParam(name = "name", required = false, defaultValue = "") String name,
                               Model model){
        List<User> users = repository.sortByName();
        model.addAttribute("users", users);
        return "index";
    }

    // 7 esepp ------
    @GetMapping("/not")
    public String email(@RequestParam(name = "email2", required = false, defaultValue = "") String email2,
                        Model model){
        List<User> users = repository.findAll();

        users = repository.findByEmailNotContaining(email2);
        model.addAttribute("users", users);
        return "index";
    }

    //10 esep ----------
    @GetMapping("/distinct")
    public String distinct(@RequestParam(name = "name", required = false, defaultValue = "") String name,
                           Model model){
        List<User> users = repository.findAll();
        users = repository.findDistinctByName();
        model.addAttribute("users", users);
        return "index";
    }

    @PostMapping("/adduser")
    public String createUser(@ModelAttribute User user){
        addUser(user);
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, User user) {
        updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = repository.getById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    private void deleteById(long id) {
        repository.deleteById(id);
    }

    private void addUser(User newUser) {
        repository.save(newUser);
    }

    private void updateUser(User updateUser) {
        User oldUser = repository.getById(updateUser.getId());

        oldUser.setName(updateUser.getName());
        oldUser.setSurname(updateUser.getSurname());
        oldUser.setEmail(updateUser.getEmail());

        repository.save(oldUser);
    }
}