package edu.lewisu.cs.tanwe;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

/**
 * This class represents an object that appears on the screen. 
 * It consists of a label. When the user clicks it, it triggers 
 * a sound to be played. Each SoundLabel object has a Label that
 * appears on the screen that the user will click, and a Sound
 * that will be played when it is clicked.
 */

class SoundLabel {
	private Label label;
	private Sound sound;
    private Texture icon;
    private Texture iconClicked;
	private float iconX, iconY;
	private float iconWidth, iconHeight;
	private float labelX, labelY;
	private float labelWidth, labelHeight;

	public Sound getSound() {
		return sound;
	}
	public Texture getIcon() {
		return icon;
    }
    public Texture getClickedIcon() {
        return iconClicked;
    }
	public float getIconX()  {
		return iconX;
	}
	public float getIconY() {
		return iconY;
	}
	public Label getLabel() {
		return label;
	}
	public float getLabelX() {
		return labelX;
	} 
	public float getLabelY() {
		return labelY;
	}
	public float getLabelWidth() {
		return labelWidth;
	}
	public float getLabelHeight() {
		return labelHeight;
	}

	/**
	 * This sets up a SoundLabel that is ready to be clicked and play sounds
	 * @param pathToSound where the sound file is located
	 * @param drumName the text to show on the screen
	 * @param style the font to use (in a nutshell)
	 * @param xpos xcoord where label will appear
	 * @param ypos ycoord where label will appear
	 */
	public SoundLabel(String pathToSound, String drumName, LabelStyle style,
	int xpos, int ypos) {
		sound = Gdx.audio.newSound(Gdx.files.internal(pathToSound));
        icon = new Texture("hit_icon.png");
        iconClicked = new Texture("hit_icon_clicked.png");
		iconX = xpos;
		iconY = ypos;
		iconWidth = icon.getWidth();
		iconHeight = icon.getHeight();
		labelX = iconX-15;
		labelY = iconY-15;
		label = new Label(drumName,style);
		label.setPosition(labelX,labelY);
	}
	/**
	 * plays sound at max value
	 */
	public void playSound() {
		sound.play();
	}
	/**
	 * plays sound at requested volume
	 * @param vol the requested volume (between 0 and 1)
	 */
	public void playSound(float vol) {
		sound.play(vol);
	}
	/**
	 * This determines if the label was clicked
	 * @param x where the mouse's x coordinate is
	 * @param y where the mouse's y coordinate is
	 * @return true if x,y lie within the label's area
	 */
	public boolean wasClicked(int x, int y) {
		if (x >= iconX && x <= iconX+iconWidth &&
		y >= iconY && y <= iconY+iconHeight) {
			return true;
		} else {
			return false;
		}
    }
}