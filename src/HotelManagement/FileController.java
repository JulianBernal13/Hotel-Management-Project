package HotelManagement;

public interface FileController {

    static String convertToTxt(String toBeConverted) {
        return toBeConverted + ".txt";
    }

    static String convertTxtBack(String toBeConverted) {
        int l = toBeConverted.length();
        if (l > 4 && toBeConverted.substring(l - 4) == ".txt") {
            return toBeConverted.substring(0, l - 4);
        }
        return null; //needs exception handling
    }

}
