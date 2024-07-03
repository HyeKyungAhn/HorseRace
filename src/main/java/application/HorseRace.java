package application;

import domain.Bettable;
import domain.Horse;
import domain.Player;

import java.util.*;

public class HorseRace implements Race{
    public static final int TRACK_LENGTH = 50;

    @Override
    public void round() {
        Horse horse1 = new Horse("알렉산더");
        Horse horse2 = new Horse("튼튼이");
        Horse horse3 = new Horse("침착맨");

        Horse[] horses = new Horse[]{horse1, horse2, horse3};

        Bettable player = new Player();

        Scanner scanner = new Scanner(System.in);

        Horse bettingHorse = getBettingHorse(scanner, horses);
        player.bet(bettingHorse);

        Thread thread_horse1 = new Thread(horse1);
        Thread thread_horse2 = new Thread(horse2);
        Thread thread_horse3 = new Thread(horse3);

        thread_horse1.start();
        thread_horse2.start();
        thread_horse3.start();

        HorseRace.match(horses);

        try { Thread.sleep(1000); } catch (InterruptedException e){ e.printStackTrace(); }

        showResult(horses, player);
    }

    private void showResult(Horse[] horses, Bettable player) {
        List<Horse> winners = getWinners(horses);

        System.out.println("결과");
        System.out.println(horses[0].getName()+"의 기록: " + horses[0].getTimeRecord());
        System.out.println(horses[1].getName()+"의 기록: " + horses[1].getTimeRecord());
        System.out.println(horses[2].getName()+"의 기록: " + horses[2].getTimeRecord());

        System.out.print("\n우승 말은 ");
        for (int i = 0; i < winners.size(); i++) {
            System.out.print(winners.get(i).getName() + " ");
        }
        System.out.println("입니다!\n");

        if(winners.contains(player.getBettingHorse())) {
            printWinSign();
        } else {
            printLoseSign();
        }
    }

    private static void printWinSign(){
        System.out.println("""
                  ___    ___ ________  ___  ___          ___       __   ___  ________   ___  ___      \s
                 |\\  \\  /  /|\\   __  \\|\\  \\|\\  \\        |\\  \\     |\\  \\|\\  \\|\\   ___  \\|\\  \\|\\  \\     \s
                 \\ \\  \\/  / | \\  \\|\\  \\ \\  \\\\\\  \\       \\ \\  \\    \\ \\  \\ \\  \\ \\  \\\\ \\  \\ \\  \\ \\  \\    \s
                  \\ \\    / / \\ \\  \\\\\\  \\ \\  \\\\\\  \\       \\ \\  \\  __\\ \\  \\ \\  \\ \\  \\\\ \\  \\ \\  \\ \\  \\   \s
                   \\/  /  /   \\ \\  \\\\\\  \\ \\  \\\\\\  \\       \\ \\  \\|\\__\\_\\  \\ \\  \\ \\  \\\\ \\  \\ \\__\\ \\__\\  \s
                 __/  / /      \\ \\_______\\ \\_______\\       \\ \\____________\\ \\__\\ \\__\\\\ \\__\\|__|\\|__|  \s
                |\\___/ /        \\|_______|\\|_______|        \\|____________|\\|__|\\|__| \\|__|   ___  ___\s
                \\|___|/                                                                      |\\__\\|\\__\\
                                                                                             \\|__|\\|__|
                                                                                                      \s
                """);
    }

    private static void printLoseSign(){
        System.out.println("""
                다음기회에...
                                            __
                                   _ ,___,-'",-=-.
                       __,-- _ _,-'_)_  (""`'-._\\ `.
                    _,'  __ |,' ,-' __)  ,-     /. |
                  ,'_,--'   |     -'  _)/         `\\
                ,','      ,'       ,-'_,`           :
                ,'     ,-'       ,(,-(              :
                     ,'       ,-' ,    _            ;
                    /        ,-._/`---'            /
                   /        (____)(----. )       ,'
                  /         (      `.__,     /\\ /,
                 :           ;-.___         /__\\/|
                 |         ,'      `--.      -,\\ |
                 :        /            \\    .__/
                  \\      (__            \\    |_
                   \\       ,`-, *       /   _|,\\
                    \\    ,'   `-.     ,'_,-'    \\
                   (_\\,-'    ,'\\")--,'-'       __\\
                    \\       /  // ,'|      ,--'  `-.
                     `-.    `-/ \\'  |   _,'         `.
                        `-._ /      `--'/             \\
                -hrr-      ,'           |              \\
                          /             |               \\
                       ,-'              |               /
                      /                 |             -'
                """);
    }

    private List<Horse> getWinners(Horse[] horses) {
        List<Horse> winners = new ArrayList<>();
        Horse first = horses[0];

        for (Horse horse : horses) {
            if(first.getTimeRecord()>horse.getTimeRecord()){
                first = horse;
            }
        }

        for (Horse horse : horses) {
            if(first.getTimeRecord()==horse.getTimeRecord()) winners.add(horse);
        }

        return winners;
    }

    private static Horse getBettingHorse(Scanner scanner, Horse[] horses){
        Horse bettingHorse = null;
        String input;

        for (Horse horse : horses) {
            System.out.print(horse.getName()+", ");
        }
        System.out.println("총 세마리 말이 있습니다\n어느 말에 베팅하시겠습니까?(말 이름을 입력해주세요)");

        while (true) {

            input = scanner.nextLine();

            boolean isValidInput = checkInput(input, horses);

            if(isValidInput) {
                for (Horse horse : horses) {
                    if (horse.getName().equals(input)) {
                        bettingHorse = horse;
                    }
                }
                break;
            } else {
                System.out.println("입력값이 잘못 되었습니다. 다시 입력해 주세요.");
            }
        }

        return bettingHorse;
    }

    private static boolean checkInput(String input, Horse[] horses) {
        boolean isValid = false;
        for (Horse horse : horses) {
            if(horse.getName().equals(input)){
                isValid = true;
                break;
            }
        }
        return isValid;
    }

    private static void match(Horse[] horses){
        int positionOfHorse1;
        int positionOfHorse2;
        int positionOfHorse3;

        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            positionOfHorse1 = horses[0].getPosition();
            positionOfHorse2 = horses[1].getPosition();
            positionOfHorse3 = horses[2].getPosition();

            System.out.printf("%"+(TRACK_LENGTH)+"s|(결승선)\n", "");

            print(horses[0].getName(), positionOfHorse1);
            print(horses[1].getName(), positionOfHorse2);
            print(horses[2].getName(), positionOfHorse3);

            if(positionOfHorse1==TRACK_LENGTH && positionOfHorse2==TRACK_LENGTH && positionOfHorse3==TRACK_LENGTH){
                break;
            }
        }
    }

    private static void print(String name, int position){
        System.out.println(name);

        for (int i = 0; i < position; i++) {
            System.out.print("-");
        }

        System.out.println("\uD83D\uDC0E\n");
    }
}
