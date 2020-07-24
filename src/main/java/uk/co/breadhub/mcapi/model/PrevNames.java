package uk.co.breadhub.mcapi.model;

import javax.persistence.*;

@Entity
@Table(name = "prevNames")
public class PrevNames {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    private String uuid;

    private Long ChangedToAt;

    public PrevNames() {
    }

    public PrevNames(String Name, String uuid, Long ChangedToAt) {
        this.name = Name;
        this.uuid = uuid;
        this.ChangedToAt = ChangedToAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getChangedToAt() {
        return ChangedToAt;
    }

    public void setChangedToAt(Long changedToAt) {
        ChangedToAt = changedToAt;
    }
}
