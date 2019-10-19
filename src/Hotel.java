/**
 * @author Yingxie Gao
 * @date 10/18/19 22:30
 */
public class Hotel {
    private String name;
    private String location;
    private Room[] room;

    public Hotel(String name, String location, Room[] room) {
        this.name = name;
        this.location = location;
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Room[] getRoom() {
        return room;
    }

    public void setRoom(Room[] room) {
        this.room = room;
    }
}
