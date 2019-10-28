package ibm.alpabankz.appTrackMyIssue.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import ibm.alpabankz.appTrackMyIssue.dao.UserDao;
import ibm.alpabankz.appTrackMyIssue.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("title", "Getting started viewing shit");
        return "index";
    }

    @GetMapping("/goToLogin")
    public String emptyLogInForm(Model model) {
        User user = new User();
        model.addAttribute("logInDummie", user);
        return "loginForm";
    }
    @GetMapping("/loggingOut")
    public String emptyLogOutForm(HttpSession session) {
        session.removeAttribute("logged");
        return "redirect:/user/goToLogin";
    }

    @PostMapping("/loginHandler")
    public String logInValidation(@ModelAttribute("logInDummie") User user, HttpSession httpSession) {
        if ((userDao.findByName(user.getName()) == null) || (!((userDao.findByName(user.getName())).getPassword()).equals(user.getPassword()))) {
            return "invalidLogIn";
        } else {
            User logged = userDao.findByName(user.getName());
            httpSession.setAttribute("logged",logged);
            //session.setAttribute("loggedUser",(User)userDao.findByName(user.getName()));
            return "menu";
        }
    }

    @GetMapping("/goToRegistration")
    public String emptyRegisterForm(Model model) {
        User user = new User();
        model.addAttribute("registrationDummie", user);
        return "registrationForm";
    }

    @PostMapping("/registrationHandler")
    public ModelAndView registrationValidation(@ModelAttribute("registrationDummie") User user) {
        if ((userDao.findByName(user.getName()) != null)) {
            ModelAndView model = new ModelAndView("unavailableUserName", "registered", user);
            return model;
        } else {
            userDao.save(user);
            ModelAndView model = new ModelAndView("justRegistered", "registered", user);
            return model;
        }

    }

}
