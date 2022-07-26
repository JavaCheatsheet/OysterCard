# OysterCard Solution

The Oyster Card Problem - alefeducation.com

## Objective - Model Fare Card System

Demonstrate a user loading a main.java.com.modules.card with £30,
and taking the following trips, and then
viewing the balance.

- Tube Holborn to Earl’s Court
- 328 bus from Earl’s Court to Chelsea
- Tube Earl’s court to Hammersmith

When the user passes through the inward barrier
at the station, their oyster main.java.com.modules.card is charged
the maximum main.java.com.modules.fare.

When they pass out of the barrier at the exit
station, the main.java.com.modules.fare is calculated and the maximum
main.java.com.modules.fare transaction removed and replaced with the
real transaction (in this way, if the user doesn’t
swipe out, they are charged the maximum main.java.com.modules.fare).

All bus journeys are charged at the same price.
Any bus journey - £1.80

Station - Zone(s)
Holborn - 1
Earl’s Court - 1, 2
Wimbledon - 3
Hammersmith - 2

main.java.com.modules.transportation main.java.com.modules.fare
Anywhere in Zone 1 - £2.50
Any one zone outside zone 1 - £2.00
Any two zones including zone 1 - £3.00
Any two zones excluding zone 1 - £2.25
Any three zones - £3.20
Any bus journey - £1.80

### Analysis
What are the interacting entities?

Note - I am assuming myself as a traveller
who will be using these means of main.java.com.modules.transportation
travelling around different locations using
the main.java.com.modules.card.

For this the most important thing would be
the main.java.com.modules.card.
1. main.java.com.modules.card

Second would be locations
2. Location

Third, as the locations are divided into
zones based on which fares vary.
3. Zone

Also, important are the modes of transportations.
Tube (or Metro) and Buses are two services
people can use.
4. main.java.com.modules.transportation

Travelling from a location to another are defined
by which zone those locations fall while Tube is
used. But for buses, it remains the same for any
journey regardless of zones. By this I assume that
it does not matter if I travel 1km or 50km.

The first thing I need to do before being able
to travel is have enough balance in my main.java.com.modules.card to
be able to use any of the services, use the Tube
or the Bus.

The minimum amount that is needed while using
the Bus which is £1.8.

So the first feature of the system would be I
to should be able to load balance. The balance
would be deducted as per main.java.com.modules.fare.

Indeed, at the very first, I need to get a main.java.com.modules.card
but that is not the requirement at the moment.

The second feature of the system is calculating
the fair. This should be the core business logic.
And we will focus more here and start from here.

## Model Tube main.java.com.modules.fare System
The complex calculations is when I use the
Tube to travel. So first let's assume I can
only travel via Tube. And to design this
system we need understand what are the charges
and payment process.
"
When the user passes through the inward barrier
at the station, their oyster main.java.com.modules.card is charged
the maximum main.java.com.modules.fare.

When they pass out of the barrier at the exit
station, the main.java.com.modules.fare is calculated and the maximum
main.java.com.modules.fare transaction removed and replaced with the
real transaction (in this way, if the user doesn’t
swipe out, they are charged the maximum main.java.com.modules.fare).
"

From the given requirement, when ever I check
into the station, the main.java.com.modules.fare is set to maximum which
in this case should be £3.20 as fares provided -

Case - "Any three zones - £3.20"

So to use Tube, I must have minimum balance in my
main.java.com.modules.card. The checking is done while entering a station.
And If the balance is low I would not be able to
enter and use the Tube.

The main.java.com.modules.card is charged the actual amount after I
check out of the station. If I don't swipe the
main.java.com.modules.card while checking out, the main.java.com.modules.card will be charged
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
station, given I have minimum balance in my main.java.com.modules.card.


### Calculating main.java.com.modules.fare
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
The understanding is this  - main.java.com.modules.fare to travel
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
   than one main.java.com.modules.fare is possible for a given journey.

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

The main.java.com.modules.fare system is designed only to check Tube
travellers travelling in ascending order only.

**Note:**
**Tube does not go backward.**


## Enable Bus main.java.com.modules.fare
We will add bus main.java.com.modules.fare system.

`Any/All bus journey £1.80`

Bus travel just concerns checkin and checkout.
There is no specific detail provided for max
main.java.com.modules.fare for bus so as a convenience of the Customer,
if in case of the Customer forgets to checkout,
£1.80 will be charged in the next checkin.


## Add main.java.com.modules.card Service
As a Oyster main.java.com.modules.card holder, I should be able to
pay the fares of my journeys.

main.java.com.modules.card is subsystem what will keep track of
the transactions.

I can add money to my main.java.com.modules.card(manual) and the amount is
reduced as per travel (when the checkout is complete or
while checkin if did I not checkout last time, automated).


## Simulate Bus Checkin-Checkout

Bus has a device to read the main.java.com.modules.card.

Simulate "328 bus from Earl’s Court to Chelsea"

Steps:
1. Checkin in the bus
    1. Machine check if I have minimum balance 1.80
    2. If not, I can not checkin
    3. Set the status to checked-in the main.java.com.modules.card
2. Checkout
    1. Deduct the amount in the main.java.com.modules.card
    2. Set the status to checked-out in the main.java.com.modules.card

## Tube main.java.com.modules.fare Implementation Review
Response from Alef-
`You should consider Earl's Court both in zone 1
and 2, so your example for "Any one zone outside
zone 1" would be incorrect, same with the first
example for "Any two zones excluding zone 1".

On the last main.java.com.modules.fare, although it's correct, I just
wanted to emphasize that any three zones means
from anywhere to anywhere, it's like a fallback
main.java.com.modules.fare or the worst case scenario.`

Fixed an assumption and the failing test case
based on the response.


## Simulate Tube Checkin-Checkout
When I checkin, system just needs to know
where I checked in, which station.

Why station? Because unlike Bus, Tube main.java.com.modules.fare
is different based on Zones.

I am also assuming that devices understands
either the customer is checking-in or checking
-out. Otherwise, the logic would be complex.
For simplicity, I would like to think this way.

Also the possibility of checking-in from other
locations from station in Tube is ZERO. I can
only checkin from a station. ( SLA,SLO,

Note: Need to add these points while submitting
the solution.

But in case of BUS, there is only one device.
If I tap-in the main.java.com.modules.card second time when should
the system understand I checked-in or out?
For example, If I tap the main.java.com.modules.card twice consecutively
while I checkin, should the system assume that
I checkout? Should the system charge for that?
Or charge only when the Bus moves and to another
location? It depends upon the device that the
bus has for checking-in and out.

For now we assume that -
THE BUS HAS TWO DEVICES FOR CHECKIN AND OUT.


## Journey
Some point to note:
1. User can travel in the direction from Zone 1
   to Zone 3 (for now).
2. All the stations comes one after another.
3. There are separate devices or the device understands
   either the user checked-in or checked-out.


### Requirement Demo
Demonstrate a user loading a card with £30,
and taking the following trips, and then
viewing the balance.

1. Tube Holborn to Earl’s Court
2. 328 bus from Earl’s Court to Chelsea
3. Tube Earl’s court to Hammersmith

While implementing this there was an interesting
issue raised.

What if I didnot checkout of the Tube and checkin
on the Bus? And similarly, didn't checkout of bus
and checkin on Tube? Does the system need to store
such information? Well for now, we will make the
absense of checkout £3.20. This leads us to making
minimum balance on Bus £3.20 too.


## Execute CLI via Maven
The following list shows the most important Maven
lifecycle phases:
- validate – checks the correctness of the project
- compile – compiles the provided source code into
  binary artifacts
- test – executes unit tests
- package – packages compiled code into an archive
  file
- integration-test – executes additional tests,
  which require the packaging
- verify – checks if the package is valid
- install – installs the package file into the
  local Maven repository
- deploy – deploys the package file to a remote
  server or repository

Running the CLI
- Run package: `mvn exec:java`
- Another way to run the package: `java -jar target/OysterCard-1.0-SNAPSHOT.jar`
- Build: `mvn clean package`
- Run tests: `mvn clean test`


## Run Unit Test From CLI
Maven is used as build tool which also helps to
run unit tests easily.

- Run all tests: `mvn clean test`
- Run one test class: `mvn test -Dtest=TubeFareTest`
- Run two test classes: `mvn test -Dtest=TubeFareTest,JourneyTest`
- Run a specific test function: `mvn test -Dtest=TubeFareTest#givenMinimumBalance_TravelFromEarlsCourt_ToHammersmith`


## Containerize Application
Dockerfile is created to build the images in
multiple stages.
- Build Image: `sudo docker build . -t oystercard`
- Execute: `sudo docker run -it --rm oystercard:latest`


## Structural Refactoring
So we have a prototype functional and logics in
place. Let's get back to refactoring.

The primary models that has been identified are
1. Card
2. Fare
3. Transportation

### Card
id: Primary key

UUID: UUID - Card holders unique id

checkedin: Boolean

amount: Double
Is it good to use Double to represent money in
Java?

The BigDecimal is the most accurate way to do
arbitrary precision calculations.

`If you are using double you run into trouble
Mainly because of the fact, that double (Wrapper:
Double) is a double-precision 64-bit IEEE 754
floating point. It's not meant for keeping exact
decimal values.`

More information at Resource > BigDecimal.

CREATE DATABASE mydatabase CHARACTER SET utf8 COLLATE utf8_general_ci;

### Fare
Classes BusFare and TubeFare seems to have two
common behaviours:
1. Constructor
2. getFare

They also have the same properties:
- private String checkInStation;
- private String checkOutStation;
- private final String EMPTY_LOCATION

So these two classes can be merged via a parent
class Fare and context bounded via IFare interface.

Stations and Zones map is hardcoded. We might
want to provide a datasource. For now a CSV.

### Transportation
Similarly for transportation types, bus and tube,
their behaviours and properties can be combined
via Transport Class and context bounded via
ITransportInterface.




# Resource
- https://www.baeldung.com/maven
- https://www.baeldung.com/junit-run-from-command-line
- https://www.baeldung.com/executable-jar-with-maven
- https://www.baeldung.com/maven-local-repository
- https://docs.github.com/en/actions/automating-builds-and-tests/building-and-testing-java-with-maven
- https://www.javacodegeeks.com/2014/03/automated-bug-finding-with-git-bisect-and-mvn-test.html
- https://www.dev2qa.com/build-and-run-java-project-with-maven
- https://www.baeldung.com/maven-run-from-another-directory
- https://snyk.io/blog/best-practices-to-build-java-containers-with-docker
- https://docs.docker.com/language/java
- https://stackify.com/guide-docker-java
- https://dzone.com/articles/working-money-java
- https://github.com/JavaMoney/javamoney-examples/blob/master/web/javamoney-payment-cdi-event/src/main/java/org/javamoney/examples/cdi/payment/beans/PaymentBean.java
- https://docs.oracle.com/javaee/6/tutorial/doc/gkhic.html
- https://docs.oracle.com/javaee/6/tutorial/doc/gkhpa.html
- https://docs.oracle.com/javaee/6/tutorial/doc/gjbnr.html
- https://javaranch.com/journal/2003/07/MoneyInJava.html
- https://www.geeksforgeeks.org/banking-transaction-system-using-java


## Docker Permission
`docker ps`
`Got permission denied while trying to connect
to the Docker daemon socket at unix:///var/run/docker.sock
..dial unix /var/run/docker.sock: connect: permission denied`

Temporary resolve, might not work after system reboot.
`sudo setfacl --modify user:$USER:rw /var/run/docker.sock`

Permanent solution
`
sudo usermod -aG docker $USER
sudo reboot
`

## BigDecimal
The BigDecimal is the most accurate way to do
arbitrary precision calculations. In most business
applications, it is well worth the cost of the
overhead of working with objects for the added
accuracy you get with the BigDecimal class.

Constructors of BigDecimal
BigDecimal has four constructors but the one you
will want to use most is the one that takes a String.

If we ran our test program using the constructor that
takes a double, we would still get the wrong answer
if we used float constants:
`
BigDecimal a1 = new BigDecimal(8250325.12f);
BigDecimal b1 = new BigDecimal(4321456.31f);
`

The reason this doesn't work correctly is that the float
constants will undergo loss of precision before they are
sent to the constructor of the BigDecimal. Using the String
constructor will always allow the BigDecimal to represent
exactly the number you want.

Methods of BigDecimal
BigDecimal supports add, subtract, multiply, and divide.
The calculated scale for add and subtract is simply the
larger scale of the two numbers you are working with.
The calculated scale for multiply is the sum of the scale
of the two numbers.
The scale of the divide method is either the scale
you specify in the method or the scale of the current
BigDecimal object depending on which overloaded version
of the BigDecimal you are using.

The divide also requires that you specify a rounding
method. `ROUND_HALF_UP` is the standard arithmetic
rounding method used most often. Lets take a look at
this code fragment:
`
BigDecimal a1 = new BigDecimal("2");
BigDecimal b1 = new BigDecimal("3");
BigDecimal c1 = a1.divide(b1, 9, BigDecimal.ROUND_HALF_UP);
System.out.println(c1);
`
This will print: 0.666666667. Since we specified a
scale of 9, we get 9 digits after the decimal point.
Since we specified "BigDecimal.ROUND_HALF_UP," we
get the last digit mathematically rounded.

BigDecimal supports the use of the compareTo method
for comparing values. In most cases you will want
to use the compareTo even for equals comparisons
because the equals method considers two numbers with
the exact same value but different scales to be not
equal. In other words, the equals method considers
2, 2.0, 2.00, and 2.000 to be different numbers.
The compareTo will consider all of them to be equal


## Contexts & Dependency Injection (CDI) for Java
Java Enterprise Edition has many features that
really stand out. One of the best is the event
mechanism, which is part of the Contexts &
Dependency Injection for Java specification.

Events have been present in Java EE for a long
time. The design of the events mechanism is
extremely clean, and learning how to use events
is therefore very simple. This overview is aimed
at developers who are not familiar with the event
mechanism and want to get to know the basics.

Advanced features of CDI 2.0, like Asynchronous
events, are not covered. You will learn to:
- Fire specific events.
- Use event qualifier.
- Observe events fired during transactions.
- Configure even observer bean instantiation.

https://dzone.com/articles/an-overview-of-cdi-events

Note: CDI use case seems to be in Java Beans, that
technology is not used much nowadays. Need to research.
