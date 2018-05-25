package test.andigital.sofianesadi.entities;

import java.util.Objects;

/**
 * Number entity
 */
public class NumberEntity {
    private Long id;
    private String ddi;
    private CustomerEntity customerEntity;
    private NumberStatus status;

    /**
     *
     * @param id The number ID
     * @param ddi The number DDI (full number string)
     * @param customerEntity  The customer possessing the number
     * @param status Is the number ACTIVATED or DESACTIVATED ?
     */
    public NumberEntity(Long id, String ddi, CustomerEntity customerEntity, NumberStatus status) {
        this.id = id;
        this.ddi = ddi;
        this.customerEntity = customerEntity;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public NumberStatus getStatus() {
        return status;
    }

    public void setStatus(NumberStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberEntity that = (NumberEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ddi, that.ddi) &&
                Objects.equals(customerEntity, that.customerEntity) &&
                status == that.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, ddi, customerEntity, status);
    }
}
