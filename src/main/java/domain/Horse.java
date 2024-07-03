package domain;

import application.HorseRace;

import java.util.Random;

public class Horse implements Runnable{
    private final String name;
    private int position;
    private long timeRecord;
    private final Random random = new Random();

    public Horse(String name){
        this.name = name;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        while (position < HorseRace.TRACK_LENGTH) {
            int ranNum = random.nextInt(3) +1;

            if(position + ranNum >= HorseRace.TRACK_LENGTH){
                position = HorseRace.TRACK_LENGTH;
            } else {
                position += ranNum;
            }

            try{
                Thread.sleep(1100);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        timeRecord = System.currentTimeMillis() - startTime;
    }

    public int getPosition(){
        return position;
    }

    public String getName(){
        return name;
    }

    public long getTimeRecord(){
        return timeRecord;
    }
}
