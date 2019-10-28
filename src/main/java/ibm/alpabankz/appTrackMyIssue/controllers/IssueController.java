package ibm.alpabankz.appTrackMyIssue.controllers;

import ibm.alpabankz.appTrackMyIssue.dao.IssueDao;
import ibm.alpabankz.appTrackMyIssue.dao.UserDao;
import ibm.alpabankz.appTrackMyIssue.models.Issue;
import ibm.alpabankz.appTrackMyIssue.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/issues")
public class IssueController {
    @Autowired
    IssueDao issueDao;

    @Autowired
    UserDao userDao;

    @Autowired
    EntityManager entityManager;

    @GetMapping("/allIssues")
    public String getAllIssues(Model model) {
        List<Issue> issues = (List<Issue>) issueDao.findAll();
        model.addAttribute("allIssues", issues);
        return "issues";
    }

    @GetMapping("/createIssue")
    public String createIssue(Model model) {
        Issue issue = new Issue();
        model.addAttribute("issueDummie", issue);
        return "issueCreation";
    }

    @GetMapping("/editIssue/{id}")
    public String editIssue(@PathVariable("id") int id, Model model) {
        Optional<Issue> issue = issueDao.findById(id);
        Issue edited = issue.get();
        model.addAttribute("toBeEdited", edited);
        List<User> users = (List<User>) userDao.findAll();
        model.addAttribute("Users", users);
        model.addAttribute("Assignees", users);
        return "issueEdition";
    }

    @PostMapping("/edited")
    public String edited(@ModelAttribute("toBeEdited") Issue issue, Model model, HttpSession httpSession) {
        User creator = issue.getCreator();
        User editor = issue.getLastEditor();
        User assignee = issue.getAssignedUser();
        Date creationDate = issue.getCreationDate();
        Date editedDate = new Date();
        issue.setIssueEditDate(editedDate);
        boolean validCreator = false;
        boolean validEditor = false;
        boolean validAssigne = false;
        boolean validDate = false;
        User u2 = (userDao.findById(creator.getId())).get();
        if (u2 != null) {
            validCreator = true;
        }
        if (assignee != null) {
            User assigneeChecker = (userDao.findById(assignee.getId())).get();
            if (assigneeChecker != null) {
                validAssigne = true;
            }
        } else {
            validAssigne = true;
        }
        User editorChecker = userDao.findById(((((User) httpSession.getAttribute("logged"))).getId())).get();
        if (editorChecker != null) {
            User editorChecker1 = userDao.findById(editorChecker.getId()).get();
            if (editorChecker1 != null) {
                validEditor = true;
            } else {
                validEditor = true;
            }
        }
        if ((validAssigne && validCreator) && validEditor) {
            issue.setLastEditor(((User) httpSession.getAttribute("logged")));
            Issue saved = issueDao.save(issue);
        }

        return "redirect:/issues/allIssues";
    }

    @PostMapping("/issueCreationHandler")
    public String createIssue(@ModelAttribute("issueDummie") Issue issue, HttpSession httpSession) {
        issue.setCreator((User) httpSession.getAttribute("logged"));
        issue.setCreationDate(new Date());
        issueDao.save(issue);
        return "redirect:/issues/allIssues";
    }

    @GetMapping("/deleteIssue/{id}")
    public String deleteIssue(@PathVariable("id") int id,@ModelAttribute("issue") Issue issue, Model model) {
        Boolean deletedChecker = false;

        issueDao.findById(id);
        Issue toBeDeleted = issueDao.findById(id).get();
        if (toBeDeleted != null) {
            List<Issue> created = issueDao.findByCreator(toBeDeleted.getCreator());
            issueDao.delete(toBeDeleted);
            issueDao.deleteById(id);
        }
        return "redirect:/issues/allIssues";
    }
}
