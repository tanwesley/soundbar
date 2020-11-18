package edu.lewisu.cs.tanwe;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class VolumeBar {
    private Texture bar;
    private Texture icon;
    private float volume;
    private float barWidth;
    private float iconWidth;
    private float iconX;

    public VolumeBar(SpriteBatch batch, float xOrigin, float yOrigin) {
        this.bar = new Texture("volume_bar.png");
        this.icon = new Texture("volume_icon.png");
        this.barWidth = bar.getWidth();
        this.iconWidth = icon.getWidth();
        this.volume = 1;
        this.iconX = xOrigin + barWidth;

        batch.draw(bar,xOrigin,yOrigin);
        batch.draw(icon,iconX,yOrigin);
    }

    public void changeVolume() {
        
    }

    public float getVolume() {
        return volume;
    }



}