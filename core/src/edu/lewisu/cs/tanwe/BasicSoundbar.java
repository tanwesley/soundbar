package edu.lewisu.cs.tanwe;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;


public class BasicSoundbar extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture drumkit;
	Texture drumstick;
	Texture note;
	OrthographicCamera cam;
	int WIDTH, HEIGHT;
	LabelStyle labelStyle;
	ArrayList<SoundLabel> kit = new ArrayList<SoundLabel>();
	float volume;
	float noteX, noteY;

	public void createSoundLabels() {
		kit.add(new SoundLabel("audio/snare.mp3","Snare",labelStyle,220,170));
		kit.add(new SoundLabel("audio/kick.mp3","Kick",labelStyle,300,210));
		kit.add(new SoundLabel("audio/crash.mp3","Crash",labelStyle,155,320));
		kit.add(new SoundLabel("audio/ride.mp3","Ride",labelStyle,380,300));
		kit.add (new SoundLabel("audio/hi_hat.mp3", "Hi Hat (Closed)",labelStyle,100,210));
		kit.add (new SoundLabel("audio/hi_hat_open.mp3", "Hi Hat (Open)",labelStyle,100,170));
		kit.add(new SoundLabel("audio/floor_tom.mp3","Floor Tom",labelStyle,390,170));
		kit.add(new SoundLabel("audio/china.mp3","China",labelStyle,460,220));
		kit.add(new SoundLabel("audio/hi_tom.mp3","High Tom",labelStyle,235,270));
	}

	public void setupLabelStyle() {
		labelStyle = new LabelStyle();
		labelStyle.font = new BitmapFont(Gdx.files.internal("fonts/arial.fnt"));
		labelStyle.fontColor = new Color(Color.MAGENTA);
	}
	/**
	 * render the soundLabel on the screen
	 */
	public void drawSoundLabel() {
		for (SoundLabel drum : kit) {
			drum.getLabel().draw(batch,1);
			batch.draw(drum.getIcon(),drum.getIconX(),drum.getIconY());
		}
	}

	@Override
	public void create () {
		background = new Texture("rug.png");
		drumkit = new Texture("drumkit.png");
		drumstick = new Texture("drumstick.png");
		note = new Texture("note.png");
		noteX = 0;
		noteY = 0;
		batch = new SpriteBatch();
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		volume = 1;
		cam = new OrthographicCamera(WIDTH,HEIGHT);
		cam.translate(WIDTH/2,HEIGHT/2);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		setupLabelStyle();
		createSoundLabels();
		try {
			// **ba-dum-tss**
			kit.get(0).playSound(); //snare
			TimeUnit.MILLISECONDS.sleep(150);
			kit.get(8).playSound(); //high tom
			TimeUnit.MILLISECONDS.sleep(300);
			kit.get(1).playSound(); //kick
			kit.get(2).playSound(); //crash
		} catch (InterruptedException e) {
			System.err.format("IOException: %s%n", e);		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
		batch.begin();
		batch.draw(background,0,0);
		batch.draw(drumkit,0,0);
		drawSoundLabel();
		if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
			for (SoundLabel drum : kit) {
				if (drum.wasClicked(Gdx.input.getX(),HEIGHT-Gdx.input.getY())) {
					drum.playSound(volume);
					batch.draw(drum.getClickedIcon(),drum.getIconX(),drum.getIconY());
					noteX = drum.getIconX();
					noteY = drum.getIconY();
				}
			}
		}
		batch.draw(note,noteX,noteY);
		batch.draw(drumstick,Gdx.input.getX()-10,HEIGHT-Gdx.input.getY()-20);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		drumkit.dispose();
		background.dispose();
		drumstick.dispose();
	}
}