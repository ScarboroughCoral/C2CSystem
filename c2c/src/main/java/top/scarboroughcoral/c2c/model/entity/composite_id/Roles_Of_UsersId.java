package top.scarboroughcoral.c2c.model.entity.composite_id;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

public class Roles_Of_UsersId implements Serializable {
    private Integer roleId;
    private Integer userId;
}
