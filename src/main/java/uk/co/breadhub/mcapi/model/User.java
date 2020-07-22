package uk.co.breadhub.mcapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "uuid")
    private String uuid;

    /**
     * Example:
     * [
     * {"Name": "braddevans", "timestamp": 1595447740},
     * {"Name": "kinkyMan", "timestamp": 1497558780}
     * ]
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "UserPrevNames")
    @MapKeyColumn(name = "name")
    @Column(name = "timestamp")
    private Map<String, Integer> prevNames;

    public User() {}

    public User(String name, String uuid) {
        this.name = name;
        this.uuid = uuid.replace("-", "");
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Map<String, Integer> getPrevNames() {
        return prevNames;
    }

    public void setPrevNames(Map<String, Integer> prevNames) {
        this.prevNames = prevNames;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uuid='" + uuid + '\'' +
                ", prevNames=" + prevNames +
                '}';
    }
}
