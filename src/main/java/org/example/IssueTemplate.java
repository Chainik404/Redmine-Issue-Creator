package org.example;

import com.taskadapter.redmineapi.bean.Issue;

public abstract class IssueTemplate extends Issue {

    public void setStats(RedmineContext redmineContext){
        setStatusId(redmineContext.getStatusId());
        setStartDate(redmineContext.getStart());
        setDueDate(redmineContext.getEnd());
    }
    public Issue createIssue() {
        Issue issue = new Issue();
        issue.setSubject(getSubject());
        issue.setDescription(getDescription());
        issue.setStatusId(getStatusId());
        issue.setStartDate(getStartDate());
        issue.setDueDate(getDueDate());
        return issue;
    }

}
