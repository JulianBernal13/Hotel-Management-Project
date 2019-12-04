package HotelManagement.Command;

import java.io.IOException;
import java.text.ParseException;

public interface iCommand {
    public boolean execute() throws IOException, ParseException;
    public String getDescription();
}
