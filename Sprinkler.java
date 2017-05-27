package com.nokia.entities;

public class Sprinkler {
 private Room room;
 private boolean on; 
public boolean isOn() {
	return on;
}

public void setOn(boolean on) {
	this.on = on;
}

public Room getRoom() {
	return room;
}

public void setRoom(Room room) {
	this.room = room;
}
}
