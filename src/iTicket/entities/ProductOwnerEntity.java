package iTicket.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
@DiscriminatorValue("PRODUCT_OWNER")
public class ProductOwnerEntity extends UserEntity {

    public ProductOwnerEntity() {}

    public ProductOwnerEntity(Timestamp dateOfBirth, String email, String lastName, String firstName, String password, String username) {

        super(dateOfBirth, email, lastName, firstName, password, username);

    }
}
