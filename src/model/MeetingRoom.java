package model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author rsamoylov
 */
@Entity
public class MeetingRoom extends Resource
{
    public MeetingRoom()
    {
        super();
    }

    public MeetingRoom(String name)
    {
        super(name);
    }


}
