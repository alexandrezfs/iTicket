package iTicket.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PRODUCT_OWNER")
public class ProductOwnerEntity extends UserEntity {

    public ProductOwnerEntity() {}

}
