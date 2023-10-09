package org.example;

import com.taskadapter.redmineapi.IssueManager;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;

import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.internal.Transport;

import java.util.Collections;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        String redmineURL = "http://192.168.1.70";
        String apiAccessKey = "a4e5b3aa181cc6b74f6a67e1919e74d393cead28";

        int project_id = 1;

        String subject = "Administrative Tasks (Week XX)";

        String description = """
                Administrative Tasks (Week 39)
                                
                    sync up meetings with the Rostov Team Lead developers (Mon / 12-00)
                    sync up meetings with the Rostov Group Managers (Mon / 13-00)
                    sync up meetings with the GroupDocs Java (Mon / 13-00)
                    weekly progress review of each team developer
                    weekly reports review
                    monitoring the blog posts
                """;
        int status = 1;

        Date start = new Date(2023,10,12);
        Date end = new Date(2023,10,17);

        int assigneeId = 1;

        RedmineManager redmineManager = RedmineManagerFactory.createWithApiKey(redmineURL,apiAccessKey);
        RedmineTicketCreator rtc = new RedmineTicketCreator();

        Transport transport = redmineManager.getTransport();


        Issue issue = RedmineTicketCreator.getBasicIssue(transport,
                project_id,
                subject,
                description,
                status,
                start,
                end,
                assigneeId
                );


        try {
            Issue createdParentIssue = redmineManager.getIssueManager().createIssue(issue);


            Issue child = RedmineTicketCreator.addSubTicket(transport,
                    createdParentIssue,
                    project_id,
                    "sub_task",
                    "subtask_discription\n blabla bla",
                    status,
                    start,
                    end,
                    assigneeId);



            redmineManager.getIssueManager().createIssue(child);

        } catch (RedmineException e) {
            throw new RuntimeException(e);
        }

    }
}