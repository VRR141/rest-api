package org.vrr.service.position;

import org.vrr.entity.Employee;
import org.vrr.entity.Position;

import java.util.List;

public interface PositionService {

    List<Position> getAllPositions();

    void savePosition(Position position);

    Position getPosition(int id);

    void removePosition(int id);

    List<Employee> getEmployees(int positionId);
}
