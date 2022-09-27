import java.awt.*;

public class Player {

    private final String name;
    private final Color color;

    private final int id;
    private final String marker;

    public Player(String name, Color color, int id, String marker) {
        this.name = name;
        this.color = color;
        this.id = id;
        this.marker = marker;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    public String getMarker() {
        return marker;
    }
}
