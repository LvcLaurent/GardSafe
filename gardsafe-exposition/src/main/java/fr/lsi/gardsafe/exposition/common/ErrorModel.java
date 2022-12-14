package fr.lsi.gardsafe.exposition.common;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Generic Error out
 * 
 * @author Laurent SION
 *
 */
@ApiModel(description = "generic error out")
@Validated
public class ErrorModel {

  @JsonProperty("code")
  private String code;

  @JsonProperty("message")
  private String message;

  @JsonProperty("description")
  private String description;

  /**
   * @return the code
   */
  @ApiModelProperty(required = true, value = "error code")
  @NotNull
  public String getCode() {
    return code;
  }

  /**
   * 
   * @param code the code to set
   */
  public void setCode(final String code) {
    this.code = code;
  }

  /**
   * http reason
   * 
   * @return the message
   */
  @ApiModelProperty(required = true, value = "HTTP reason phrase")
  @NotNull
  @Size(max = 50)
  public String getMessage() {
    return message;
  }

  /**
   * @param message the message to set
   */
  public void setMessage(final String message) {
    this.message = message;
  }

  /**
   * description
   * 
   * @return the description
   */
  @ApiModelProperty(value = "description")
  @Size(max = 200)
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(final String description) {
    this.description = description;
  }

  private ErrorModel(final Builder builder) {
    code = builder.code;
    message = builder.message;
    description = builder.description;
  }

  public static class Builder {
    private String code;
    private String message;
    private String description;

    public Builder code(final String code) {
      this.code = code;
      return this;
    }

    public Builder message(final String message) {
      this.message = message;
      return this;
    }

    public Builder description(final String description) {
      this.description = description;
      return this;
    }

    public ErrorModel build() {
      return new ErrorModel(this);
    }
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if ((this.getClass() == obj.getClass())) {
      final ErrorModel other = (ErrorModel) obj;
      return new EqualsBuilder()//
          .append(code, other.code)//
          .append(message, other.message)//
          .append(description, other.description)//
          .isEquals();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)//
        .append(code)//
        .append(message)//
        .append(description)//
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)//
        .append("code", code)//
        .append("message", message)//
        .append("description", description)//
        .toString();
  }

}
