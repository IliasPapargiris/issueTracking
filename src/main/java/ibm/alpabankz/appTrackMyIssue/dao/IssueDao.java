package ibm.alpabankz.appTrackMyIssue.dao;

import ibm.alpabankz.appTrackMyIssue.models.Issue;
import ibm.alpabankz.appTrackMyIssue.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IssueDao  extends CrudRepository<Issue,Integer> {
    List<Issue> findByCreator(User creator);
   // List<Issue> findByEditor(User editor);
}
