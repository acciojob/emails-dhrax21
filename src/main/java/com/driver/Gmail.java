package com.driver;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.tuple.Triple;

public class Gmail extends Email {
    int inboxCapacity;
    private List<Triple<Date, String, String>> Inbox;
    private List<Triple<Date, String, String>> Trash;

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.Inbox = new ArrayList();
        this.Trash = new ArrayList();
    }

    public void receiveMail(Date date, String sender, String message) {
        Triple email;
        if (this.Inbox.size() == this.inboxCapacity) {
            email = (Triple)this.Inbox.get(0);
            this.Inbox.remove(0);
            this.Trash.add(email);
        }

        email = Triple.of(date, sender, message);
        this.Inbox.add(email);
    }

    public void deleteMail(String message) {
        int index = -1;

        for(int i = 0; i < this.Inbox.size(); ++i) {
            if (message.equals(((Triple)this.Inbox.get(i)).getRight())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            this.Trash.add((Triple)this.Inbox.get(index));
            this.Inbox.remove(index);
        }

    }

    public String findLatestMessage() {
        return this.Inbox.isEmpty() ? null : (String)((Triple)this.Inbox.get(this.Inbox.size() - 1)).getRight();
    }

    public String findOldestMessage() {
        return this.Inbox.isEmpty() ? null : (String)((Triple)this.Inbox.get(0)).getRight();
    }

    public int findMailsBetweenDates(Date start, Date end) {
        int count = 0;

        for(int i = 0; i < this.Inbox.size(); ++i) {
            if (((Date)((Triple)this.Inbox.get(i)).getLeft()).compareTo(start) >= 0 && ((Date)((Triple)
                    this.Inbox.get(i)).getLeft()).compareTo(end) <= 0)
            {
                ++count;
            }
        }
        return count;
    }

    public int getInboxSize()
    {
        int size=this.Inbox.size();
        return size;
    }

    public int getTrashSize() {
        int size=this.Trash.size();
        return size;
    }

    public void emptyTrash() {
        this.Trash.clear();
    }

    public int getInboxCapacity() {
        return this.inboxCapacity;
    }
}
