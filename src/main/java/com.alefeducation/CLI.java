package main.java.com.alefeducation;

import main.java.com.alefeducation.modules.transportation.Tube;
import main.java.com.alefeducation.modules.card.Card;
import main.java.com.alefeducation.modules.transportation.Bus;

public class CLI {
    public static void main(String args[]) {
        int cardNumber = Integer.MAX_VALUE;
        Card card = new Card(cardNumber);
        card.topUp(30);

        System.out.println("Account Balance: "
                + card.getAmount());

        Tube tube = new Tube(card);

        System.out.println("Journey from Holborn to Earlcourt");
        tube.checkin("Holborn");
        tube.checkout("Earlscourt");

        System.out.println("Account Balance: "
                + card.getAmount());

        System.out.println("Journey 328 bus from Earl’s Court to Chelsea");
        Bus bus = new Bus(card);
        bus.checkin();
        bus.checkout();

        System.out.println("Account Balance: "
                + card.getAmount());

        System.out.println("Journey from Earlscourt to Hammersmith");
        tube.checkin("Earlscourt");
        tube.checkout("Hammersmith");

        System.out.println("Account Balance: "
                + card.getAmount());
    }
}
