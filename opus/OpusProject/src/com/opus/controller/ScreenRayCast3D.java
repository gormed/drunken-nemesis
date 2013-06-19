/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * MazeTD Project (c) 2012 by Hady Khalifa, Ahmed Arous and Hans Ferchland
 * 
 * MazeTD rights are by its owners/creators.
 * The project was created for educational purposes and may be used under 
 * the GNU Public license only.
 * 
 * If you modify it please let other people have part of it!
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * GNU Public License
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License 3 as published by
 * the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 * 
 * Email us: 
 * hans[dot]ferchland[at]gmx[dot]de
 * 
 * 
 * Project: MazeTD Project
 * File: ScreenRayCast3D.java
 * Type: collisions.raycasts.ScreenRayCast3D
 * 
 * Documentation created: 14.05.2012 - 18:59:39 by Hans Ferchland
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package com.opus.controller;

import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.opus.gui.OpusApplication;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class ScreenRayCast3D is responsible for firing events when a 3D-object
 * is clicked.
 *
 * @author Hans Ferchland
 */
public class ScreenRayCast3D implements TuioListener {

    /**
     * The RAYCAS t_3 d.
     */
    public static String RAYCAST_3D = "Raycast_3D";
    public static final int CALIB_X = 0;
    public static final int CALIB_Y = 0;

    //==========================================================================
    //===   Singleton
    //==========================================================================
    /**
     * The hidden constructor of the singleton.
     */
    private ScreenRayCast3D() {
        // add listener for left click
//        EventManager.getInstance().addMouseButtonEvent(
//                RAYCAST_3D,
//                new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
    }

    /**
     * Static method to retrieve the one and olny reference to the manager.
     *
     * @return the reference of the ScreenRayCast3D
     */
    public static ScreenRayCast3D getInstance() {
        return ScreenRayCast3DHolder.INSTANCE;
    }

    /**
     * Holder class for the ScreenRayCast3D.
     */
    private static class ScreenRayCast3DHolder {

        /**
         * The Constant INSTANCE.
         */
        private static final ScreenRayCast3D INSTANCE = new ScreenRayCast3D();
    }
    //==========================================================================
    //===   Static Fields
    //==========================================================================
    /**
     * The Constant MOUSE_MOVEMENT_TOLERANCE.
     */
    public static final float MOUSE_MOVEMENT_TOLERANCE = 0.25f;
    //==========================================================================
    //===   Private Fields
    //==========================================================================
    /**
     * The clickable3 d.
     */
    private Node clickable3D;
    /**
     * The last hovered.
     */
    private HashMap<Long, Clickable3D> lastHovered = null;
    /**
     * The cam.
     */
    private Camera cam;// = MazeTDGame.getInstance().getCamera();
    /**
     * The last mouse position.
     */
    private HashMap<Long, Vector2f> lastMousePosition = new HashMap<Long, Vector2f>();// Vector2f.ZERO.clone();
    /**
     * The initialized.
     */
    private boolean initialized;
    private HashMap<Long, TuioCursor> cursorList = new HashMap<Long, TuioCursor>();
    private HashMap<Long, Node> visCursorList = new HashMap<Long, Node>();
    private ArrayList<TuioCursor> addCursors = new ArrayList<TuioCursor>();
    private ArrayList<TuioCursor> removeCursors = new ArrayList<TuioCursor>();

    //==========================================================================
    //===   Methods
    //==========================================================================
    /**
     * Initializes the class if not already done or it was destroyed.
     */
    public void initialize(Camera camera, Node rootNode) {
        if (initialized) {
            return;
        }
        cam = camera;
        clickable3D = rootNode;
//        game.getRootNode().attachChild(clickable3D);
//        inputManager.setCursorVisible(true);
//
//        EventManager.getInstance().addMouseInputListener(
//                this,
//                RAYCAST_3D);
        initialized = true;
    }

    /**
     * Destroys the class, removes all resources from jme3.
     */
    public void destroy() {
        if (!initialized) {
            return;
        }

//        EventManager.getInstance().
//                removeMouseInputListener(this);
        lastHovered = null;
        clickable3D.detachAllChildren();
//        game.getRootNode().detachChild(clickable3D);
        clickable3D = null;
        lastMousePosition = null;//Vector2f.ZERO.clone();
        initialized = false;
    }

    /**
     * Checks if the class was already initialized.
     *
     * @return true if initialize false otherwise
     */
    public boolean isInitialized() {
        return initialized;
    }

    /**
     * Updates the mouse movement on screen for movement checks.
     *
     * @param tpf the gamp between two calls
     */
    public void update(float tpf) {
        for (TuioCursor tcur : addCursors) {
            Node cursor = new Node(tcur.getCursorID() + "");
            visCursorList.put(tcur.getSessionID(), cursor);
            Geometry geom = new Geometry("Point", new Box(Vector3f.ZERO, 1, 1, 1));

            Material mat = new Material(OpusApplication.getInstance().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
            mat.setColor("Color", ColorRGBA.Blue);
            geom.setMaterial(mat);
            geom.setLocalScale(10);

            cursor.attachChild(geom);
            cursor.setLocalTranslation(tcur.getX(), tcur.getY(), 0);
            OpusApplication.getInstance().getGuiNode().attachChild(cursor);
            cursorList.put(tcur.getSessionID(), tcur);
        }
        addCursors.clear();
        for (TuioCursor tcur : removeCursors) {
            OpusApplication.getInstance().getGuiNode().detachChild(visCursorList.remove(tcur.getSessionID()));
            cursorList.remove(tcur.getSessionID());
        }
        removeCursors.clear();

        for (Map.Entry<Long, TuioCursor> entry : cursorList.entrySet()) {
            Vector2f mouse = new Vector2f(entry.getValue().getX() * cam.getWidth(), (1 - entry.getValue().getY()) * cam.getHeight());
            mouse.addLocal(CALIB_X, CALIB_Y);
            Node n = visCursorList.get(entry.getValue().getSessionID());
            if (n != null) {
                n.setLocalTranslation(mouse.x, mouse.y, 0);
            }
//            boolean isInWindow =
//                    mouse.x >= 0 && mouse.y >= 0
//                    && mouse.x <= cam.getWidth() && mouse.y <= cam.getHeight();
//            if (isInWindow) {
//                checkMouseMovement(mouse, tpf);
//            }
            lastMousePosition.put(entry.getKey(), mouse);
        }
    }

    /**
     * Checks if the mouse moved over an object to call its onRayCastMouseOver()
     * method.
     *
     * @param mouse the mouse position
     * @param tpf the gap between 2 two calls
     */
    public void checkMouseMovement(long id, Vector2f mouse, float tpf) {
        float diff = Math.abs(mouse.subtract(lastMousePosition.get(id)).length());
        //==========================================================================
        //===   Mouse Moved
        //==========================================================================

        if (diff > MOUSE_MOVEMENT_TOLERANCE) {

            // 1. Reset results list.
            CollisionResults results = new CollisionResults();
            // 2. Aim the ray from cam loc to cam direction.
            Vector2f click2d = mouse;
//            Vector3f click3d = cam.getWorldCoordinates(
//                    new Vector2f(click2d.x, click2d.y), 0f).clone();
//            lastWorldHit = click3d.clone();
//            Vector3f dir = cam.getWorldCoordinates(
//                    new Vector2f(click2d.x, click2d.y), 1f).subtractLocal(click3d).normalizeLocal();
            Vector3f click3d =
                    new Vector3f(click2d.x, click2d.y, 0f);
            //lastWorldHit.put(id, click3d.clone());
            Vector3f dir = new Vector3f(click2d.x, click2d.y, 1f).subtractLocal(click3d).normalizeLocal();
            Ray ray = new Ray(click3d, dir);
            // 3. Collect intersections between Ray and Shootables in results list.
            clickable3D.collideWith(ray, results);
            // 4. Print the results
            System.out.println("----- 3D Collisions? " + results.size() + " -----");
            for (int i = 0; i < results.size(); i++) {
                // For each hit, we know distance, impact point, name of geometry.
                float dist = results.getCollision(i).getDistance();
                Vector3f pt = results.getCollision(i).getContactPoint();
                String hit = results.getCollision(i).getGeometry().getName();
                System.out.println("* 3D Collision #" + i);
                System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");
            }
            // 5. Use the results (we mark the hit object)
            if (results.size() > 0) {
//                for (CollisionResult r : results) {
                CollisionResult r = results.getClosestCollision();
                    Spatial n = r.getGeometry().getParent().getParent();
                    // recursivly check if object is clickable or not
                    if (n != null) {
                        if (n instanceof Clickable3D) {
                            decideLeftOrOver((Clickable3D) n, click2d, r);
                        }
//                        parent = n.getParent();
//                        while (parent != null) {
//                            if (parent instanceof Clickable3D) {
//                                decideLeftOrOver((Clickable3D) parent, click2d, r);
//                            }
//                            parent = parent.getParent();
//                        }
                    }
//                }
//                // The closest collision point is what was truly hit:
//                CollisionResult closest = results.getClosestCollision();
//                Spatial n = closest.getGeometry();
//                Spatial parent;
//                // this is where the magic happens
//                // if there was a geometry check
//                if (n != null) {
//                    // okay, we have geometry, is it inheriting Clickable3D
//                    if (n instanceof Clickable3D) {
//                        // okay than decide if we entered the geometry or
//                        // left it
//                        decideLeftOrOver((Clickable3D) n, click2d, closest);
//                    }
//                    // does the node have a parent
//                    parent = n.getParent();
//                    // really?
//                    while (parent != null) {
//                        // okay, than check if Clickable3D
//                        if (parent instanceof Clickable3D) {
//                            try {
//                                // okay than decide if we entered the geometry 
//                                // or left it
//                                decideLeftOrOver((Clickable3D) parent, click2d, closest);
//                            } catch (ClassCastException castException) {
//                                System.err.println(castException.getStackTrace());
//                            }
//                        }
//                        // okay, that find me the next parent and do this again
//                        parent = parent.getParent();
//                    }
//                }
            } else {
                // well otherwise we have no selection at all
            }

        }

    }

    /**
     * Invokes a RayCast3DNodes onMouseOver method.
     *
     * @param r the hit RayCast3DNode
     * @param click2d the screen pos
     * @param closest the 3d hit params
     */
    private void invokeOnMouseOver(final Clickable3D r, final Vector2f click2d, final CollisionResult closest) {
        Future<Clickable3D> o = OpusApplication.getInstance().enqueue(new Callable<Clickable3D>() {
            @Override
            public Clickable3D call() throws Exception {
                r.onRayCastMouseOver(click2d, closest);
                return r;
            }
        });
//        try {
//            while (!o.isDone()) {
//                lastHovered.put(, r) = o.get();
//            }
//        } catch (InterruptedException ex) {
//            Logger.getLogger(ScreenRayCast3D.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ExecutionException ex) {
//            Logger.getLogger(ScreenRayCast3D.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    /**
     * Invokes a RayCast3DNodes onMouseLeft method.
     *
     * @param r the hit RayCast3DNode
     * @param click2d the screen pos
     * @param closest the 3d hit params
     */
    private void invokeOnMouseLeft(final Clickable3D r, final Vector2f click2d, final CollisionResult closest) {
        OpusApplication.getInstance().enqueue(new Callable<Clickable3D>() {
            @Override
            public Clickable3D call() throws Exception {
                r.onRayCastMouseLeft(click2d, closest);
                return r;
            }
        });
    }

    /**
     * Desides if a node was left with mouse-pointer or entered.
     *
     * @param spatial the spatial
     * @param click2d the click2d
     * @param closest the closest
     */
    private void decideLeftOrOver(Clickable3D node, Vector2f click2d, CollisionResult closest) {
        if (lastHovered != null && !node.equals(lastHovered)) {
//            invokeOnMouseLeft(lastHovered, click2d, closest);
        }
        invokeOnMouseOver(node, click2d, closest);
    }

    @Override
    public void addTuioObject(TuioObject tobj) {
    }

    @Override
    public void updateTuioObject(TuioObject tobj) {
    }

    @Override
    public void removeTuioObject(TuioObject tobj) {
    }

    @Override
    public void addTuioCursor(final TuioCursor tcur) {
        if (!cursorList.containsKey(tcur.getSessionID())) {

            Future<ArrayList<TuioCursor>> fut = OpusApplication.getInstance().enqueue(new Callable<ArrayList<TuioCursor>>() {
                @Override
                public ArrayList<TuioCursor> call() throws Exception {
                    addCursors.add(tcur);
                    return addCursors;
                }
            });
            while (!fut.isDone()) {
            }
            try {
                addCursors = fut.get();
            } catch (InterruptedException ex) {
                Logger.getLogger(ScreenRayCast3D.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(ScreenRayCast3D.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void updateTuioCursor(TuioCursor tcur) {
    }

    @Override
    public void removeTuioCursor(final TuioCursor tcur) {
        onClick(tcur);

        Future<ArrayList<TuioCursor>> fut = OpusApplication.getInstance().enqueue(new Callable<ArrayList<TuioCursor>>() {
            @Override
            public ArrayList<TuioCursor> call() throws Exception {
                removeCursors.add(tcur);
                return removeCursors;
            }
        });
        while (!fut.isDone()) {
        }
        try {
            removeCursors = fut.get();
        } catch (InterruptedException ex) {
            Logger.getLogger(ScreenRayCast3D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ScreenRayCast3D.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void refresh(TuioTime ftime) {
    }

    //==========================================================================
    //===   Mouse Clicks
    //==========================================================================
    /**
     * This method is raised on a click on the left mouse button to check 3d
     * click events (ray casts). All Spatials that implement RayCast3DNode will
     * be clickable and will execute the onRayCast3D() method on click.
     *
     */
    public void onClick(TuioCursor cursor) {

        if (cursor != null) {
            // 1. Reset results list.
            CollisionResults results = new CollisionResults();
            // 2. Aim the ray from cam loc to cam direction.
            Vector2f click2d = new Vector2f(cursor.getX() * cam.getWidth(), (1 - cursor.getY()) * cam.getHeight());
            click2d.addLocal(CALIB_X, CALIB_Y);
            Vector3f click3d =
                    new Vector3f(click2d.x, click2d.y, 0f);
            Vector3f dir = new Vector3f(click2d.x, click2d.y, 1f).subtractLocal(click3d).normalizeLocal();
            Ray ray = new Ray(click3d, dir);
            // 3. Collect intersections between Ray and Shootables in results list.
            clickable3D.collideWith(ray, results);
            // 4. Print the results
            System.out.println("----- 3D Collisions? " + results.size() + " -----");
            for (int i = 0; i < results.size(); i++) {
                // For each hit, we know distance, impact point, name of geometry.
                float dist = results.getCollision(i).getDistance();
                Vector3f pt = results.getCollision(i).getContactPoint();
                String hit = results.getCollision(i).getGeometry().getName();
                System.out.println("* 3D Collision #" + i);
                System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");
            }
            // 5. Use the results (we mark the hit object)
            if (results.size() > 0) {
//                for (CollisionResult r : results) {
                CollisionResult r = results.getClosestCollision();
                    Spatial n = r.getGeometry().getParent();
                    // recursivly check if object is clickable or not
                    if (n != null) {
                        if (n instanceof Clickable3D) {
                            invokeOnClick((Clickable3D) n, click2d, r);
                        }
                    }

            }
        }
    }

    /**
     * Invokes a RayCast3DNodes onClick method.
     *
     * @param spatial the spatial
     * @param click2d the screen pos
     * @param closest the 3d hit params
     */
    private void invokeOnClick(final Clickable3D node, final Vector2f click2d, final CollisionResult closest) {
        OpusApplication.getInstance().enqueue(new Callable<Clickable3D>() {
            @Override
            public Clickable3D call() throws Exception {
                node.onRayCastClick(click2d, closest);
                return node;
            }
        });

    }
}
