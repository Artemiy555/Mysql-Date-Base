package group;

import group.entily.Course;
import group.service.CourseService;
import group.service.GroupService;
import group.service.impl.CourseServiceImpl;
import hibernate.Util.HibernateUtil;

public class Test {


    public static void main(String[] args) {

        CourseService courseService = new CourseServiceImpl();
        //GroupService groupService = new GroupServiceImpl();

        Course course1 = courseService
                .create("Course 1", "Description 1");
        Course course2 = courseService
                .create("Course 2", "Description 2");

        HibernateUtil.getFactory().close();
    }


}
