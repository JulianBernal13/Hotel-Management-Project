package HotelManagement;

import java.io.IOException;
import java.text.ParseException;

public class CommandShowHotelInfo extends aCommandManager {
    public CommandShowHotelInfo(Hotel h) {
        hotel = h;
        manager = hotel.getManager();
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        FileReader.displayHotelInfo(hotel.getPath());
        return true;
    }

    @Override
    public String getDescription() {
        return "display hotel information";
    }
}
