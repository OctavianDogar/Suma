package backend.model;

/**
 * Created by dogaro on 11/07/2016.
 */
import java.io.Serializable;

/**
 * @author Dogar Octavian
 * Base entity class that implements the unique identifier at the level of each 
 * inheriting entity: {@link User}
 */
public class BaseEntity extends AbstractModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    public BaseEntity() {
        this(null);
    }

    public BaseEntity(final Long id) {
        super();
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}