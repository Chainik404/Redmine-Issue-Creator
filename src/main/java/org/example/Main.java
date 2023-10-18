package org.example;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.*;
import com.taskadapter.redmineapi.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String redmineURL = "http://192.168.1.14";
        String apiAccessKey = "50a549c11f339eade87791e26babfc4ce123d985";


//        var redmineURL = "https://issue.saltov.dynabic.com";
//        var apiAccessKey = "d70d1ae6424837f9e19bcbe7b7316583e71b2188";

        int week_num = 39;

        int project_id =1;

        int assignee_id = 1;

        int status_id = 1;



        Date today = new Date(123, Calendar.OCTOBER, 11, 15,45);

        RedmineManagerOperator rmo = new RedmineManagerOperator(redmineURL,apiAccessKey);

        RedmineContext redmineContext = new RedmineContext(week_num, status_id, today);


        List<Issue> kids = new ArrayList<>();




        Issue a1 = new BaseIssueTemplate(redmineContext).createIssue();
//
        Issue m1 = new MeetIssueTemplate(redmineContext).createIssue();
//        BaseIssueManager bim = new BaseIssueManager(redmineURL, apiAccessKey);


        Issue parent = rmo.getBaseIssue(a1,project_id,assignee_id);
        Issue child =  rmo.getBaseIssue(m1,project_id,assignee_id);

        kids.add(child);
        rmo.createComposedIssue(parent,kids);




    }




}