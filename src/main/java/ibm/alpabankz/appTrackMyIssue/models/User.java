package ibm.alpabankz.appTrackMyIssue.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity(name="USER_DB")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String password;

    @OneToMany(targetEntity = Issue.class,cascade = CascadeType.ALL ,fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonBackReference
    private List<Issue> issuesCreated;

    @OneToMany(targetEntity = Issue.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonBackReference
    private List<Issue> issuesEdited;


    @OneToMany(targetEntity = Issue.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonBackReference
    private List<Issue> issuesAssigned;

    public List<Issue> getIssuesEdited() {
        return issuesEdited;
    }

    public void setIssuesEdited(List<Issue> issuesEdited) {
        this.issuesEdited = issuesEdited;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Issue> getIssuesCreated() {
        return issuesCreated;
    }

    public void setIssuesCreated(List<Issue> issuesCreated) {
        this.issuesCreated = issuesCreated;
    }


    public String getPassword() {
        return password;
    }

    public List<Issue> getIssuesAssigned() {
        return issuesAssigned;
    }

    public void setIssuesAssigned(List<Issue> issuesAssigned) {
        this.issuesAssigned = issuesAssigned;
    }

    @Override
    public String toString() {
        return "User name:"+ name +",id:"+id ;
    }
}


