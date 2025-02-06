package com.compasible.Examples.uis;

import com.compasible.GamePanel;
import com.compasible.ui.Ui;
import com.compasible.ui.UiBox;
import com.compasible.ui.UiText;

import java.awt.*;

public class ExampleUi extends Ui {

    public ExampleUi(GamePanel gamePanel) {
        super(gamePanel);

        UiBox exampleBox = new UiBox();
        exampleBox.setValues(0, 0, 200, 200, Color.white);
        addUiElement(exampleBox);

        UiText exampleText = new UiText();
        exampleText.setValues(100, 30, "Example", Color.blue, new Font("TimesRoman", Font.BOLD, 25));
        addUiElement(exampleText);
    }

    @Override
    public void update() {
        super.update();
    }
}