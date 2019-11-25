package com.iceman.formula_one.web.controller;


import com.iceman.formula_one.FormulaOneApplication;
import com.iceman.formula_one.model.Direction;
import com.iceman.formula_one.model.Type;
import com.iceman.formula_one.model.track.RaceTrack;
import com.iceman.formula_one.model.coordinates.Coordinates;
import com.iceman.formula_one.model.car.Williams_FW_42;
import com.iceman.formula_one.model.web.request.Action;
import com.iceman.formula_one.model.web.request.ActionRequest;
import com.iceman.formula_one.model.web.response.ActionResponse;
import com.iceman.formula_one.model.web.response.Status;
import com.iceman.formula_one.service.game.impl.RaceTrackServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

/**
 * A class that tests web part of application
 */
@SpringBootTest
public class RaceTrackControllerTest {


    public static final int DEFAULT_X_DIMENSION = 6;
    public static final int DEFAULT_Y_DIMENSION = 5;

    @BeforeAll
    public static void startServer(){
        FormulaOneApplication.main(new String[0]);
    }

    @Test
    public void stompConnectionTest() throws ExecutionException, InterruptedException {

        SockJsClient sockJsClient = new SockJsClient(Collections.singletonList
                                        (new WebSocketTransport(new StandardWebSocketClient())));
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        ListenableFuture<StompSession> future = stompClient.connect
                                        ("ws://localhost:8080/game", new StompSessionHandlerAdapter(){});
        StompSession stompSession = future.get();
        Assumptions.assumeTrue(stompSession.isConnected());
    }

    @Test
    public void responseStatusOnWrongMoveRequestIsErrorTest(){
        RaceTrackServiceImpl raceTrackService =  new RaceTrackServiceImpl();
        raceTrackService.setDimensionXDefault(DEFAULT_X_DIMENSION);
        raceTrackService.setDimensionYDefault(DEFAULT_Y_DIMENSION);

        RaceTrack raceTrack = new RaceTrack(DEFAULT_X_DIMENSION, DEFAULT_Y_DIMENSION);
        Williams_FW_42 williamsFW42 = new Williams_FW_42(Type.TRACTOR, null, new Coordinates(0, 0), Direction.LEFT);
        String tractorUuid = williamsFW42.getUuid();
        raceTrack.getCars().add(williamsFW42);
        raceTrackService.setRaceTrack(raceTrack);
        RaceTrackController controller = new RaceTrackController(raceTrackService);
        ActionResponse response = controller.processMessage(new ActionRequest(Action.DRIVE, tractorUuid));
        Assertions.assertEquals(response.getStatus(), Status.ERROR);
    }

    @Test
    public void responseStatusOnCorrectMoveRequestIsSuccessTest(){
        RaceTrackServiceImpl raceTrackService =  new RaceTrackServiceImpl();
        raceTrackService.setDimensionXDefault(DEFAULT_X_DIMENSION);
        raceTrackService.setDimensionYDefault(5);

        RaceTrack raceTrack = new RaceTrack(DEFAULT_X_DIMENSION, DEFAULT_Y_DIMENSION);
        Williams_FW_42 williamsFW42 = new Williams_FW_42(Type.TRACTOR, null, new Coordinates(0, 0), Direction.RIGHT);
        String tractorUuid = williamsFW42.getUuid();
        raceTrack.getCars().add(williamsFW42);
        raceTrackService.setRaceTrack(raceTrack);
        RaceTrackController controller = new RaceTrackController(raceTrackService);
        ActionResponse response = controller.processMessage(new ActionRequest(Action.DRIVE, tractorUuid));
        Assertions.assertEquals(response.getStatus(), Status.SUCCESS);
    }

}
