package com.iceman.formula_one.model.track.utility;

import com.iceman.formula_one.model.Direction;
import com.iceman.formula_one.model.Type;
import com.iceman.formula_one.model.car.Car;
import com.iceman.formula_one.model.coordinates.Coordinates;
import com.iceman.formula_one.model.car.Renault_RS_19;
import com.iceman.formula_one.model.car.Ferrari_SF_90;
import com.iceman.formula_one.model.car.Williams_FW_42;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Set;

/**
 * Utility class for RaceTrack. Helps to fill the starting line.
 */
@UtilityClass
public class RaceTrackUtility {

    private static final Coordinates POLE_POSITION = new Coordinates(1, 1);
    private static final Coordinates SECOND_POSITION = new Coordinates(1, 2);
    private static final Coordinates THIRD_POSITION = new Coordinates(1, 3);

    public static Set<Car> getStartingLine(){
        Set<Car> cars = new HashSet<>();

        Ferrari_SF_90 ferrariSF90 = new Ferrari_SF_90();
        ferrariSF90.setType(Type.RACING_CAR);
        ferrariSF90.setCoordinates(POLE_POSITION);
        ferrariSF90.setDirection(Direction.LEFT);
        cars.add(ferrariSF90);

        Renault_RS_19 renaultRS19 = new Renault_RS_19();
        renaultRS19.setType(Type.CAR);
        renaultRS19.setCoordinates(SECOND_POSITION);
        renaultRS19.setDirection(Direction.BACKWARD);
        cars.add(renaultRS19);

        Williams_FW_42 williamsFW42 = new Williams_FW_42();
        williamsFW42.setType(Type.TRACTOR);
        williamsFW42.setCoordinates(THIRD_POSITION);
        williamsFW42.setDirection(Direction.RIGHT);
        cars.add(williamsFW42);

        return cars;
    }

}
