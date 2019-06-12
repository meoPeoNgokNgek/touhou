package program;

import program.physics.BoxCollider;
import program.renderer.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject {
    // static ~ quan li doi tuong
    public static ArrayList<GameObject> objects = new ArrayList<>();

    public static <E extends GameObject> E recycle(Class<E> cls) {
        // E ~ Player / Background
        // cls ~ Player.class / Background.class
        // 1. Tim 1 doi tuong da deactive > reset > return
        E object = findDeactive(cls);
        if(object != null) {
            object.reset();
            return object;
        }
        // 2. Neu ko tim duoc > khoi tao > return
        try {
            object = cls.getConstructor().newInstance();
            return object;
        } catch(Exception ex) {
            return null;
        }
    }

    public static <E extends GameObject> E findDeactive(Class<E> cls) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            if(!object.active
                    && object.getClass().isAssignableFrom(cls)) {
                return (E)object;
            }
        }
        return null;
    }

    public static <E extends GameObject> E findIntersects(Class<E> cls
        , BoxCollider hitBox) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            if(object.active
                && object.getClass().isAssignableFrom(cls)
                && object.hitBox != null
                && object.hitBox.intersects(hitBox)) {
                return (E) object;
            }
        }
        return null;
    }

    // non-static ~ dinh nghia doi tuong
//    public BufferedImage image; // = null
    public Renderer renderer;
    public Vector2D position;
    public Vector2D velocity;
    public boolean active;
    public BoxCollider hitBox; // = null
    public Vector2D anchor;

    public GameObject() {
        objects.add(this);
        position = new Vector2D();
        velocity = new Vector2D();
        active = true;
        anchor = new Vector2D(0.5, 0.5);
    }

    public void render(Graphics g) {
        if(renderer != null) {
            renderer.render(g, this);
        }
//        if(image != null) {
//            g.drawImage(
//                    image,
//                    (int) position.x,
//                    (int) position.y,
//                    null
//            );
//        }
    }

    public void run() {
        position.add(velocity);
    }

    public void deactive() {
        active = false;
    }

    public void reset() {
        active = true;
    }
}
