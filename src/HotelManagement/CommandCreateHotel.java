package HotelManagement;

import java.io.File;
import java.io.IOException;

public class CommandCreateHotel implements iCommand {

    private File hotelFile;

    CommandCreateHotel(File file) {
        hotelFile = file;
    }

    @Override
    public boolean execute() throws IOException {
        HotelFileController.createHotel(hotelFile);
        return true;
    }

    @Override
    public String getDescription() {
        return "create a new hotel";
    }
}
