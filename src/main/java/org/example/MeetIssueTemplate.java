package org.example;

import com.taskadapter.redmineapi.bean.Issue;

import java.util.Date;

public class MeetIssueTemplate extends IssueTemplate{
    public MeetIssueTemplate(RedmineContext redmineContext) {
        int week = redmineContext.getWeek();
        Date date = redmineContext.getStart();
        String subject =  String.format("Meeting *** team (Mon /%s Week %n )",date.getHours() + ":" + date.getMinutes() ,week);

        String desc = String.format("*link*");

        setSubject(subject);
        setDescription(desc);
        setStats(redmineContext);
    }


}
