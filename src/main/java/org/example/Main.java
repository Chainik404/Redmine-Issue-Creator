package org.example;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String redmineURL = "http://192.168.1.14";
        String apiAccessKey = "50a549c11f339eade87791e26babfc4ce123d985";

        String week_num = "39";

        int project_id =1;

        int assignee_id = 1;

        Date today = new Date(123, Calendar.OCTOBER, 11, 15,45);

        List<Issue> kids = new ArrayList<>();




        Issue a1 = new BaseIssueTemplate(week_num, today).createIssue();

        Issue m1 = new MeetIssueTemplate(week_num,today).createIssue();
        BaseIssueManager bim = new BaseIssueManager(redmineURL, apiAccessKey);


        Issue parent = bim.getBaseIssue(a1,project_id,assignee_id);
        Issue child =  bim.getBaseIssue(m1,project_id,assignee_id);

        kids.add(child);
//        bim.createComposedIssue(parent,kids);
        getAllProjects(redmineURL,apiAccessKey);




    }


    public static void getAllProjects(String redmineURL, String apiAccessKey){
        RedmineManager redmineManager = RedmineManagerFactory.createWithApiKey(redmineURL, apiAccessKey);
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
}