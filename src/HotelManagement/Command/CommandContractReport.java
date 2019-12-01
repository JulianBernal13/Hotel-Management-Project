package HotelManagement.Command;

import HotelManagement.ContractFileController;
import HotelManagement.Hotel;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author Yingxie Gao
 * @date 11/30/19 17:05
 */
public class CommandContractReport extends aCommandManager {

    public CommandContractReport(Hotel hotel){
        this.hotel = hotel;
        this.manager = hotel.getManager();
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        ContractFileController.contractReport(hotel);
        return true;
    }

    @Override
    public String getDescription() {
        return "Show contract financial report by year or month.";
    }
}
