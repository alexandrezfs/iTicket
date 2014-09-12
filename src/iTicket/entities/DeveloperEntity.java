package iTicket.entities;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import java.sql.Timestamp;

@Entity
@DiscriminatorValue("DEVELOPER")
public class DeveloperEntity extends UserEntity {

    public DeveloperEntity() {}

    public DeveloperEntity(Timestamp dateOfBirth, String email, String lastName, String firstName, String password, String username) {

        super(dateOfBirth, email, lastName, firstName, password, username);

    }

}
