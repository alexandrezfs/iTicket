package iTicket.entities;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("DEVELOPER")
public class DeveloperEntity extends UserEntity {

    public DeveloperEntity() {}

}
