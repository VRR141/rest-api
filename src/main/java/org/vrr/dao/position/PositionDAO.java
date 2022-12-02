package org.vrr.dao.position;

import org.vrr.entity.Employee;
import org.vrr.entity.Position;

import java.util.List;

public interface PositionDAO {

    List<Position> getAllPositions();

    void savePosition(Position position);

    Position getPosition(int id);

    void removePosition(int id);

    List<Employee> getEmployees(int positionId);

    void setPositionToEmployee(int positionId, int employeeId);

}
