package backend.model;

/**
 * Created by dogaro on 11/07/2016.
 */
import java.util.UUID;



/**
 * @author Dogar Octavian
 * Abstract class that implements the use of random UUID in 
 * each inheriting entities: {@link AbstractModel} also inherited by {@link User}
 */
public abstract class AbstractModel {

    private String uuid;

    public String getUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString().substring(0,4);
        }
        return uuid;
    }

    public void setUuid(String uuid){

        this.uuid = uuid;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((getUuid() == null) ? 0 : getUuid().hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractModel other = (AbstractModel) obj;
        if (getUuid() == null) {
            if (other.getUuid() != null) {
                return false;
            }
        } else if (!getUuid().equals(other.getUuid())) {
            return false;
        }
        return true;
    }

}