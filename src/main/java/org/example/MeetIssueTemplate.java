package org.example;

import com.taskadapter.redmineapi.bean.Issue;

import java.util.Date;

public class MeetIssueTemplate extends IssueTemplate{
    public MeetIssueTemplate(String week, Date date) {
        String subject =  String.format("Meeting *** team (Mon /%s Week %s )",date.getHours() + ":" + date.getMinutes() ,week);

        String desc = String.format("*link*");


        setSubject(subject);
        setDescription(desc);
        setStatusId(1);

        setStartDate(date);
    }


}
