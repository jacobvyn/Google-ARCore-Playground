package com.arcore.example.arcoremanager.object;

import com.google.ar.core.Anchor;
import com.arcore.example.core.rendering.PlaneAttachment;

public class ArCoreObject {
    private final PlaneAttachment planeAttachment;
    private boolean mIsAnimateAppereance;
    private float scale;
    private float rotation;
    private float translationX;
    private float translationZ;

    private final static float DELTA_Z = 10f;
    private final static float DELTA_X = 4f;
    private final static int DOUBLE_FRAMES = 2;
    private final static int CAMERA_MAX_FRAMES = 30;
    private final static int MAX_FRAMES = CAMERA_MAX_FRAMES * DOUBLE_FRAMES;
    private int mCurrentFrame = 0;

    public ArCoreObject(PlaneAttachment planeAttachment, boolean needAnimate) {
        this.planeAttachment = planeAttachment;
        mIsAnimateAppereance = needAnimate;
        if (mIsAnimateAppereance) {
            translationZ -= DELTA_Z;
            translationX += DELTA_X;
        }
        this.scale = 1f;
    }

    public Anchor getAnchor() {
        return planeAttachment.getAnchor();
    }

    public PlaneAttachment getPlaneAttachment() {
        return planeAttachment;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void setRotation(float angle) {
        this.rotation = angle;
    }

    public float getRotation() {
        return rotation;
    }

    public void setTranslation(float distanceX, float distanceZ) {
        translationX += distanceX;
        translationZ += distanceZ;
    }

    public float getTranslationX() {
        if (mIsAnimateAppereance) {
            mCurrentFrame++;
            return translationX -= DELTA_X / CAMERA_MAX_FRAMES;
        } else {
            return translationX;
        }
    }

    public float getTranslationZ() {
        if (mIsAnimateAppereance) {
            mCurrentFrame++;
            if (mCurrentFrame >= MAX_FRAMES) {
                mIsAnimateAppereance = false;
            }
            return translationZ += DELTA_Z / CAMERA_MAX_FRAMES;
        } else {
            return translationZ;
        }
    }
}
