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

Transportation Fare
Anywhere in Zone 1 - £2.50
Any one zone outside zone 1 - £2.00
Any two zones including zone 1 - £3.00
Any two zones excluding zone 1 - £2.25
Any three zones - £3.20
Any bus journey - £1.80

### Analysis
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

## Model Tube Fare System
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

Case - "Any three zones - £3.20"

So to use Tube, I must have minimum balance in my
card. The checking is done while entering a station.
And If the balance is low I would not be able to
enter and use the Tube.

The card is charged the actual amount after I 
check out of the station. If I don't swipe the 
card while checking out, the card will be charged
the maximum. This check can be done either at 
the EOD or when the user checks in next time.
For now, we will deduce when the user checks-in
again, previous charge is calculated and reduced
, if any.

Let's start with the simplest journey I can make,
i.e. Anywhere in Zone 1 - £2.50, from Holborn 
station to Earl’s Court station.

Let's assume I have the minimum balance. Then I 
checkin.

The story would be - As a user, I should be able
to travel from Holborn station to Earl's Court
station, given I have minimum balance in my card.

### Calculating Fare
The charge rate is defined based on which zones
I am travelling to, from and to.

Cases to implement:
* Anywhere in Zone 1 £2.50
* Any one zone outside zone 1 £2.00
* Any two zones including zone 1 £3.00
* Any two zones excluding zone 1 £2.25
* Any three zones £3.20
* Any bus journey £1.80

Simple solution would be -
Check in which zone the stations are located?

### Case - "Any one zone outside zone 1 - £2.00"
This logic is not that clear so skipping to next
for now.

### Case - "Any two zones including zone 1 - £3.00"
Lets say I'm travelling from Wimbledon(3) to 
Holborn(1).

### Case - Any two zones excluding zone 1 - £2.25
Lets say, I'm travelling from Wimbledon(3) to
Hammersmith(2).

### Case - Any three zones - £3.20
Holborn - 1
Wimbledon - 3
Hammersmith - 2

For this case the assumptions I make:
- One Tube can pass through many zones,
sequentially. 

- Or I have to take another Tube in the same 
platform to go to another zone.

In any case, the final cost is charged once I 
check out of the station.

So the question is - 

How does the system know that I have travelled 
3 zones? 

The best possible understanding is that the 
Zones comes one after another, or are sequential. 

Probably this is the reason 1,2 and 3 numbers are 
given in the example.

Therefore, first, the platform must be a linear
connecting the Zones. For example, let's suppose 
I am in Zone 1 and to reach Zone three, I have to 
go through Zone 2, which makes logical sense. And 
I am in Zone 3, to get to Zone 1, I have the Tube 
takes me through Zone 2.

For the stations above, I am travelling from Zone 
1 to Zone 3.

I check in at Holborn station, Zone 1. Tube goes 
through Zone 2 passing along stations in this Zone.
Tube reaches Zone 3 and get off at Wimbledon station.

### Case - Any one zone outside zone 1
The understanding is this  - Fare to travel 
in any other zone (with in that Zone) except 
Zone 1 is £2.00.

From the provided information, we can take 
Zone 2 as an example.

Earl’s Court - 1, 2
Hammersmith - 2

I can travel from Earl's Court to Hammersmith
while being in Zone 2.

Earl's Court is also in Zone 1 and as well as 
Zone 2. As we have already mapped this station
in Zone 1, we have to rethink of the Zone-Station 
map logic and used data structure.

Previously HashMap was used for storing Station 
name mapped with Zone number, one on one mapping.

`
HashMap<String, Integer> zoneStation = new HashMap<>();
zoneStation.put("EarlsCourt", 1);
`

Problem - One Zone Can Multiple Stations. 
This problem should have been realized in the very 
beginning, which it was but left out to see how the
solution evolves to meet the requirements, the TDD
approach to adjusting to change.

Try using 
`Hashmap <Zone, List<Station>>` 
to solve the problem.

The existing functionality - 
`int[] getCheckInCheckOutZones()`

searches Zone from Station. Now as we have 
`Zone` as the key, we need to search all the 
Zones to get `Stations`. It is costly to 
iterate through all the Zones and check. 

I think it would be better to use
`Hashmap <Station, List<Zone>>`

**Pseudocode** 
Are checkin station and checkout stations 
in the same Zone?
If yes, and return the same Zone.
Else, return their respective Zones.

While solving the problems from this perspective,
`The case of Zone containing more than one Station`
it forced to reevaluate the entire implementation.

It even forced to question the nature of my first
belief that platform is linear and not circular.
I had questions if there would be many small 
distributed separate platforms. 

I requested clarification and got the answers -
1. Platform is Linear and NOT Circular.

2. Tube moves from one Zone to other from higher
to lower by its assigned number, i.e. Zones are
in ascending order and the train moves from low
to high (for now).
"Hence to reach a zone with a higher number you 
would go through the ones with lower number."

3. Choose among fares when they become ambiguous -
The system should favour the Customer where more 
than one fare is possible for a given journey.

Verify test cases.

The problem that was created after using the new
data structure was finding the checkin and checkout
stations. For the given data where only Earl’s Court 
are located in two Zones (1, 2) we can hard code it 
but if there are other stations as such or even more
the current implementation would not work.

In case of multiple stations with multiple Zones, 
It would be difficult to match the Zones and get
the rate. Or as the statement suggests to favour
the Customer, we choose the Zone with the lowest
rate.

As every solution has its constraints, we will assume
only Earl's Court belongs to two Zones and none other
station does (provided data).

After further reviewing, simple condition to check 
if checkout is Earlscourt and checkin is from the
stations in Zone 1 would be enough. So there is
minimum change in the code.
`
if ( checkOutStation == "Earlscourt"
    && zoneStation.get(checkInStation) < 2 )
    
    return new int[] { 1, 1 };
`

The fare system is designed only to check Tube
travellers travelling in ascending order only.

**Note:**
**Tube does not go backward.**

## Enable Bus Fare
We will add bus fare system.

`Any/All bus journey £1.80`

Bus travel just concerns checkin and checkout.
There is no specific detail provided for max
fare for bus so as a convenience of the Customer,
if in case of the Customer forgets to checkout,
£1.80 will be charged in the next checkin.


## Add Card Service
As a Oyster Card holder, I should be able to 
pay the fares of my journeys. 

Card is subsystem what will keep track of 
the transactions. 

I can add money to my card(manual) and the amount is
reduced as per travel (when the checkout is complete or
while checkin if did I not checkout last time, automated).

## Simulate Bus Checkin-Checkout

Bus has a device to read the card.

Simulate "328 bus from Earl’s Court to Chelsea"

Steps:
1. Checkin in the bus
   1. Machine check if I have minimum balance 1.80 
   2. If not, I can not checkin
   3. Set the status to checked-in the card
2. Checkout 
   1. Deduct the amount in the card
   2. Set the status to checked-out in the card

## Tube Fare Implementation Review
Response from Alef-
`You should consider Earl's Court both in zone 1
and 2, so your example for "Any one zone outside 
zone 1" would be incorrect, same with the first 
example for "Any two zones excluding zone 1". 

On the last fare, although it's correct, I just 
wanted to emphasize that any three zones means 
from anywhere to anywhere, it's like a fallback 
fare or the worst case scenario.`

Fixed an assumption and the failing test case 
based on the response.

