package com.arcore.example.arcoremanager.object;

import com.arcore.example.core.FrameSettings;

public class TankObjectDrawer extends SimpleArCoreObjectDrawer {

    private static final String TAG = "AndyObjectDrawer";

    public TankObjectDrawer() {
        super("WartHog.obj", "Tire.jpg");
    }

    @Override
    public void onDraw(FrameSettings canvas) {
        // Visualize anchors created by touch.
        for (ArCoreObject bugDroid : positions) {
            if (!bugDroid.isTracking()) {
                continue;
            }
            bugDroid.toMatrix(mAnchorMatrix, 0);

            // Update and draw the model and its shadow.
            super.objectRenderer.updateModelMatrix(mAnchorMatrix, bugDroid.getScale(), bugDroid.getRotation(), bugDroid.getTranslationX(), bugDroid.getTranslationZ());
            super.objectRenderer.draw(canvas.getCameraMatrix(), canvas.getProjMatrix(), canvas.getLightIntensity());
        }
    }
}
