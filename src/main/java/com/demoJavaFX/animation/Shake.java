package com.demoJavaFX.animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

//этот класс нужен для анимации кнопки входа в главном окне входа
public class Shake {

    private TranslateTransition transition;

    public Shake(Node node) {  //node это объкут который будем анимировать
        transition = new TranslateTransition(Duration.millis(100), node);
        transition.setFromX(0f);
        transition.setByX(10f);
        transition.setCycleCount(4);
        transition.setAutoReverse(true);
        play();
    }

    public void play() {
        transition.playFromStart();
    }

}
