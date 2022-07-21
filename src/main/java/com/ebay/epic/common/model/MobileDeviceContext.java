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
public class MobileDeviceContext extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -7375714149340428841L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"MobileDeviceContext\",\"namespace\":\"com.ebay.epic.common.model\",\"fields\":[{\"name\":\"formFactor\",\"type\":[\"null\",{\"type\":\"enum\",\"name\":\"FormFactor\",\"symbols\":[\"phone\",\"tablet\",\"watch\",\"desktop\"]}],\"default\":null},{\"name\":\"manufacturer\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"osVersion\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"model\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"networkCarrier\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"networkConnectionType\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"theme\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"countryId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<MobileDeviceContext> ENCODER =
      new BinaryMessageEncoder<MobileDeviceContext>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<MobileDeviceContext> DECODER =
      new BinaryMessageDecoder<MobileDeviceContext>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<MobileDeviceContext> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<MobileDeviceContext> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<MobileDeviceContext>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this MobileDeviceContext to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a MobileDeviceContext from a ByteBuffer. */
  public static MobileDeviceContext fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public com.ebay.epic.common.model.FormFactor formFactor;
  @Deprecated public java.lang.String manufacturer;
  @Deprecated public java.lang.String osVersion;
  @Deprecated public java.lang.String model;
  @Deprecated public java.lang.String networkCarrier;
  @Deprecated public java.lang.String networkConnectionType;
  @Deprecated public java.lang.String theme;
  @Deprecated public java.lang.String countryId;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public MobileDeviceContext() {}

  /**
   * All-args constructor.
   * @param formFactor The new value for formFactor
   * @param manufacturer The new value for manufacturer
   * @param osVersion The new value for osVersion
   * @param model The new value for model
   * @param networkCarrier The new value for networkCarrier
   * @param networkConnectionType The new value for networkConnectionType
   * @param theme The new value for theme
   * @param countryId The new value for countryId
   */
  public MobileDeviceContext(com.ebay.epic.common.model.FormFactor formFactor, java.lang.String manufacturer, java.lang.String osVersion, java.lang.String model, java.lang.String networkCarrier, java.lang.String networkConnectionType, java.lang.String theme, java.lang.String countryId) {
    this.formFactor = formFactor;
    this.manufacturer = manufacturer;
    this.osVersion = osVersion;
    this.model = model;
    this.networkCarrier = networkCarrier;
    this.networkConnectionType = networkConnectionType;
    this.theme = theme;
    this.countryId = countryId;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return formFactor;
    case 1: return manufacturer;
    case 2: return osVersion;
    case 3: return model;
    case 4: return networkCarrier;
    case 5: return networkConnectionType;
    case 6: return theme;
    case 7: return countryId;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: formFactor = (com.ebay.epic.common.model.FormFactor)value$; break;
    case 1: manufacturer = (java.lang.String)value$; break;
    case 2: osVersion = (java.lang.String)value$; break;
    case 3: model = (java.lang.String)value$; break;
    case 4: networkCarrier = (java.lang.String)value$; break;
    case 5: networkConnectionType = (java.lang.String)value$; break;
    case 6: theme = (java.lang.String)value$; break;
    case 7: countryId = (java.lang.String)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'formFactor' field.
   * @return The value of the 'formFactor' field.
   */
  public com.ebay.epic.common.model.FormFactor getFormFactor() {
    return formFactor;
  }

  /**
   * Sets the value of the 'formFactor' field.
   * @param value the value to set.
   */
  public void setFormFactor(com.ebay.epic.common.model.FormFactor value) {
    this.formFactor = value;
  }

  /**
   * Gets the value of the 'manufacturer' field.
   * @return The value of the 'manufacturer' field.
   */
  public java.lang.String getManufacturer() {
    return manufacturer;
  }

  /**
   * Sets the value of the 'manufacturer' field.
   * @param value the value to set.
   */
  public void setManufacturer(java.lang.String value) {
    this.manufacturer = value;
  }

  /**
   * Gets the value of the 'osVersion' field.
   * @return The value of the 'osVersion' field.
   */
  public java.lang.String getOsVersion() {
    return osVersion;
  }

  /**
   * Sets the value of the 'osVersion' field.
   * @param value the value to set.
   */
  public void setOsVersion(java.lang.String value) {
    this.osVersion = value;
  }

  /**
   * Gets the value of the 'model' field.
   * @return The value of the 'model' field.
   */
  public java.lang.String getModel() {
    return model;
  }

  /**
   * Sets the value of the 'model' field.
   * @param value the value to set.
   */
  public void setModel(java.lang.String value) {
    this.model = value;
  }

  /**
   * Gets the value of the 'networkCarrier' field.
   * @return The value of the 'networkCarrier' field.
   */
  public java.lang.String getNetworkCarrier() {
    return networkCarrier;
  }

  /**
   * Sets the value of the 'networkCarrier' field.
   * @param value the value to set.
   */
  public void setNetworkCarrier(java.lang.String value) {
    this.networkCarrier = value;
  }

  /**
   * Gets the value of the 'networkConnectionType' field.
   * @return The value of the 'networkConnectionType' field.
   */
  public java.lang.String getNetworkConnectionType() {
    return networkConnectionType;
  }

  /**
   * Sets the value of the 'networkConnectionType' field.
   * @param value the value to set.
   */
  public void setNetworkConnectionType(java.lang.String value) {
    this.networkConnectionType = value;
  }

  /**
   * Gets the value of the 'theme' field.
   * @return The value of the 'theme' field.
   */
  public java.lang.String getTheme() {
    return theme;
  }

  /**
   * Sets the value of the 'theme' field.
   * @param value the value to set.
   */
  public void setTheme(java.lang.String value) {
    this.theme = value;
  }

  /**
   * Gets the value of the 'countryId' field.
   * @return The value of the 'countryId' field.
   */
  public java.lang.String getCountryId() {
    return countryId;
  }

  /**
   * Sets the value of the 'countryId' field.
   * @param value the value to set.
   */
  public void setCountryId(java.lang.String value) {
    this.countryId = value;
  }

  /**
   * Creates a new MobileDeviceContext RecordBuilder.
   * @return A new MobileDeviceContext RecordBuilder
   */
  public static com.ebay.epic.common.model.MobileDeviceContext.Builder newBuilder() {
    return new com.ebay.epic.common.model.MobileDeviceContext.Builder();
  }

  /**
   * Creates a new MobileDeviceContext RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new MobileDeviceContext RecordBuilder
   */
  public static com.ebay.epic.common.model.MobileDeviceContext.Builder newBuilder(com.ebay.epic.common.model.MobileDeviceContext.Builder other) {
    return new com.ebay.epic.common.model.MobileDeviceContext.Builder(other);
  }

  /**
   * Creates a new MobileDeviceContext RecordBuilder by copying an existing MobileDeviceContext instance.
   * @param other The existing instance to copy.
   * @return A new MobileDeviceContext RecordBuilder
   */
  public static com.ebay.epic.common.model.MobileDeviceContext.Builder newBuilder(com.ebay.epic.common.model.MobileDeviceContext other) {
    return new com.ebay.epic.common.model.MobileDeviceContext.Builder(other);
  }

  /**
   * RecordBuilder for MobileDeviceContext instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<MobileDeviceContext>
    implements org.apache.avro.data.RecordBuilder<MobileDeviceContext> {

    private com.ebay.epic.common.model.FormFactor formFactor;
    private java.lang.String manufacturer;
    private java.lang.String osVersion;
    private java.lang.String model;
    private java.lang.String networkCarrier;
    private java.lang.String networkConnectionType;
    private java.lang.String theme;
    private java.lang.String countryId;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.ebay.epic.common.model.MobileDeviceContext.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.formFactor)) {
        this.formFactor = data().deepCopy(fields()[0].schema(), other.formFactor);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.manufacturer)) {
        this.manufacturer = data().deepCopy(fields()[1].schema(), other.manufacturer);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.osVersion)) {
        this.osVersion = data().deepCopy(fields()[2].schema(), other.osVersion);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.model)) {
        this.model = data().deepCopy(fields()[3].schema(), other.model);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.networkCarrier)) {
        this.networkCarrier = data().deepCopy(fields()[4].schema(), other.networkCarrier);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.networkConnectionType)) {
        this.networkConnectionType = data().deepCopy(fields()[5].schema(), other.networkConnectionType);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.theme)) {
        this.theme = data().deepCopy(fields()[6].schema(), other.theme);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.countryId)) {
        this.countryId = data().deepCopy(fields()[7].schema(), other.countryId);
        fieldSetFlags()[7] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing MobileDeviceContext instance
     * @param other The existing instance to copy.
     */
    private Builder(com.ebay.epic.common.model.MobileDeviceContext other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.formFactor)) {
        this.formFactor = data().deepCopy(fields()[0].schema(), other.formFactor);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.manufacturer)) {
        this.manufacturer = data().deepCopy(fields()[1].schema(), other.manufacturer);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.osVersion)) {
        this.osVersion = data().deepCopy(fields()[2].schema(), other.osVersion);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.model)) {
        this.model = data().deepCopy(fields()[3].schema(), other.model);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.networkCarrier)) {
        this.networkCarrier = data().deepCopy(fields()[4].schema(), other.networkCarrier);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.networkConnectionType)) {
        this.networkConnectionType = data().deepCopy(fields()[5].schema(), other.networkConnectionType);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.theme)) {
        this.theme = data().deepCopy(fields()[6].schema(), other.theme);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.countryId)) {
        this.countryId = data().deepCopy(fields()[7].schema(), other.countryId);
        fieldSetFlags()[7] = true;
      }
    }

    /**
      * Gets the value of the 'formFactor' field.
      * @return The value.
      */
    public com.ebay.epic.common.model.FormFactor getFormFactor() {
      return formFactor;
    }

    /**
      * Sets the value of the 'formFactor' field.
      * @param value The value of 'formFactor'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.MobileDeviceContext.Builder setFormFactor(com.ebay.epic.common.model.FormFactor value) {
      validate(fields()[0], value);
      this.formFactor = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'formFactor' field has been set.
      * @return True if the 'formFactor' field has been set, false otherwise.
      */
    public boolean hasFormFactor() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'formFactor' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.MobileDeviceContext.Builder clearFormFactor() {
      formFactor = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'manufacturer' field.
      * @return The value.
      */
    public java.lang.String getManufacturer() {
      return manufacturer;
    }

    /**
      * Sets the value of the 'manufacturer' field.
      * @param value The value of 'manufacturer'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.MobileDeviceContext.Builder setManufacturer(java.lang.String value) {
      validate(fields()[1], value);
      this.manufacturer = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'manufacturer' field has been set.
      * @return True if the 'manufacturer' field has been set, false otherwise.
      */
    public boolean hasManufacturer() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'manufacturer' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.MobileDeviceContext.Builder clearManufacturer() {
      manufacturer = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'osVersion' field.
      * @return The value.
      */
    public java.lang.String getOsVersion() {
      return osVersion;
    }

    /**
      * Sets the value of the 'osVersion' field.
      * @param value The value of 'osVersion'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.MobileDeviceContext.Builder setOsVersion(java.lang.String value) {
      validate(fields()[2], value);
      this.osVersion = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'osVersion' field has been set.
      * @return True if the 'osVersion' field has been set, false otherwise.
      */
    public boolean hasOsVersion() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'osVersion' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.MobileDeviceContext.Builder clearOsVersion() {
      osVersion = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'model' field.
      * @return The value.
      */
    public java.lang.String getModel() {
      return model;
    }

    /**
      * Sets the value of the 'model' field.
      * @param value The value of 'model'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.MobileDeviceContext.Builder setModel(java.lang.String value) {
      validate(fields()[3], value);
      this.model = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'model' field has been set.
      * @return True if the 'model' field has been set, false otherwise.
      */
    public boolean hasModel() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'model' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.MobileDeviceContext.Builder clearModel() {
      model = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'networkCarrier' field.
      * @return The value.
      */
    public java.lang.String getNetworkCarrier() {
      return networkCarrier;
    }

    /**
      * Sets the value of the 'networkCarrier' field.
      * @param value The value of 'networkCarrier'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.MobileDeviceContext.Builder setNetworkCarrier(java.lang.String value) {
      validate(fields()[4], value);
      this.networkCarrier = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'networkCarrier' field has been set.
      * @return True if the 'networkCarrier' field has been set, false otherwise.
      */
    public boolean hasNetworkCarrier() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'networkCarrier' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.MobileDeviceContext.Builder clearNetworkCarrier() {
      networkCarrier = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'networkConnectionType' field.
      * @return The value.
      */
    public java.lang.String getNetworkConnectionType() {
      return networkConnectionType;
    }

    /**
      * Sets the value of the 'networkConnectionType' field.
      * @param value The value of 'networkConnectionType'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.MobileDeviceContext.Builder setNetworkConnectionType(java.lang.String value) {
      validate(fields()[5], value);
      this.networkConnectionType = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'networkConnectionType' field has been set.
      * @return True if the 'networkConnectionType' field has been set, false otherwise.
      */
    public boolean hasNetworkConnectionType() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'networkConnectionType' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.MobileDeviceContext.Builder clearNetworkConnectionType() {
      networkConnectionType = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'theme' field.
      * @return The value.
      */
    public java.lang.String getTheme() {
      return theme;
    }

    /**
      * Sets the value of the 'theme' field.
      * @param value The value of 'theme'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.MobileDeviceContext.Builder setTheme(java.lang.String value) {
      validate(fields()[6], value);
      this.theme = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'theme' field has been set.
      * @return True if the 'theme' field has been set, false otherwise.
      */
    public boolean hasTheme() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'theme' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.MobileDeviceContext.Builder clearTheme() {
      theme = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'countryId' field.
      * @return The value.
      */
    public java.lang.String getCountryId() {
      return countryId;
    }

    /**
      * Sets the value of the 'countryId' field.
      * @param value The value of 'countryId'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.MobileDeviceContext.Builder setCountryId(java.lang.String value) {
      validate(fields()[7], value);
      this.countryId = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'countryId' field has been set.
      * @return True if the 'countryId' field has been set, false otherwise.
      */
    public boolean hasCountryId() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'countryId' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.MobileDeviceContext.Builder clearCountryId() {
      countryId = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public MobileDeviceContext build() {
      try {
        MobileDeviceContext record = new MobileDeviceContext();
        record.formFactor = fieldSetFlags()[0] ? this.formFactor : (com.ebay.epic.common.model.FormFactor) defaultValue(fields()[0]);
        record.manufacturer = fieldSetFlags()[1] ? this.manufacturer : (java.lang.String) defaultValue(fields()[1]);
        record.osVersion = fieldSetFlags()[2] ? this.osVersion : (java.lang.String) defaultValue(fields()[2]);
        record.model = fieldSetFlags()[3] ? this.model : (java.lang.String) defaultValue(fields()[3]);
        record.networkCarrier = fieldSetFlags()[4] ? this.networkCarrier : (java.lang.String) defaultValue(fields()[4]);
        record.networkConnectionType = fieldSetFlags()[5] ? this.networkConnectionType : (java.lang.String) defaultValue(fields()[5]);
        record.theme = fieldSetFlags()[6] ? this.theme : (java.lang.String) defaultValue(fields()[6]);
        record.countryId = fieldSetFlags()[7] ? this.countryId : (java.lang.String) defaultValue(fields()[7]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<MobileDeviceContext>
    WRITER$ = (org.apache.avro.io.DatumWriter<MobileDeviceContext>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<MobileDeviceContext>
    READER$ = (org.apache.avro.io.DatumReader<MobileDeviceContext>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}