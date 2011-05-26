package model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author rsamoylov
 */
@Entity
@Table(name = "RES")
@PrimaryKeyJoinColumn(name = "resource_id", referencedColumnName = "resource_id")
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
