package group.service;


import group.entily.Group;
import group.entily.Course;

import java.util.Date;

public interface GroupService {

    Group create(
            Course course, String title, Date begin, Date end);
}
