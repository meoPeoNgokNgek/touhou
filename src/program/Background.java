package program;

import program.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.*;

public class Background extends GameObject {
    public Background() {
//        image = SpriteUtils.loadImage("assets/images/background/0.png");
        renderer = new SingleImageRenderer("assets/images/background/0.png");
        position.set(0, Settings.GAME_HEIGHT - Settings.BACKGROUND_HEIGHT);
        velocity.set(0, Settings.BACKGROUND_SPEED);
        anchor.set(0, 0);
    }

    @Override
    public void run() {
        super.run();
        if(position.y >= 0) {
            position.setY(0);
        }
    }
}
