package org.vrr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vrr.entity.Employee;
import org.vrr.entity.Position;
import org.vrr.exception_handling.NoSuchEmployeeException;
import org.vrr.service.position.PositionService;

import java.util.List;

@RestController
@Api(tags = {"Positions info"})
public class PositionRestController {

    private PositionService positionService;

    @Autowired
    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }

    @RequestMapping(value = "/positions", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all positions")
    public List<Position> getAllPositions(){
        List<Position> result = positionService.getAllPositions();
        return result;
    }

    @RequestMapping(value = "/positions/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get positions by id")
    public List<Employee> getPosition(@PathVariable int id){
        List<Employee> result = positionService.getEmployees(id);
        if (result == null){
            throw new NoSuchEmployeeException("There is no position with id " + id + " at Database");
        }
        return result;
    }

    @ApiOperation(value = "Add positions")
    @RequestMapping(value = "/positions", method = RequestMethod.POST)
    public ResponseEntity<Position> addPosition(@RequestBody Position position){
        positionService.savePosition(position);
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @ApiOperation(value = "Update positions")
    @RequestMapping(value = "/positions", method = RequestMethod.PUT)
    public ResponseEntity<Position> updatePosition(@RequestBody Position Position){
        positionService.savePosition(Position);
        return new ResponseEntity<>(Position, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete position id")
    @RequestMapping(value = "/positions/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePosistion(@PathVariable int id){
        if (positionService.getPosition(id) == null){
            throw new NoSuchEmployeeException("There is no position with id " + id + " at Database");
        }
        positionService.removePosition(id);
        return new ResponseEntity<>(new String("Position with id " + id + " successfully delete"),HttpStatus.OK);
    }
}
