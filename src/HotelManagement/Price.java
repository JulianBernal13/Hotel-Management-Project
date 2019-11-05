package HotelManagement;

public enum Price {
    singleRoomPrice(100),
    doubleRoomPrice(150),
    tripleRoomPrice(200),
    queenRoomPrice(200),
    kingRoomPrice(300);

    private int price;

    Price(int price) {
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
