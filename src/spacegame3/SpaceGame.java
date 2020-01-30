package spacegame3;

import javafx.application.Application;
import javafx.stage.Stage;
import spacegame3.gamedata.StoryTellingScheme;
import spacegame3.userinterface.startscreen.StartScreen;

import java.util.logging.Logger;

public class SpaceGame extends Application {
    private static final Logger LOG = Logger.getLogger(SpaceGame.class.getName());


    private final StartScreen startScreen;

    private Stage stage;

    private StoryTellingScheme storyTellingScheme;

    private boolean gameStarted;

    public SpaceGame() {
        startScreen = new StartScreen(this);
        storyTellingScheme = null;
        gameStarted = false;
    }

    public StoryTellingScheme getStoryTellingScheme() {
        return storyTellingScheme;
    }

    public void setStoryTellingScheme(StoryTellingScheme storyTellingScheme) {
        this.storyTellingScheme = storyTellingScheme;
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        stage.setTitle("My magic space game (find a better title)!");
        stage.setScene(startScreen);
        stage.sizeToScene();

        stage.show();

        stage.setMinHeight(primaryStage.getHeight());
        stage.setMinWidth(primaryStage.getWidth());
    }

    public static void main(String[] args) {
        launch(args);
    }

    public boolean gameStarted() {
        return gameStarted;
    }


}
