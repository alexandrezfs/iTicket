package iTicket.entities;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@DiscriminatorValue("DEVELOPER")
public class DeveloperEntity extends UserEntity {

    public DeveloperEntity() {}

    public DeveloperEntity(Timestamp dateOfBirth, String email, String lastName, String firstName, String password, String username) {

        super(dateOfBirth, email, lastName, firstName, password, username);

    }

}
