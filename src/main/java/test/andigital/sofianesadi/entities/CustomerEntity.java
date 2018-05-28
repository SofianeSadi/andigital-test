package test.andigital.sofianesadi.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * Customer entity
 */
public class CustomerEntity implements Serializable {
    private String id;

    public CustomerEntity() {
    }

    public CustomerEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id=" + id +
                '}';
    }
}
