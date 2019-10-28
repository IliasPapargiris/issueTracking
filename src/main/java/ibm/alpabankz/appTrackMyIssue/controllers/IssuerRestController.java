package ibm.alpabankz.appTrackMyIssue.controllers;

import ibm.alpabankz.appTrackMyIssue.dao.IssueDao;
import ibm.alpabankz.appTrackMyIssue.dao.UserDao;
import ibm.alpabankz.appTrackMyIssue.models.Issue;
import ibm.alpabankz.appTrackMyIssue.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class IssuerRestController {
    @Autowired
    IssueDao issueDao;
    @Autowired
    UserDao userDao;
    @PostMapping("/saveIssue")
    public Issue saveIssue(){
        Issue issue =new Issue();
        issue.setStatus("IN_PROGRESS");
        issue.setCreationDate(new Date());
        User john = new User();
        john.setName("John");
       // john.setId(7);
        userDao.save(john);
        List<Issue> jIssues=new ArrayList<Issue>();
        jIssues.add(issue);
        //issue.setId(5);
        john.setIssuesCreated(jIssues);
        issue.setCreator(john);
        issueDao.save(issue);
        return issue;
    }
    @PostMapping("/saveJohn")
    public User saveUser(){
        User john = new User();
        john.setId(2);
        Issue issue =new Issue();
        issue.setStatus("IN_PROGRESS");
        issue.setCreationDate(new Date());
        john.setName("John");
        List<Issue> jIssues=new ArrayList<Issue>();
        jIssues.add(issue);
        issue.setId(2);
        john.setIssuesCreated(jIssues);
        issue.setCreator(john);
        userDao.save(john);
        return john;
    }
    @GetMapping("/getAll")
    public Iterable<Issue> getAllIssues(){
        return issueDao.findAll();
    }

//    @PostMapping("/editIssue")
//    public String editIssue(){
//
//    }
}
