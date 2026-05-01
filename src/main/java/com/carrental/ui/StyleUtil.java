package com.carrental.ui;

import javafx.scene.Scene;

public class StyleUtil {

    public static void applyStyle(Scene scene) {
        try {
            scene.getStylesheets().add(
                    StyleUtil.class.getResource("/style.css").toExternalForm()
            );
        } catch (Exception e) {
            System.out.println("style.css not found");
        }
    }
}