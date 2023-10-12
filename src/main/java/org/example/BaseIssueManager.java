package org.example;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;

import java.util.List;

public class BaseIssueManager {
    private RedmineManager redmineManager;

    public BaseIssueManager(String url, String ApiKey) {
        this.redmineManager = RedmineManagerFactory.createWithApiKey(url, ApiKey);

    }
    public Issue getBaseIssue(Issue issue,int project_id, int assigneeId){


        if (issue.getSubject() == null){
            throw new RuntimeException("given issue not fully constructed");
        }
        issue.setProjectId(project_id);
        issue.setAssigneeId(assigneeId);
        issue.setTransport(redmineManager.getTransport());
        return issue;
    }
    private Issue deployIssue(Issue issue){
        try {
            issue = redmineManager.getIssueManager().createIssue(issue);
        } catch (RedmineException e) {
            throw new RuntimeException(e);
        }
        return issue;
    }
    public void createComposedIssue(Issue parent, List<Issue> kids){
        parent = deployIssue(parent);
        for (Issue child: kids) {
            child.setParentId(parent.getId());
        }
        for (Issue child: kids) {
            deployIssue(child);
        }
    }


}
