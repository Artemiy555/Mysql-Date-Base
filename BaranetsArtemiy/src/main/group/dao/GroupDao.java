package group.dao;

import group.entily.Group;

import java.util.List;

public interface GroupDao {
    Long create(Group group);

    Group read(Long id);

    boolean update(Group group);

    boolean delete(Long id);

    List<Group> findAll();

}
