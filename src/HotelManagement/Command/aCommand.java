package HotelManagement.Command;

import HotelManagement.Hotel;
import HotelManagement.iCommand;

import java.io.IOException;

public abstract class aCommand implements iCommand {
    protected Hotel hotel;
}
