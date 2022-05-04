# DAS-Management-Tracker

Java Android Mobile application for tracking/managing depression, anxiety, and stress in users.

## Usage:
Upon entry add a pin and select confirm pin at the bottom.
Swipe left to see diary entries or right to see pin analytics.

## Dev Information: 
### Internal Database Structures:
All data is kept local to the device.


#### Tables

#### Objects
##### DiaryEntry
```java
public class DiaryEntry  {
  private int ID;       // (primary key) unique id upon entry to the data base
  private String title; // user supplied title
  private String text;  // user supplied text
  private String date;  // date of entry format yyyy/mm/dd hh:mm:ss
  ...
}
```
##### Pin
```java
public class Pin  {
  private int id;       // (primary key) unique id upon entry to the data base
  private double x;     // x value of pin
  private double y;     // y value of pin
  private String date;  // date of entry format yyyy/mm/dd hh:mm:ss
  private int color;    // hex value of the associated color
  ...
}
```

## Issues
- [ ] confirm button on main page will not show up when marker is moved to anywhere on the screen.
- [ ] can't delete diary entries
- [ ] diary entries don't ask to save before exiting
