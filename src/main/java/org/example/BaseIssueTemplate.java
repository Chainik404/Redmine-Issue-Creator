package org.example;

import com.taskadapter.redmineapi.bean.Issue;

import java.util.Date;

public class BaseIssueTemplate extends Issue{


    public BaseIssueTemplate(String week, Date start) {
        String subject =  String.format("Administrative Tasks (Week %s)",week);

        String desc = String.format("""
                Administrative Tasks (Week %s)
                                
                    sync up meetings with the Rostov Team Lead developers (Mon / 12-00)
                    sync up meetings with the Rostov Group Managers (Mon / 13-00)
                    sync up meetings with the GroupDocs Java (Mon / 13-00)
                    weekly progress review of each team developer
                    weekly reports review
                    monitoring the blog posts
                """,week);

        setSubject(subject);
        setDescription(desc);
        setStatusId(1);
        setStartDate(start);
        setDueDate(new Date(start.getYear(), start.getMonth(), start.getDate()+5));
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
