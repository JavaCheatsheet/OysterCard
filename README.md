# OysterCard

Some points to note.

### Assumption
1. All the stations comes one after another in an
   ascending order.
2. Tube platform is linear, not circular, i.e. start
   does not connect with end.

### Implementation
1. User can travel in the one direction from Zone 1
   to Zone 3.
2. All the stations and zones are hardcoded.
3. Application does not take any input. A CLI based
   application runs.

### Next version features
1. Bi-directional travel.
2. Provide external data source for zones and stations.
3. As the nature of system will have huge volume of
   transactions every minutes, must consider scalability,
   reliability as design goals. Probably use threading
   at system level and message based distributed architecture.
4. Use Architectural Decision Records to track system decisions.
   https://adr.github.io
   https://github.com/adoble/adr-j

# Documentation
Software Development Lifecycle (SDLC) is documented
in `sdlc` folder, named as per stages.


To run the application view:
sdlc > 4.implemention > Development.md

For analysis, design and development processes,
view: sdlc > 2. analysis.. > Test-Driven..md