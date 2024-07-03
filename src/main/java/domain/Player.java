package domain;

public class Player implements Bettable {
    private Horse bettingHorse;

    @Override
    public void bet(Horse horseName) {
        bettingHorse = horseName;
    }

    @Override
    public Horse getBettingHorse(){
        return bettingHorse;
    }
}
