package org.example;
import com.taskadapter.redmineapi.IssueManager;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;

//import com.taskadapter.redmineapi.bean.IssueFactory;
import com.taskadapter.redmineapi.bean.IssueCategory;
import com.taskadapter.redmineapi.bean.*;
import com.taskadapter.redmineapi.bean.Tracker;
import com.taskadapter.redmineapi.bean.User;
import com.taskadapter.redmineapi.bean.Version;
//import com.taskadapter.redmineapi.bean.UserFactory;
import com.taskadapter.redmineapi.internal.Transport;

import java.util.Date;

public class RedmineTicketCreator {

        public static Issue addSubTicket(Transport transport,
                                         Issue parentIssue,
                                    int project_id,
                                   String subject,
                                   String description,
                                   int status,
                                   Date start_d,
                                   Date end_d,
                                   int assigneeId){


            Issue childIssue = new Issue();
            childIssue.setTransport(transport);
            childIssue.setProjectId(project_id);

            childIssue.setSubject(subject);
            childIssue.setDescription(description);
            childIssue.setStatusId(status); // In Progress
            childIssue.setStartDate(start_d); // Replace with the Monday date
            childIssue.setDueDate(end_d); // +5 days from start

//                parentIssue.addCategory("2023/WW"); // Add a category or tag
            childIssue.setAssigneeId(assigneeId);
            childIssue.setParentId(parentIssue.getId());
//            System.out.println(parentIssue.getId());
            return childIssue;


        }

        public static Issue getBasicIssue(Transport transport,
                                  int project_id,
                                  String subject,
                                  String description,
                                  int status,
                                  Date start_d,
                                  Date end_d,
                                  int assigneeId){

//            RedmineManager redmineManager = RedmineManagerFactory.createWithApiKey(URL, apiKey);


            // Create the parent issue (the Redmine ticket)
            Issue parentIssue = new Issue();
            parentIssue.setProjectId(project_id);
            parentIssue.setTransport(transport);
            parentIssue.setSubject(subject);
            parentIssue.setDescription(description);
            parentIssue.setStatusId(status); // In Progress
            parentIssue.setStartDate(start_d); // Replace with the Monday date
            parentIssue.setDueDate(end_d); // +5 days from start

//                parentIssue.addCategory("2023/WW"); // Add a category or tag
            parentIssue.setAssigneeId(assigneeId);

//                Issue createdParentIssue = redmineManager.getIssueManager().createIssue(parentIssue);


            //            // Create a sub-task
            //            Issue subTask = IssueFactory.create(projectId, "Sub-Task Name");
            //            subTask.setDescription("Sub-Task Description");
            //            subTask.setStatusId(2); // In Progress
            //            parentIssue.setStartDate(new Date("2023-10-02")); // Replace with the Monday date
            //            parentIssue.setDueDate(new Date("2023-10-06")); // +5 days from start
            //
            ////            subTask.addCategory("2023/WW"); // Add a category or tag
            //            subTask.setAssigneeId(assigneeId);
            //            subTask.setParentId(createdParentIssue.getId());

            //            Issue createdSubTask = redmineManager.createIssue(subTask);

            return parentIssue;
        }
}
