package ibm.alpabankz.appTrackMyIssue.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "ISSUE_DB")
@Table(name = "issue_db")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date issueEditDate;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JsonManagedReference
    private User lastEditor;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JsonManagedReference
    private User creator;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JsonManagedReference
    private User assignedUser;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getIssueEditDate() {
        return issueEditDate;
    }

    public void setIssueEditDate(Date issueEditDate) {
        this.issueEditDate = issueEditDate;
    }

    public User getLastEditor() {
        return lastEditor;
    }

    public void setLastEditor(User lastEditor) {
        this.lastEditor = lastEditor;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getStatus() {
        return status;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public void setStatus(String status) {
        try {
            status = StatusOptions.valueOf(status).toString();
            this.status = status;
        } catch (IllegalArgumentException ex) {
        }
    }
}