/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.ebay.epic.common.model;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Activity extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 6222888764303614089L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Activity\",\"namespace\":\"com.ebay.epic.common.model\",\"fields\":[{\"name\":\"timestamp\",\"type\":\"long\"},{\"name\":\"duration\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"ratio\",\"type\":[\"null\",\"double\"],\"default\":null},{\"name\":\"viewportWidth\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"viewportHeight\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"category\",\"type\":{\"type\":\"enum\",\"name\":\"ActivityCategory\",\"symbols\":[\"Impression\",\"Click\"]}},{\"name\":\"type\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"referer\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"details\",\"type\":[\"null\",{\"type\":\"map\",\"values\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"avro.java.string\":\"String\"}],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Activity> ENCODER =
      new BinaryMessageEncoder<Activity>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Activity> DECODER =
      new BinaryMessageDecoder<Activity>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Activity> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Activity> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Activity>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Activity to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Activity from a ByteBuffer. */
  public static Activity fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public long timestamp;
  @Deprecated public java.lang.Long duration;
  @Deprecated public java.lang.Double ratio;
  @Deprecated public java.lang.Long viewportWidth;
  @Deprecated public java.lang.Long viewportHeight;
  @Deprecated public com.ebay.epic.common.model.ActivityCategory category;
  @Deprecated public java.lang.String type;
  @Deprecated public java.lang.String referer;
  @Deprecated public java.util.Map<java.lang.String,java.lang.String> details;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Activity() {}

  /**
   * All-args constructor.
   * @param timestamp The new value for timestamp
   * @param duration The new value for duration
   * @param ratio The new value for ratio
   * @param viewportWidth The new value for viewportWidth
   * @param viewportHeight The new value for viewportHeight
   * @param category The new value for category
   * @param type The new value for type
   * @param referer The new value for referer
   * @param details The new value for details
   */
  public Activity(java.lang.Long timestamp, java.lang.Long duration, java.lang.Double ratio, java.lang.Long viewportWidth, java.lang.Long viewportHeight, com.ebay.epic.common.model.ActivityCategory category, java.lang.String type, java.lang.String referer, java.util.Map<java.lang.String,java.lang.String> details) {
    this.timestamp = timestamp;
    this.duration = duration;
    this.ratio = ratio;
    this.viewportWidth = viewportWidth;
    this.viewportHeight = viewportHeight;
    this.category = category;
    this.type = type;
    this.referer = referer;
    this.details = details;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return timestamp;
    case 1: return duration;
    case 2: return ratio;
    case 3: return viewportWidth;
    case 4: return viewportHeight;
    case 5: return category;
    case 6: return type;
    case 7: return referer;
    case 8: return details;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: timestamp = (java.lang.Long)value$; break;
    case 1: duration = (java.lang.Long)value$; break;
    case 2: ratio = (java.lang.Double)value$; break;
    case 3: viewportWidth = (java.lang.Long)value$; break;
    case 4: viewportHeight = (java.lang.Long)value$; break;
    case 5: category = (com.ebay.epic.common.model.ActivityCategory)value$; break;
    case 6: type = (java.lang.String)value$; break;
    case 7: referer = (java.lang.String)value$; break;
    case 8: details = (java.util.Map<java.lang.String,java.lang.String>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'timestamp' field.
   * @return The value of the 'timestamp' field.
   */
  public java.lang.Long getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the value of the 'timestamp' field.
   * @param value the value to set.
   */
  public void setTimestamp(java.lang.Long value) {
    this.timestamp = value;
  }

  /**
   * Gets the value of the 'duration' field.
   * @return The value of the 'duration' field.
   */
  public java.lang.Long getDuration() {
    return duration;
  }

  /**
   * Sets the value of the 'duration' field.
   * @param value the value to set.
   */
  public void setDuration(java.lang.Long value) {
    this.duration = value;
  }

  /**
   * Gets the value of the 'ratio' field.
   * @return The value of the 'ratio' field.
   */
  public java.lang.Double getRatio() {
    return ratio;
  }

  /**
   * Sets the value of the 'ratio' field.
   * @param value the value to set.
   */
  public void setRatio(java.lang.Double value) {
    this.ratio = value;
  }

  /**
   * Gets the value of the 'viewportWidth' field.
   * @return The value of the 'viewportWidth' field.
   */
  public java.lang.Long getViewportWidth() {
    return viewportWidth;
  }

  /**
   * Sets the value of the 'viewportWidth' field.
   * @param value the value to set.
   */
  public void setViewportWidth(java.lang.Long value) {
    this.viewportWidth = value;
  }

  /**
   * Gets the value of the 'viewportHeight' field.
   * @return The value of the 'viewportHeight' field.
   */
  public java.lang.Long getViewportHeight() {
    return viewportHeight;
  }

  /**
   * Sets the value of the 'viewportHeight' field.
   * @param value the value to set.
   */
  public void setViewportHeight(java.lang.Long value) {
    this.viewportHeight = value;
  }

  /**
   * Gets the value of the 'category' field.
   * @return The value of the 'category' field.
   */
  public com.ebay.epic.common.model.ActivityCategory getCategory() {
    return category;
  }

  /**
   * Sets the value of the 'category' field.
   * @param value the value to set.
   */
  public void setCategory(com.ebay.epic.common.model.ActivityCategory value) {
    this.category = value;
  }

  /**
   * Gets the value of the 'type' field.
   * @return The value of the 'type' field.
   */
  public java.lang.String getType() {
    return type;
  }

  /**
   * Sets the value of the 'type' field.
   * @param value the value to set.
   */
  public void setType(java.lang.String value) {
    this.type = value;
  }

  /**
   * Gets the value of the 'referer' field.
   * @return The value of the 'referer' field.
   */
  public java.lang.String getReferer() {
    return referer;
  }

  /**
   * Sets the value of the 'referer' field.
   * @param value the value to set.
   */
  public void setReferer(java.lang.String value) {
    this.referer = value;
  }

  /**
   * Gets the value of the 'details' field.
   * @return The value of the 'details' field.
   */
  public java.util.Map<java.lang.String,java.lang.String> getDetails() {
    return details;
  }

  /**
   * Sets the value of the 'details' field.
   * @param value the value to set.
   */
  public void setDetails(java.util.Map<java.lang.String,java.lang.String> value) {
    this.details = value;
  }

  /**
   * Creates a new Activity RecordBuilder.
   * @return A new Activity RecordBuilder
   */
  public static com.ebay.epic.common.model.Activity.Builder newBuilder() {
    return new com.ebay.epic.common.model.Activity.Builder();
  }

  /**
   * Creates a new Activity RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Activity RecordBuilder
   */
  public static com.ebay.epic.common.model.Activity.Builder newBuilder(com.ebay.epic.common.model.Activity.Builder other) {
    return new com.ebay.epic.common.model.Activity.Builder(other);
  }

  /**
   * Creates a new Activity RecordBuilder by copying an existing Activity instance.
   * @param other The existing instance to copy.
   * @return A new Activity RecordBuilder
   */
  public static com.ebay.epic.common.model.Activity.Builder newBuilder(com.ebay.epic.common.model.Activity other) {
    return new com.ebay.epic.common.model.Activity.Builder(other);
  }

  /**
   * RecordBuilder for Activity instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Activity>
    implements org.apache.avro.data.RecordBuilder<Activity> {

    private long timestamp;
    private java.lang.Long duration;
    private java.lang.Double ratio;
    private java.lang.Long viewportWidth;
    private java.lang.Long viewportHeight;
    private com.ebay.epic.common.model.ActivityCategory category;
    private java.lang.String type;
    private java.lang.String referer;
    private java.util.Map<java.lang.String,java.lang.String> details;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.ebay.epic.common.model.Activity.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[0].schema(), other.timestamp);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.duration)) {
        this.duration = data().deepCopy(fields()[1].schema(), other.duration);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.ratio)) {
        this.ratio = data().deepCopy(fields()[2].schema(), other.ratio);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.viewportWidth)) {
        this.viewportWidth = data().deepCopy(fields()[3].schema(), other.viewportWidth);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.viewportHeight)) {
        this.viewportHeight = data().deepCopy(fields()[4].schema(), other.viewportHeight);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.category)) {
        this.category = data().deepCopy(fields()[5].schema(), other.category);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.type)) {
        this.type = data().deepCopy(fields()[6].schema(), other.type);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.referer)) {
        this.referer = data().deepCopy(fields()[7].schema(), other.referer);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.details)) {
        this.details = data().deepCopy(fields()[8].schema(), other.details);
        fieldSetFlags()[8] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Activity instance
     * @param other The existing instance to copy.
     */
    private Builder(com.ebay.epic.common.model.Activity other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[0].schema(), other.timestamp);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.duration)) {
        this.duration = data().deepCopy(fields()[1].schema(), other.duration);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.ratio)) {
        this.ratio = data().deepCopy(fields()[2].schema(), other.ratio);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.viewportWidth)) {
        this.viewportWidth = data().deepCopy(fields()[3].schema(), other.viewportWidth);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.viewportHeight)) {
        this.viewportHeight = data().deepCopy(fields()[4].schema(), other.viewportHeight);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.category)) {
        this.category = data().deepCopy(fields()[5].schema(), other.category);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.type)) {
        this.type = data().deepCopy(fields()[6].schema(), other.type);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.referer)) {
        this.referer = data().deepCopy(fields()[7].schema(), other.referer);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.details)) {
        this.details = data().deepCopy(fields()[8].schema(), other.details);
        fieldSetFlags()[8] = true;
      }
    }

    /**
      * Gets the value of the 'timestamp' field.
      * @return The value.
      */
    public java.lang.Long getTimestamp() {
      return timestamp;
    }

    /**
      * Sets the value of the 'timestamp' field.
      * @param value The value of 'timestamp'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Activity.Builder setTimestamp(long value) {
      validate(fields()[0], value);
      this.timestamp = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'timestamp' field has been set.
      * @return True if the 'timestamp' field has been set, false otherwise.
      */
    public boolean hasTimestamp() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'timestamp' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Activity.Builder clearTimestamp() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'duration' field.
      * @return The value.
      */
    public java.lang.Long getDuration() {
      return duration;
    }

    /**
      * Sets the value of the 'duration' field.
      * @param value The value of 'duration'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Activity.Builder setDuration(java.lang.Long value) {
      validate(fields()[1], value);
      this.duration = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'duration' field has been set.
      * @return True if the 'duration' field has been set, false otherwise.
      */
    public boolean hasDuration() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'duration' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Activity.Builder clearDuration() {
      duration = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'ratio' field.
      * @return The value.
      */
    public java.lang.Double getRatio() {
      return ratio;
    }

    /**
      * Sets the value of the 'ratio' field.
      * @param value The value of 'ratio'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Activity.Builder setRatio(java.lang.Double value) {
      validate(fields()[2], value);
      this.ratio = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'ratio' field has been set.
      * @return True if the 'ratio' field has been set, false otherwise.
      */
    public boolean hasRatio() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'ratio' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Activity.Builder clearRatio() {
      ratio = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'viewportWidth' field.
      * @return The value.
      */
    public java.lang.Long getViewportWidth() {
      return viewportWidth;
    }

    /**
      * Sets the value of the 'viewportWidth' field.
      * @param value The value of 'viewportWidth'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Activity.Builder setViewportWidth(java.lang.Long value) {
      validate(fields()[3], value);
      this.viewportWidth = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'viewportWidth' field has been set.
      * @return True if the 'viewportWidth' field has been set, false otherwise.
      */
    public boolean hasViewportWidth() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'viewportWidth' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Activity.Builder clearViewportWidth() {
      viewportWidth = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'viewportHeight' field.
      * @return The value.
      */
    public java.lang.Long getViewportHeight() {
      return viewportHeight;
    }

    /**
      * Sets the value of the 'viewportHeight' field.
      * @param value The value of 'viewportHeight'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Activity.Builder setViewportHeight(java.lang.Long value) {
      validate(fields()[4], value);
      this.viewportHeight = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'viewportHeight' field has been set.
      * @return True if the 'viewportHeight' field has been set, false otherwise.
      */
    public boolean hasViewportHeight() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'viewportHeight' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Activity.Builder clearViewportHeight() {
      viewportHeight = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'category' field.
      * @return The value.
      */
    public com.ebay.epic.common.model.ActivityCategory getCategory() {
      return category;
    }

    /**
      * Sets the value of the 'category' field.
      * @param value The value of 'category'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Activity.Builder setCategory(com.ebay.epic.common.model.ActivityCategory value) {
      validate(fields()[5], value);
      this.category = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'category' field has been set.
      * @return True if the 'category' field has been set, false otherwise.
      */
    public boolean hasCategory() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'category' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Activity.Builder clearCategory() {
      category = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'type' field.
      * @return The value.
      */
    public java.lang.String getType() {
      return type;
    }

    /**
      * Sets the value of the 'type' field.
      * @param value The value of 'type'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Activity.Builder setType(java.lang.String value) {
      validate(fields()[6], value);
      this.type = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'type' field has been set.
      * @return True if the 'type' field has been set, false otherwise.
      */
    public boolean hasType() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'type' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Activity.Builder clearType() {
      type = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'referer' field.
      * @return The value.
      */
    public java.lang.String getReferer() {
      return referer;
    }

    /**
      * Sets the value of the 'referer' field.
      * @param value The value of 'referer'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Activity.Builder setReferer(java.lang.String value) {
      validate(fields()[7], value);
      this.referer = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'referer' field has been set.
      * @return True if the 'referer' field has been set, false otherwise.
      */
    public boolean hasReferer() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'referer' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Activity.Builder clearReferer() {
      referer = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /**
      * Gets the value of the 'details' field.
      * @return The value.
      */
    public java.util.Map<java.lang.String,java.lang.String> getDetails() {
      return details;
    }

    /**
      * Sets the value of the 'details' field.
      * @param value The value of 'details'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Activity.Builder setDetails(java.util.Map<java.lang.String,java.lang.String> value) {
      validate(fields()[8], value);
      this.details = value;
      fieldSetFlags()[8] = true;
      return this;
    }

    /**
      * Checks whether the 'details' field has been set.
      * @return True if the 'details' field has been set, false otherwise.
      */
    public boolean hasDetails() {
      return fieldSetFlags()[8];
    }


    /**
      * Clears the value of the 'details' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Activity.Builder clearDetails() {
      details = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Activity build() {
      try {
        Activity record = new Activity();
        record.timestamp = fieldSetFlags()[0] ? this.timestamp : (java.lang.Long) defaultValue(fields()[0]);
        record.duration = fieldSetFlags()[1] ? this.duration : (java.lang.Long) defaultValue(fields()[1]);
        record.ratio = fieldSetFlags()[2] ? this.ratio : (java.lang.Double) defaultValue(fields()[2]);
        record.viewportWidth = fieldSetFlags()[3] ? this.viewportWidth : (java.lang.Long) defaultValue(fields()[3]);
        record.viewportHeight = fieldSetFlags()[4] ? this.viewportHeight : (java.lang.Long) defaultValue(fields()[4]);
        record.category = fieldSetFlags()[5] ? this.category : (com.ebay.epic.common.model.ActivityCategory) defaultValue(fields()[5]);
        record.type = fieldSetFlags()[6] ? this.type : (java.lang.String) defaultValue(fields()[6]);
        record.referer = fieldSetFlags()[7] ? this.referer : (java.lang.String) defaultValue(fields()[7]);
        record.details = fieldSetFlags()[8] ? this.details : (java.util.Map<java.lang.String,java.lang.String>) defaultValue(fields()[8]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Activity>
    WRITER$ = (org.apache.avro.io.DatumWriter<Activity>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Activity>
    READER$ = (org.apache.avro.io.DatumReader<Activity>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
