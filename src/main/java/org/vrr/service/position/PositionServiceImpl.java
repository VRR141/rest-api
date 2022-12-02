package org.vrr.service.position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vrr.dao.position.PositionDAO;
import org.vrr.entity.Employee;
import org.vrr.entity.Position;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    private PositionDAO positionDAO;

    @Autowired
    public void setPositionDAO(PositionDAO positionDAO) {
        this.positionDAO = positionDAO;
    }

    @Transactional
    @Override
    public List<Position> getAllPositions() {
        List<Position> result = positionDAO.getAllPositions();
        return result;
    }

    @Transactional
    @Override
    public void savePosition(Position position) {
        positionDAO.savePosition(position);
    }

    @Transactional
    @Override
    public Position getPosition(int id) {
        Position position = positionDAO.getPosition(id);
        return position;
    }

    @Transactional
    @Override
    public void removePosition(int id) {
        positionDAO.removePosition(id);
    }

    @Transactional
    @Override
    public List<Employee> getEmployees(int positionId) {
        List<Employee> result = positionDAO.getEmployees(positionId);
        return result;
    }

    @Transactional
    @Override
    public void setPositionToEmployee(int positionId, int employeeId) {
        positionDAO.setPositionToEmployee(positionId, employeeId);
    }
}
