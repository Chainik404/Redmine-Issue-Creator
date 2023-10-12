package org.example;

import com.taskadapter.redmineapi.bean.Issue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String redmineURL = "http://192.168.1.14";
        String apiAccessKey = "50a549c11f339eade87791e26babfc4ce123d985";

        int project_id =1;

        int assigneeId = 1;
        Date today = new Date(123, Calendar.OCTOBER, 11, 15,45);

        List<Issue> kids = new ArrayList<>();


        Issue a1 = new BaseIssueTemplate("39", today).createIssue();

//        today.setHours(15);
//        today.setMinutes(45);
        Issue m1 = new MeetIssueTemplate("39",today).createIssue();
        BaseIssueManager bim = new BaseIssueManager(redmineURL, apiAccessKey);


        Issue parent = bim.getBaseIssue(a1,project_id,assigneeId);
        Issue child =  bim.getBaseIssue(m1,project_id,assigneeId);

        kids.add(child);
        bim.createComposedIssue(parent,kids);





    }
}