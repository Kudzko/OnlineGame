package com.iceman.formula_one.service.game.impl;

import com.iceman.formula_one.exception.DriveException;
import com.iceman.formula_one.model.Direction;
import com.iceman.formula_one.model.Type;
import com.iceman.formula_one.model.track.RaceTrack;
import com.iceman.formula_one.model.coordinates.Coordinates;
import com.iceman.formula_one.model.car.Renault_RS_19;
import com.iceman.formula_one.model.car.Ferrari_SF_90;
import com.iceman.formula_one.model.car.Williams_FW_42;
import com.iceman.formula_one.model.car.Car;
import com.iceman.formula_one.model.web.request.Action;
import com.iceman.formula_one.model.web.request.ActionRequest;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class RaceTrackServiceImplTest {

    public static final int DEFAULT_X_DIMENSION = 6;
    public static final int DEFAULT_Y_DIMENSION = 5;
    private static RaceTrackServiceImpl raceTrackService;
    private String tractorUuid;
    private String carUuid;
    private String sportCarUuid;

    @BeforeAll
    public static void serviceInit(){
        raceTrackService =  new RaceTrackServiceImpl();
        raceTrackService.setDimensionXDefault(DEFAULT_X_DIMENSION);
        raceTrackService.setDimensionYDefault(DEFAULT_Y_DIMENSION);
    }


    @BeforeEach
    public void battlefieldInit(){
        RaceTrack raceTrack = new RaceTrack(DEFAULT_X_DIMENSION, DEFAULT_Y_DIMENSION);

        Williams_FW_42 williamsFW42 = new Williams_FW_42(Type.TRACTOR, null, new Coordinates(0, 0), Direction.LEFT);
        tractorUuid = williamsFW42.getUuid();
        raceTrack.getCars().add(williamsFW42);

        Renault_RS_19 renaultRS19 = new Renault_RS_19(Type.CAR, null, new Coordinates(3, 3), Direction.LEFT);
        carUuid = renaultRS19.getUuid();
        raceTrack.getCars().add(renaultRS19);

        Ferrari_SF_90 ferrariSF90 = new Ferrari_SF_90(Type.RACING_CAR, null, new Coordinates(3, 0), Direction.FORWARD);
        sportCarUuid = ferrariSF90.getUuid();
        raceTrack.getCars().add(ferrariSF90);

        raceTrackService.setRaceTrack(raceTrack);
    }


    @Test
    public void moveOnBusyFieldThrowsExceptionTest(){
        Assertions.assertThrows(DriveException.class, ()->{
            ActionRequest actionRequest = new ActionRequest(Action.DRIVE, sportCarUuid);
            raceTrackService.processAction(actionRequest);
        });
    }


    @Test
    public void moveOutsideBattleFieldMoveThrowsExceptionTest(){
        Assertions.assertThrows(DriveException.class, ()->{
            ActionRequest actionRequest = new ActionRequest(Action.DRIVE, tractorUuid);
            raceTrackService.processAction(actionRequest);
        });
    }


    @Test
    public void moveOnFreeFieldDoesNotThrowExceptionTest(){
        Assertions.assertDoesNotThrow(()->{
            ActionRequest actionRequest = new ActionRequest(Action.DRIVE, carUuid);
            raceTrackService.processAction(actionRequest);
        });
    }


    @Test
    public void moveHappensOnExpectedCellTest() throws DriveException {
        ActionRequest actionRequest = new ActionRequest(Action.DRIVE, carUuid);
        Car car = raceTrackService.processAction(actionRequest);
        Assertions.assertEquals(car.getCoordinates(), new Coordinates(1, 3));
    }


    @Test
    public void rotate90DegreesCounterclockwiseTest() throws DriveException {
        ActionRequest actionRequest = new ActionRequest(Action.COUNTERCLOCKWISE_MANEUVER, carUuid);
        Car car = raceTrackService.processAction(actionRequest);
        Assertions.assertEquals(car.getDirection(), Direction.BACKWARD);
    }


    @Test
    public void rotate90DegreesClockwiseTest() throws DriveException {
        ActionRequest actionRequest = new ActionRequest(Action.CLOCKWISE_MANEUVER, carUuid);
        Car car = raceTrackService.processAction(actionRequest);
        Assertions.assertEquals(car.getDirection(), Direction.FORWARD);
    }

}
