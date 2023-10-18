package org.example;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.CustomFieldDefinition;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.IssueCategory;
import com.taskadapter.redmineapi.bean.Project;

import java.util.Arrays;
import java.util.List;

public class RedmineManagerOperator {
    String redmineURL;
    String apiAccessKey;

    RedmineManager redmineManager;



    public RedmineManagerOperator(String redmineURL, String apiAccessKey) {
        this.redmineURL = redmineURL;
        this.apiAccessKey = apiAccessKey;
        this.redmineManager = RedmineManagerFactory.createWithApiKey(redmineURL, apiAccessKey);
//        redmineManager.getUserManager().
    }


    public Issue getBaseIssue(Issue issue, int project_id, int assigneeId){


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

    public void getAllProjects(){
        List<Project> projects;

        try {
            projects = redmineManager.getProjectManager().getProjects();
        } catch (RedmineException e) {
            throw new RuntimeException(e);
        }
        for (Project p: projects) {

            System.out.println(String.format("name: %s | id: %s",p.getName(), p.getId()));
        }
    }
    public void getAllCategories(){
        List<IssueCategory> ic;

        try {
            ic = redmineManager.getIssueManager().getCategories(1);
        } catch (RedmineException e) {
            throw new RuntimeException(e);
        }
        for (IssueCategory c: ic) {

            System.out.println(String.format("name: %s | id: %s",c.getName(), c.getId()));
        }
    }

    public void getAllCustomFields(){
        List<CustomFieldDefinition> cfd;

        try {
            cfd = redmineManager.getCustomFieldManager().getCustomFieldDefinitions();
        } catch (RedmineException e) {
            throw new RuntimeException(e);
        }
        for (CustomFieldDefinition c: cfd) {

            System.out.println(String.format("name: %s | id: %s",c.getName(), c.getId()));
            System.out.println(Arrays.toString(c.getPossibleValues().toArray()));
            System.out.println("==================================================");
        }
    }

    public void getCustomFieldByName(String fieldName){
        List<CustomFieldDefinition> cfd;
        try {
            cfd = redmineManager.getCustomFieldManager().getCustomFieldDefinitions();
        } catch (RedmineException e) {
            throw new RuntimeException(e);
        }

        for (CustomFieldDefinition customFieldDefinition : cfd) {
            if (customFieldDefinition.getName().equals(fieldName)) {
                System.out.println(customFieldDefinition.getId());
            }
        }
        throw new RuntimeException("Custom Field definition '" + fieldName + "' is not found on server.");
    }
}
