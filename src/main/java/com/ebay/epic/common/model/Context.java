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
public class Context extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -5302579171841346328L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Context\",\"namespace\":\"com.ebay.epic.common.model\",\"fields\":[{\"name\":\"appId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"os\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"utcOffset\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"clientIp\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"userLang\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"userAgent\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"screenWidth\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"screenHeight\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"screenDPI\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"deviceInfo\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"MobileDeviceContext\",\"fields\":[{\"name\":\"formFactor\",\"type\":[\"null\",{\"type\":\"enum\",\"name\":\"FormFactor\",\"symbols\":[\"phone\",\"tablet\",\"watch\",\"desktop\"]}],\"default\":null},{\"name\":\"manufacturer\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"osVersion\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"model\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"networkCarrier\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"networkConnectionType\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"theme\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"countryId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null}]}],\"default\":null},{\"name\":\"others\",\"type\":[\"null\",{\"type\":\"map\",\"values\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"avro.java.string\":\"String\"}],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Context> ENCODER =
      new BinaryMessageEncoder<Context>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Context> DECODER =
      new BinaryMessageDecoder<Context>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Context> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Context> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Context>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Context to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Context from a ByteBuffer. */
  public static Context fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.String appId;
  @Deprecated public java.lang.String version;
  @Deprecated public java.lang.String os;
  @Deprecated public java.lang.String utcOffset;
  @Deprecated public java.lang.String clientIp;
  @Deprecated public java.lang.String userLang;
  @Deprecated public java.lang.String userAgent;
  @Deprecated public java.lang.Long screenWidth;
  @Deprecated public java.lang.Long screenHeight;
  @Deprecated public java.lang.Long screenDPI;
  @Deprecated public com.ebay.epic.common.model.MobileDeviceContext deviceInfo;
  @Deprecated public java.util.Map<java.lang.String,java.lang.String> others;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Context() {}

  /**
   * All-args constructor.
   * @param appId The new value for appId
   * @param version The new value for version
   * @param os The new value for os
   * @param utcOffset The new value for utcOffset
   * @param clientIp The new value for clientIp
   * @param userLang The new value for userLang
   * @param userAgent The new value for userAgent
   * @param screenWidth The new value for screenWidth
   * @param screenHeight The new value for screenHeight
   * @param screenDPI The new value for screenDPI
   * @param deviceInfo The new value for deviceInfo
   * @param others The new value for others
   */
  public Context(java.lang.String appId, java.lang.String version, java.lang.String os, java.lang.String utcOffset, java.lang.String clientIp, java.lang.String userLang, java.lang.String userAgent, java.lang.Long screenWidth, java.lang.Long screenHeight, java.lang.Long screenDPI, com.ebay.epic.common.model.MobileDeviceContext deviceInfo, java.util.Map<java.lang.String,java.lang.String> others) {
    this.appId = appId;
    this.version = version;
    this.os = os;
    this.utcOffset = utcOffset;
    this.clientIp = clientIp;
    this.userLang = userLang;
    this.userAgent = userAgent;
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
    this.screenDPI = screenDPI;
    this.deviceInfo = deviceInfo;
    this.others = others;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return appId;
    case 1: return version;
    case 2: return os;
    case 3: return utcOffset;
    case 4: return clientIp;
    case 5: return userLang;
    case 6: return userAgent;
    case 7: return screenWidth;
    case 8: return screenHeight;
    case 9: return screenDPI;
    case 10: return deviceInfo;
    case 11: return others;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: appId = (java.lang.String)value$; break;
    case 1: version = (java.lang.String)value$; break;
    case 2: os = (java.lang.String)value$; break;
    case 3: utcOffset = (java.lang.String)value$; break;
    case 4: clientIp = (java.lang.String)value$; break;
    case 5: userLang = (java.lang.String)value$; break;
    case 6: userAgent = (java.lang.String)value$; break;
    case 7: screenWidth = (java.lang.Long)value$; break;
    case 8: screenHeight = (java.lang.Long)value$; break;
    case 9: screenDPI = (java.lang.Long)value$; break;
    case 10: deviceInfo = (com.ebay.epic.common.model.MobileDeviceContext)value$; break;
    case 11: others = (java.util.Map<java.lang.String,java.lang.String>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'appId' field.
   * @return The value of the 'appId' field.
   */
  public java.lang.String getAppId() {
    return appId;
  }

  /**
   * Sets the value of the 'appId' field.
   * @param value the value to set.
   */
  public void setAppId(java.lang.String value) {
    this.appId = value;
  }

  /**
   * Gets the value of the 'version' field.
   * @return The value of the 'version' field.
   */
  public java.lang.String getVersion() {
    return version;
  }

  /**
   * Sets the value of the 'version' field.
   * @param value the value to set.
   */
  public void setVersion(java.lang.String value) {
    this.version = value;
  }

  /**
   * Gets the value of the 'os' field.
   * @return The value of the 'os' field.
   */
  public java.lang.String getOs() {
    return os;
  }

  /**
   * Sets the value of the 'os' field.
   * @param value the value to set.
   */
  public void setOs(java.lang.String value) {
    this.os = value;
  }

  /**
   * Gets the value of the 'utcOffset' field.
   * @return The value of the 'utcOffset' field.
   */
  public java.lang.String getUtcOffset() {
    return utcOffset;
  }

  /**
   * Sets the value of the 'utcOffset' field.
   * @param value the value to set.
   */
  public void setUtcOffset(java.lang.String value) {
    this.utcOffset = value;
  }

  /**
   * Gets the value of the 'clientIp' field.
   * @return The value of the 'clientIp' field.
   */
  public java.lang.String getClientIp() {
    return clientIp;
  }

  /**
   * Sets the value of the 'clientIp' field.
   * @param value the value to set.
   */
  public void setClientIp(java.lang.String value) {
    this.clientIp = value;
  }

  /**
   * Gets the value of the 'userLang' field.
   * @return The value of the 'userLang' field.
   */
  public java.lang.String getUserLang() {
    return userLang;
  }

  /**
   * Sets the value of the 'userLang' field.
   * @param value the value to set.
   */
  public void setUserLang(java.lang.String value) {
    this.userLang = value;
  }

  /**
   * Gets the value of the 'userAgent' field.
   * @return The value of the 'userAgent' field.
   */
  public java.lang.String getUserAgent() {
    return userAgent;
  }

  /**
   * Sets the value of the 'userAgent' field.
   * @param value the value to set.
   */
  public void setUserAgent(java.lang.String value) {
    this.userAgent = value;
  }

  /**
   * Gets the value of the 'screenWidth' field.
   * @return The value of the 'screenWidth' field.
   */
  public java.lang.Long getScreenWidth() {
    return screenWidth;
  }

  /**
   * Sets the value of the 'screenWidth' field.
   * @param value the value to set.
   */
  public void setScreenWidth(java.lang.Long value) {
    this.screenWidth = value;
  }

  /**
   * Gets the value of the 'screenHeight' field.
   * @return The value of the 'screenHeight' field.
   */
  public java.lang.Long getScreenHeight() {
    return screenHeight;
  }

  /**
   * Sets the value of the 'screenHeight' field.
   * @param value the value to set.
   */
  public void setScreenHeight(java.lang.Long value) {
    this.screenHeight = value;
  }

  /**
   * Gets the value of the 'screenDPI' field.
   * @return The value of the 'screenDPI' field.
   */
  public java.lang.Long getScreenDPI() {
    return screenDPI;
  }

  /**
   * Sets the value of the 'screenDPI' field.
   * @param value the value to set.
   */
  public void setScreenDPI(java.lang.Long value) {
    this.screenDPI = value;
  }

  /**
   * Gets the value of the 'deviceInfo' field.
   * @return The value of the 'deviceInfo' field.
   */
  public com.ebay.epic.common.model.MobileDeviceContext getDeviceInfo() {
    return deviceInfo;
  }

  /**
   * Sets the value of the 'deviceInfo' field.
   * @param value the value to set.
   */
  public void setDeviceInfo(com.ebay.epic.common.model.MobileDeviceContext value) {
    this.deviceInfo = value;
  }

  /**
   * Gets the value of the 'others' field.
   * @return The value of the 'others' field.
   */
  public java.util.Map<java.lang.String,java.lang.String> getOthers() {
    return others;
  }

  /**
   * Sets the value of the 'others' field.
   * @param value the value to set.
   */
  public void setOthers(java.util.Map<java.lang.String,java.lang.String> value) {
    this.others = value;
  }

  /**
   * Creates a new Context RecordBuilder.
   * @return A new Context RecordBuilder
   */
  public static com.ebay.epic.common.model.Context.Builder newBuilder() {
    return new com.ebay.epic.common.model.Context.Builder();
  }

  /**
   * Creates a new Context RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Context RecordBuilder
   */
  public static com.ebay.epic.common.model.Context.Builder newBuilder(com.ebay.epic.common.model.Context.Builder other) {
    return new com.ebay.epic.common.model.Context.Builder(other);
  }

  /**
   * Creates a new Context RecordBuilder by copying an existing Context instance.
   * @param other The existing instance to copy.
   * @return A new Context RecordBuilder
   */
  public static com.ebay.epic.common.model.Context.Builder newBuilder(com.ebay.epic.common.model.Context other) {
    return new com.ebay.epic.common.model.Context.Builder(other);
  }

  /**
   * RecordBuilder for Context instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Context>
    implements org.apache.avro.data.RecordBuilder<Context> {

    private java.lang.String appId;
    private java.lang.String version;
    private java.lang.String os;
    private java.lang.String utcOffset;
    private java.lang.String clientIp;
    private java.lang.String userLang;
    private java.lang.String userAgent;
    private java.lang.Long screenWidth;
    private java.lang.Long screenHeight;
    private java.lang.Long screenDPI;
    private com.ebay.epic.common.model.MobileDeviceContext deviceInfo;
    private com.ebay.epic.common.model.MobileDeviceContext.Builder deviceInfoBuilder;
    private java.util.Map<java.lang.String,java.lang.String> others;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.ebay.epic.common.model.Context.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.appId)) {
        this.appId = data().deepCopy(fields()[0].schema(), other.appId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.version)) {
        this.version = data().deepCopy(fields()[1].schema(), other.version);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.os)) {
        this.os = data().deepCopy(fields()[2].schema(), other.os);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.utcOffset)) {
        this.utcOffset = data().deepCopy(fields()[3].schema(), other.utcOffset);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.clientIp)) {
        this.clientIp = data().deepCopy(fields()[4].schema(), other.clientIp);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.userLang)) {
        this.userLang = data().deepCopy(fields()[5].schema(), other.userLang);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.userAgent)) {
        this.userAgent = data().deepCopy(fields()[6].schema(), other.userAgent);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.screenWidth)) {
        this.screenWidth = data().deepCopy(fields()[7].schema(), other.screenWidth);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.screenHeight)) {
        this.screenHeight = data().deepCopy(fields()[8].schema(), other.screenHeight);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.screenDPI)) {
        this.screenDPI = data().deepCopy(fields()[9].schema(), other.screenDPI);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.deviceInfo)) {
        this.deviceInfo = data().deepCopy(fields()[10].schema(), other.deviceInfo);
        fieldSetFlags()[10] = true;
      }
      if (other.hasDeviceInfoBuilder()) {
        this.deviceInfoBuilder = com.ebay.epic.common.model.MobileDeviceContext.newBuilder(other.getDeviceInfoBuilder());
      }
      if (isValidValue(fields()[11], other.others)) {
        this.others = data().deepCopy(fields()[11].schema(), other.others);
        fieldSetFlags()[11] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Context instance
     * @param other The existing instance to copy.
     */
    private Builder(com.ebay.epic.common.model.Context other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.appId)) {
        this.appId = data().deepCopy(fields()[0].schema(), other.appId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.version)) {
        this.version = data().deepCopy(fields()[1].schema(), other.version);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.os)) {
        this.os = data().deepCopy(fields()[2].schema(), other.os);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.utcOffset)) {
        this.utcOffset = data().deepCopy(fields()[3].schema(), other.utcOffset);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.clientIp)) {
        this.clientIp = data().deepCopy(fields()[4].schema(), other.clientIp);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.userLang)) {
        this.userLang = data().deepCopy(fields()[5].schema(), other.userLang);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.userAgent)) {
        this.userAgent = data().deepCopy(fields()[6].schema(), other.userAgent);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.screenWidth)) {
        this.screenWidth = data().deepCopy(fields()[7].schema(), other.screenWidth);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.screenHeight)) {
        this.screenHeight = data().deepCopy(fields()[8].schema(), other.screenHeight);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.screenDPI)) {
        this.screenDPI = data().deepCopy(fields()[9].schema(), other.screenDPI);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.deviceInfo)) {
        this.deviceInfo = data().deepCopy(fields()[10].schema(), other.deviceInfo);
        fieldSetFlags()[10] = true;
      }
      this.deviceInfoBuilder = null;
      if (isValidValue(fields()[11], other.others)) {
        this.others = data().deepCopy(fields()[11].schema(), other.others);
        fieldSetFlags()[11] = true;
      }
    }

    /**
      * Gets the value of the 'appId' field.
      * @return The value.
      */
    public java.lang.String getAppId() {
      return appId;
    }

    /**
      * Sets the value of the 'appId' field.
      * @param value The value of 'appId'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder setAppId(java.lang.String value) {
      validate(fields()[0], value);
      this.appId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'appId' field has been set.
      * @return True if the 'appId' field has been set, false otherwise.
      */
    public boolean hasAppId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'appId' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder clearAppId() {
      appId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'version' field.
      * @return The value.
      */
    public java.lang.String getVersion() {
      return version;
    }

    /**
      * Sets the value of the 'version' field.
      * @param value The value of 'version'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder setVersion(java.lang.String value) {
      validate(fields()[1], value);
      this.version = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'version' field has been set.
      * @return True if the 'version' field has been set, false otherwise.
      */
    public boolean hasVersion() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'version' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder clearVersion() {
      version = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'os' field.
      * @return The value.
      */
    public java.lang.String getOs() {
      return os;
    }

    /**
      * Sets the value of the 'os' field.
      * @param value The value of 'os'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder setOs(java.lang.String value) {
      validate(fields()[2], value);
      this.os = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'os' field has been set.
      * @return True if the 'os' field has been set, false otherwise.
      */
    public boolean hasOs() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'os' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder clearOs() {
      os = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'utcOffset' field.
      * @return The value.
      */
    public java.lang.String getUtcOffset() {
      return utcOffset;
    }

    /**
      * Sets the value of the 'utcOffset' field.
      * @param value The value of 'utcOffset'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder setUtcOffset(java.lang.String value) {
      validate(fields()[3], value);
      this.utcOffset = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'utcOffset' field has been set.
      * @return True if the 'utcOffset' field has been set, false otherwise.
      */
    public boolean hasUtcOffset() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'utcOffset' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder clearUtcOffset() {
      utcOffset = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'clientIp' field.
      * @return The value.
      */
    public java.lang.String getClientIp() {
      return clientIp;
    }

    /**
      * Sets the value of the 'clientIp' field.
      * @param value The value of 'clientIp'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder setClientIp(java.lang.String value) {
      validate(fields()[4], value);
      this.clientIp = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'clientIp' field has been set.
      * @return True if the 'clientIp' field has been set, false otherwise.
      */
    public boolean hasClientIp() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'clientIp' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder clearClientIp() {
      clientIp = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'userLang' field.
      * @return The value.
      */
    public java.lang.String getUserLang() {
      return userLang;
    }

    /**
      * Sets the value of the 'userLang' field.
      * @param value The value of 'userLang'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder setUserLang(java.lang.String value) {
      validate(fields()[5], value);
      this.userLang = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'userLang' field has been set.
      * @return True if the 'userLang' field has been set, false otherwise.
      */
    public boolean hasUserLang() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'userLang' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder clearUserLang() {
      userLang = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'userAgent' field.
      * @return The value.
      */
    public java.lang.String getUserAgent() {
      return userAgent;
    }

    /**
      * Sets the value of the 'userAgent' field.
      * @param value The value of 'userAgent'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder setUserAgent(java.lang.String value) {
      validate(fields()[6], value);
      this.userAgent = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'userAgent' field has been set.
      * @return True if the 'userAgent' field has been set, false otherwise.
      */
    public boolean hasUserAgent() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'userAgent' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder clearUserAgent() {
      userAgent = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'screenWidth' field.
      * @return The value.
      */
    public java.lang.Long getScreenWidth() {
      return screenWidth;
    }

    /**
      * Sets the value of the 'screenWidth' field.
      * @param value The value of 'screenWidth'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder setScreenWidth(java.lang.Long value) {
      validate(fields()[7], value);
      this.screenWidth = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'screenWidth' field has been set.
      * @return True if the 'screenWidth' field has been set, false otherwise.
      */
    public boolean hasScreenWidth() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'screenWidth' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder clearScreenWidth() {
      screenWidth = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /**
      * Gets the value of the 'screenHeight' field.
      * @return The value.
      */
    public java.lang.Long getScreenHeight() {
      return screenHeight;
    }

    /**
      * Sets the value of the 'screenHeight' field.
      * @param value The value of 'screenHeight'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder setScreenHeight(java.lang.Long value) {
      validate(fields()[8], value);
      this.screenHeight = value;
      fieldSetFlags()[8] = true;
      return this;
    }

    /**
      * Checks whether the 'screenHeight' field has been set.
      * @return True if the 'screenHeight' field has been set, false otherwise.
      */
    public boolean hasScreenHeight() {
      return fieldSetFlags()[8];
    }


    /**
      * Clears the value of the 'screenHeight' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder clearScreenHeight() {
      screenHeight = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    /**
      * Gets the value of the 'screenDPI' field.
      * @return The value.
      */
    public java.lang.Long getScreenDPI() {
      return screenDPI;
    }

    /**
      * Sets the value of the 'screenDPI' field.
      * @param value The value of 'screenDPI'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder setScreenDPI(java.lang.Long value) {
      validate(fields()[9], value);
      this.screenDPI = value;
      fieldSetFlags()[9] = true;
      return this;
    }

    /**
      * Checks whether the 'screenDPI' field has been set.
      * @return True if the 'screenDPI' field has been set, false otherwise.
      */
    public boolean hasScreenDPI() {
      return fieldSetFlags()[9];
    }


    /**
      * Clears the value of the 'screenDPI' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder clearScreenDPI() {
      screenDPI = null;
      fieldSetFlags()[9] = false;
      return this;
    }

    /**
      * Gets the value of the 'deviceInfo' field.
      * @return The value.
      */
    public com.ebay.epic.common.model.MobileDeviceContext getDeviceInfo() {
      return deviceInfo;
    }

    /**
      * Sets the value of the 'deviceInfo' field.
      * @param value The value of 'deviceInfo'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder setDeviceInfo(com.ebay.epic.common.model.MobileDeviceContext value) {
      validate(fields()[10], value);
      this.deviceInfoBuilder = null;
      this.deviceInfo = value;
      fieldSetFlags()[10] = true;
      return this;
    }

    /**
      * Checks whether the 'deviceInfo' field has been set.
      * @return True if the 'deviceInfo' field has been set, false otherwise.
      */
    public boolean hasDeviceInfo() {
      return fieldSetFlags()[10];
    }

    /**
     * Gets the Builder instance for the 'deviceInfo' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public com.ebay.epic.common.model.MobileDeviceContext.Builder getDeviceInfoBuilder() {
      if (deviceInfoBuilder == null) {
        if (hasDeviceInfo()) {
          setDeviceInfoBuilder(com.ebay.epic.common.model.MobileDeviceContext.newBuilder(deviceInfo));
        } else {
          setDeviceInfoBuilder(com.ebay.epic.common.model.MobileDeviceContext.newBuilder());
        }
      }
      return deviceInfoBuilder;
    }

    /**
     * Sets the Builder instance for the 'deviceInfo' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public com.ebay.epic.common.model.Context.Builder setDeviceInfoBuilder(com.ebay.epic.common.model.MobileDeviceContext.Builder value) {
      clearDeviceInfo();
      deviceInfoBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'deviceInfo' field has an active Builder instance
     * @return True if the 'deviceInfo' field has an active Builder instance
     */
    public boolean hasDeviceInfoBuilder() {
      return deviceInfoBuilder != null;
    }

    /**
      * Clears the value of the 'deviceInfo' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder clearDeviceInfo() {
      deviceInfo = null;
      deviceInfoBuilder = null;
      fieldSetFlags()[10] = false;
      return this;
    }

    /**
      * Gets the value of the 'others' field.
      * @return The value.
      */
    public java.util.Map<java.lang.String,java.lang.String> getOthers() {
      return others;
    }

    /**
      * Sets the value of the 'others' field.
      * @param value The value of 'others'.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder setOthers(java.util.Map<java.lang.String,java.lang.String> value) {
      validate(fields()[11], value);
      this.others = value;
      fieldSetFlags()[11] = true;
      return this;
    }

    /**
      * Checks whether the 'others' field has been set.
      * @return True if the 'others' field has been set, false otherwise.
      */
    public boolean hasOthers() {
      return fieldSetFlags()[11];
    }


    /**
      * Clears the value of the 'others' field.
      * @return This builder.
      */
    public com.ebay.epic.common.model.Context.Builder clearOthers() {
      others = null;
      fieldSetFlags()[11] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Context build() {
      try {
        Context record = new Context();
        record.appId = fieldSetFlags()[0] ? this.appId : (java.lang.String) defaultValue(fields()[0]);
        record.version = fieldSetFlags()[1] ? this.version : (java.lang.String) defaultValue(fields()[1]);
        record.os = fieldSetFlags()[2] ? this.os : (java.lang.String) defaultValue(fields()[2]);
        record.utcOffset = fieldSetFlags()[3] ? this.utcOffset : (java.lang.String) defaultValue(fields()[3]);
        record.clientIp = fieldSetFlags()[4] ? this.clientIp : (java.lang.String) defaultValue(fields()[4]);
        record.userLang = fieldSetFlags()[5] ? this.userLang : (java.lang.String) defaultValue(fields()[5]);
        record.userAgent = fieldSetFlags()[6] ? this.userAgent : (java.lang.String) defaultValue(fields()[6]);
        record.screenWidth = fieldSetFlags()[7] ? this.screenWidth : (java.lang.Long) defaultValue(fields()[7]);
        record.screenHeight = fieldSetFlags()[8] ? this.screenHeight : (java.lang.Long) defaultValue(fields()[8]);
        record.screenDPI = fieldSetFlags()[9] ? this.screenDPI : (java.lang.Long) defaultValue(fields()[9]);
        if (deviceInfoBuilder != null) {
          record.deviceInfo = this.deviceInfoBuilder.build();
        } else {
          record.deviceInfo = fieldSetFlags()[10] ? this.deviceInfo : (com.ebay.epic.common.model.MobileDeviceContext) defaultValue(fields()[10]);
        }
        record.others = fieldSetFlags()[11] ? this.others : (java.util.Map<java.lang.String,java.lang.String>) defaultValue(fields()[11]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Context>
    WRITER$ = (org.apache.avro.io.DatumWriter<Context>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Context>
    READER$ = (org.apache.avro.io.DatumReader<Context>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
