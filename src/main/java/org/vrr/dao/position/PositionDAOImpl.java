package org.vrr.dao.position;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vrr.entity.Customer;
import org.vrr.entity.Employee;
import org.vrr.entity.Position;

import java.util.List;

@Repository
public class PositionDAOImpl implements PositionDAO{

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Position> getAllPositions() {
        Session session = sessionFactory.getCurrentSession();
        Query<Position> query = session.createQuery("from Position", Position.class);
        List<Position> positions = query.getResultList();
        return positions;
    }

    @Override
    public void savePosition(Position position) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(position);
    }

    @Override
    public Position getPosition(int id) {
        Session session = sessionFactory.getCurrentSession();
        Position position = session.get(Position.class, id);
        return position;
    }

    @Override
    public void removePosition(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Position> query = session.createQuery("delete from Position where id =: positionId");
        query.setParameter("positionId", id);
        query.executeUpdate();
    }

    public List<Employee> getEmployees(int positionId){
        Session session = sessionFactory.getCurrentSession();
        Query<Employee> query = session.createQuery(
                "select elements(employees) from Position where id =: id");
        query.setParameter("id", positionId);
        List<Employee> result = query.getResultList();
        return result;
    }

    @Override
    public void setPositionToEmployee(int positionId, int employeeId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Position> query = session.createQuery("update Employee set position.id =: positionid where id =: employeeId");
        query.setParameter("positionid", positionId);
        query.setParameter("employeeId", employeeId);
        query.executeUpdate();
    }
}
