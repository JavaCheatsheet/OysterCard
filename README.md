# OysterCard Solution
The Oyster Card Problem - alefeducation.com

## Objective - Model A Fare Card System.

Demonstrate a user loading a card with £30, 
and taking the following trips, and then 
viewing the balance.

- Tube Holborn to Earl’s Court
- 328 bus from Earl’s Court to Chelsea
- Tube Earl’s court to Hammersmith

When the user passes through the inward barrier 
at the station, their oyster card is charged 
the maximum fare.

When they pass out of the barrier at the exit 
station, the fare is calculated and the maximum
fare transaction removed and replaced with the 
real transaction (in this way, if the user doesn’t 
swipe out, they are charged the maximum fare).

All bus journeys are charged at the same price.
Any bus journey - £1.80

Station - Zone(s)
Holborn - 1
Earl’s Court - 1, 2
Wimbledon - 3
Hammersmith - 2

Journey Fare
Anywhere in Zone 1 - £2.50
Any one zone outside zone 1 - £2.00
Any two zones including zone 1 - £3.00
Any two zones excluding zone 1 - £2.25
Any three zones - £3.20
Any bus journey - £1.80

## Entities
What are the interacting entities?

Note - I am assuming myself as a traveller
who will be using these means of transportation
travelling around different locations using
the card.

For this the most important thing would be 
the card.
1. Card

Second would be locations
2. Location

Third, as the locations are divided into
zones based on which fares vary.
3. Zone

Also, important are the modes of transportations.
Tube (or Metro) and Buses are two services 
people can use.
4. Transportation

Travelling from a location to another are defined
by which zone those locations fall while Tube is
used. But for buses, it remains the same for any
journey regardless of zones. By this I assume that
it does not matter if I travel 1km or 50km.

The first thing I need to do before being able 
to travel is have enough balance in my card to 
be able to use any of the services, use the Tube 
or the Bus.

The minimum amount that is needed while using 
the Bus which is £1.8.

So the first feature of the system would be I
to should be able to load balance. The balance
would be deducted as per fare. 

Indeed, at the very first, I need to get a card 
but that is not the requirement at the moment.

The second feature of the system is calculating
the fair. This should be the core business logic.
And we will focus more here and start from here.

The complex calculations is when I use the 
Tube to travel. So first let's assume I can 
only travel via Tube. And to design this 
system we need understand what are the charges
and payment process.
"
When the user passes through the inward barrier
at the station, their oyster card is charged
the maximum fare.

When they pass out of the barrier at the exit
station, the fare is calculated and the maximum
fare transaction removed and replaced with the
real transaction (in this way, if the user doesn’t
swipe out, they are charged the maximum fare).
"

From the given requirement, when ever I check
into the station, the fare is set to maximum which
in this case should be £3.20 as fares provided -
Any three zones - £3.20

So to use Tube, I must have minimum balance in my
card. The checking is done while entering a station.
And If the balance is low I would not be able to
enter and use the Tube.

The card is charged the actual amount after I 
check out of the station. If I don't swipe the 
card while checking out, the card will be charged
the maximum. This check can be done either at 
the EOD or when the user checks in next time.
For now we will deduce when the user checks-in
again.

Let's start with the simplest journey I can make,
i.e. Anywhere in Zone 1 - £2.50, from Holborn 
station to Earl’s Court station.

Let's assume I have the minimum balance.

The story would be - As a user I should be able
to travel from Holborn station to Earl's Court
station, given I have minimum balance in my card.






