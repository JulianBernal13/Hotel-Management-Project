package HotelManagement.Display;

import HotelManagement.Command.CommandAddInvItem;
import HotelManagement.Command.CommandGetInventoryInfo;
import HotelManagement.Command.CommandGetInventoryItemInfo;
import HotelManagement.Command.CommandUseInventoryItem;
import HotelManagement.Hotel;

public class DisplayInventoryManager extends aDisplayManager{
    public DisplayInventoryManager(Hotel h) {
        this.hotel = h;
        this.manager = h.getManager();
        addCommand(new CommandGetInventoryInfo(hotel));
        addCommand(new CommandGetInventoryItemInfo(hotel));
        addCommand(new CommandUseInventoryItem(hotel));
        addCommand(new CommandAddInvItem(hotel));
    }
}
