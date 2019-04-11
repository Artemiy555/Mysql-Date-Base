package group.entily;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "GROUPS")
public class Group {

    @Id
    @Column(name = "GROUP_ID")
    @SequenceGenerator(
            name = "groupId",
            sequenceName = "SEQ_GROUP_ID",
            initialValue = 2018_00000,
            allocationSize = 25
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "groupId"
    )
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER,
            targetEntity = Course.class
    )
    @JoinColumn(
            name = "COURSE_ID",
            nullable = false
    )
    private Course courseId;

    @Column
    private String title;

    @Temporal(TemporalType.DATE)
    private Date begin;

    @Temporal(TemporalType.DATE)
    private Date end;

    public Group() {}
    public Group(Course courseId, String title, Date begin, Date end) {
        this.courseId = courseId;
        this.title = title;
        this.begin = begin;
        this.end = end;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Course getCourseId() {
        return courseId;
    }
    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getBegin() {
        return begin;
    }
    public void setBegin(Date begin) {
        this.begin = begin;
    }
    public Date getEnd() {
        return end;
    }
    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", title='" + title + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                '}';
    }
}
