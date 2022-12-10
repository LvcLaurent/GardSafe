package fr.lsi.gardsafe.domain.common.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Laurent SION
 *
 */
public abstract class AbstractBaseEntity implements Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = 3599571896428853661L;

  /**
   * Entity ID
   */
  @NotNull
  protected String id = UUID.randomUUID().toString();

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(final String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("AbstractBaseEntity [id=");
    builder.append(id);
    builder.append("]");
    return builder.toString();
  }

  @Override
  public int hashCode() {
    final int PRIME = 31;
    int result = 1;
    result = (PRIME * result) + (id == null ? 0 : id.hashCode());
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
    final AbstractBaseEntity other = (AbstractBaseEntity) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

}
