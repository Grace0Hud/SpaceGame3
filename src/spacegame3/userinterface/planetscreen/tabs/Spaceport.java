package spacegame3.userinterface.planetscreen.tabs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import spacegame3.gamedata.systems.tabdata.SpaceportPerson;
import spacegame3.gamedata.systems.tabdata.SpaceportTabRecord;
import spacegame3.userinterface.planetscreen.TabLike;

public class Spaceport extends TabLike<SpaceportTabRecord> {

    private static final int BUTTON_MAX_HEIGHT = 25;
    private static final int BUTTON_PREF_HEIGHT = 25;
    private static final int BUTTON_MIN_HEIGHT = 25;
    private static final int BUTTON_MAX_WIDTH = 150;
    private static final int BUTTON_PREF_WIDTH = 150;
    private static final int BUTTON_MIN_WIDTH = 150;

    private final TilePane tp;

    private Button selected;


    public Spaceport(SpaceportTabRecord record) {
        super(record, new HBox());

        VBox leftSide = new VBox();
        StackPane rightSide = new StackPane();
        rightSide.setStyle("-fx-background-color: darkslategray");

        leftSide.maxWidthProperty().bind(basePane.widthProperty().multiply(0.66));
        leftSide.minWidthProperty().bind(basePane.widthProperty().multiply(0.66));
        leftSide.prefWidthProperty().bind(basePane.widthProperty().multiply(0.66));

        leftSide.maxHeightProperty().bind(basePane.heightProperty());
        leftSide.minHeightProperty().bind(basePane.heightProperty());
        leftSide.prefHeightProperty().bind(basePane.heightProperty());

        rightSide.maxWidthProperty().bind(basePane.widthProperty().multiply(0.34));
        rightSide.minWidthProperty().bind(basePane.widthProperty().multiply(0.34));
        rightSide.prefWidthProperty().bind(basePane.widthProperty().multiply(0.34));

        rightSide.maxHeightProperty().bind(basePane.heightProperty());
        rightSide.minHeightProperty().bind(basePane.heightProperty());
        rightSide.prefHeightProperty().bind(basePane.heightProperty());


        ScrollPane sp = new ScrollPane();

        sp.maxWidthProperty().bind(leftSide.widthProperty());
        sp.minWidthProperty().bind(leftSide.widthProperty());
        sp.prefWidthProperty().bind(leftSide.widthProperty());

        sp.maxHeightProperty().bind(leftSide.heightProperty().multiply(0.66));
        sp.minHeightProperty().bind(leftSide.heightProperty().multiply(0.66));
        sp.prefHeightProperty().bind(leftSide.heightProperty().multiply(0.66));

        tp = new TilePane();

        tp.setHgap(10);
        tp.setVgap(10);

        sp.setContent(tp);

        sp.setFitToWidth(true);

        leftSide.getChildren().addAll(sp);

        basePane.getChildren().addAll(leftSide, rightSide);

    }

    public void add(SpaceportPerson spaceportPerson){
        Button btn = createButton(spaceportPerson);

        tp.getChildren().add(btn);

    }

    public Button createButton(SpaceportPerson person){
        Button btn = new Button();

        btn.setMaxSize(BUTTON_MAX_WIDTH, BUTTON_MAX_HEIGHT);
        btn.setMinSize(BUTTON_MIN_WIDTH, BUTTON_MIN_HEIGHT);
        btn.setPrefSize(BUTTON_PREF_WIDTH, BUTTON_PREF_HEIGHT);

        btn.setOnAction(new Action(person, btn));

        return btn;
    }

    @Override
    protected void attachTo(){
        basePane.maxWidthProperty().bind(container.getContentPane().widthProperty());
        basePane.minWidthProperty().bind(container.getContentPane().widthProperty());
        basePane.prefWidthProperty().bind(container.getContentPane().widthProperty());

        basePane.maxHeightProperty().bind(container.getContentPane().heightProperty());
        basePane.minHeightProperty().bind(container.getContentPane().heightProperty());
        basePane.prefHeightProperty().bind(container.getContentPane().heightProperty());
    }


    private class Action implements EventHandler<ActionEvent> {
        private final SpaceportPerson person;
        private final Button me;

        public Action(SpaceportPerson person, Button me) {
            this.person = person;
            this.me = me;
        }

        @Override
        public void handle(ActionEvent event) {
            selected.setDisable(false);
            me.setDisable(true);
            selected = me;
        }
    }

}
