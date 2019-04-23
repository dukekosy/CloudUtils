
package org.cloudutils.queues.config;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_queueConfigDev extends queueConfigDev {

  private final String connectionString;

  private AutoValue_queueConfigDev(
      String connectionString) {
    this.connectionString = connectionString;
  }

  @Override
  public String connectionString() {
    return connectionString;
  }

  @Override
  public String toString() {
    return "queueConfigDev{"
        + "connectionString=" + connectionString
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof queueConfigDev) {
      queueConfigDev that = (queueConfigDev) o;
      return (this.connectionString.equals(that.connectionString()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.connectionString.hashCode();
    return h;
  }

  static final class Builder extends queueConfigDev.Builder {
    private String connectionString;
    Builder() {
    }
    @Override
    public queueConfigDev.Builder connectionString(String connectionString) {
      if (connectionString == null) {
        throw new NullPointerException("Null connectionString");
      }
      this.connectionString = connectionString;
      return this;
    }
    @Override
    public queueConfigDev build() {
      String missing = "";
      if (this.connectionString == null) {
        missing += " connectionString";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_queueConfigDev(
          this.connectionString);
    }
  }

}
