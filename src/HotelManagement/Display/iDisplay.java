package HotelManagement.Display;

import HotelManagement.Command.iCommand;

import java.io.IOException;
import java.text.ParseException;

public interface iDisplay {
    public void addCommand(iCommand cmd);
    public boolean displayCommand() throws IOException, ParseException;
}
