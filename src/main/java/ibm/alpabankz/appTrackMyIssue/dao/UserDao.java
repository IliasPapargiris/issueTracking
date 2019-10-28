package ibm.alpabankz.appTrackMyIssue.dao;

import ibm.alpabankz.appTrackMyIssue.models.Issue;
import ibm.alpabankz.appTrackMyIssue.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserDao extends CrudRepository<User, Integer> {
    User findByName(String name);
    List<User> findByPassword(String password);
    //User findbyIssuesCreated(Issue created);
}
