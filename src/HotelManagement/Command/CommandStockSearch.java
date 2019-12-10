package HotelManagement.Command;

import java.io.IOException;
import java.text.ParseException;

import HotelManagement.StockSerach;

public class CommandStockSearch implements iCommand{

	@Override
	public boolean execute() throws IOException, ParseException {
		StockSerach.StockLook();
		return true;
	}

	@Override
	public String getDescription() {
		return "Seach stocks";
	}
}
