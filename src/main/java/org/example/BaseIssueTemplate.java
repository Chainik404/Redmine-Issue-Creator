package org.example;

import com.taskadapter.redmineapi.bean.Issue;

import java.util.Date;

public class BaseIssueTemplate extends IssueTemplate{


    public BaseIssueTemplate(RedmineContext redmineContext) {
        int week  = redmineContext.getWeek();
        String subject =  String.format("Administrative Tasks (Week %n)",week);

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
        setStats(redmineContext);
    }

}
