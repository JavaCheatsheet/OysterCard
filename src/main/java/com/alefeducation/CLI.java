package main.java.com.alefeducation;

import core.transportation.Tube;
import core.card.Card;
import core.transportation.Bus;

import java.math.BigDecimal;
import java.util.UUID;

public class CLI {
    public static void main(String args[]) {
        try {
            Card card = new Card(UUID.randomUUID());
            card.topUp(new BigDecimal(30));

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
            bus.checkin("Earlscourt");
            bus.checkout("Chelsea");

            System.out.println("Account Balance: "
                    + card.getAmount());

            System.out.println("Journey from Earlscourt to Hammersmith");
            tube.checkin("Earlscourt");
            tube.checkout("Hammersmith");

            System.out.println("Account Balance: "
                    + card.getAmount());

        } catch (Exception e){
          System.out.println(e.getMessage());
        }


    }
}
