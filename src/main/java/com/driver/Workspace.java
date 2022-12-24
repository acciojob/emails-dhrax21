package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar=new ArrayList<>(); // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId,Integer.MAX_VALUE);
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        this.calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        ArrayList<Pair<LocalTime,Integer>> endTime=new ArrayList<>();

        for(int i = 0; i < this.calendar.size(); ++i) {
            endTime.add(Pair.of(((Meeting)this.calendar.get(i)).getEndTime(), i));
        }
        Collections.sort(endTime);
        LocalTime time_limit = (LocalTime)((Pair)endTime.get(0)).getLeft();
        int count = 0;
        if (!endTime.isEmpty()) {
            ++count;
        }

        for(int j = 1; j < endTime.size(); ++j) {
            if (((Meeting)this.calendar.get((Integer)((Pair)endTime.get(j)).getRight())).getStartTime().compareTo(time_limit) > 0) {
                ++count;
                time_limit = (LocalTime)((Pair)endTime.get(j)).getLeft();
            }
        }
        return count;
    }
}
