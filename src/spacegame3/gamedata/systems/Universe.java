package spacegame3.gamedata.systems;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Universe {
    private static final Logger LOG = getLogger(Universe.class.getName());

    private static final String systemsPath = "data/systems/";
    private static final int ID_POSITION = 0;

    private final Path storyPath;

    private final Map<String, StarSystem> universe;

    public Universe(Path storyPath) {
        this.storyPath = storyPath;
        universe = new HashMap<>();
    }

    public StarSystem getStarSystem(String name){
        return universe.computeIfAbsent(name, this::loadStarSystem);
    }

    private StarSystem loadStarSystem(String name) {
        Path systemPath = storyPath.resolve(systemsPath + name + ".txt");

        StarSystem ss = null;

        try (BufferedReader read = Files.newBufferedReader(systemPath)) {

            Map<String, List<String>> data = new HashMap<>();
            data.computeIfAbsent(StarSystem.ID_NAME, k -> new LinkedList<>()).add("name|" + name);
            String line = read.readLine();
            while (line != null){
                if (!line.isBlank() && !line.startsWith("#")) {
                    String[] splitted = line.split(":");
                    String id = splitted[ID_POSITION];

                    if ("".equals(id)) {
                        id = StarSystem.ID_NAME;
                    }

                    data.computeIfAbsent(id, k -> new LinkedList<>()).add(String.join(":", Arrays.copyOfRange(splitted
                            , 1, splitted.length)));

                }
                line = read.readLine();
            }

            ss = new StarSystem(data);


        } catch (IOException e) {
            LOG.severe(() -> "Trouble loading system " + name + " " + e.toString());
        }

        return ss;
    }
}
