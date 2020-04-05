// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: npgraph_service.proto

package org.rtassembly.npgraph.grpc;

/**
 * <pre>
 * An alignment from minimap2 sent to the server 
 * </pre>
 *
 * Protobuf type {@code assembly.AlignmentMsg}
 */
public  final class AlignmentMsg extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:assembly.AlignmentMsg)
    AlignmentMsgOrBuilder {
private static final long serialVersionUID = 0L;
  // Use AlignmentMsg.newBuilder() to construct.
  private AlignmentMsg(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AlignmentMsg() {
    queryName_ = "";
    targetName_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new AlignmentMsg();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AlignmentMsg(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            queryName_ = s;
            break;
          }
          case 16: {

            queryLength_ = input.readInt32();
            break;
          }
          case 24: {

            queryStart_ = input.readInt32();
            break;
          }
          case 32: {

            queryEnd_ = input.readInt32();
            break;
          }
          case 40: {

            strand_ = input.readBool();
            break;
          }
          case 50: {
            java.lang.String s = input.readStringRequireUtf8();

            targetName_ = s;
            break;
          }
          case 56: {

            targetLength_ = input.readInt32();
            break;
          }
          case 64: {

            targetStart_ = input.readInt32();
            break;
          }
          case 72: {

            targetEnd_ = input.readInt32();
            break;
          }
          case 80: {

            quality_ = input.readInt32();
            break;
          }
          case 88: {

            score_ = input.readInt32();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.rtassembly.npgraph.grpc.AssemblyGuideProto.internal_static_assembly_AlignmentMsg_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.rtassembly.npgraph.grpc.AssemblyGuideProto.internal_static_assembly_AlignmentMsg_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.rtassembly.npgraph.grpc.AlignmentMsg.class, org.rtassembly.npgraph.grpc.AlignmentMsg.Builder.class);
  }

  public static final int QUERY_NAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object queryName_;
  /**
   * <code>string query_name = 1;</code>
   * @return The queryName.
   */
  public java.lang.String getQueryName() {
    java.lang.Object ref = queryName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      queryName_ = s;
      return s;
    }
  }
  /**
   * <code>string query_name = 1;</code>
   * @return The bytes for queryName.
   */
  public com.google.protobuf.ByteString
      getQueryNameBytes() {
    java.lang.Object ref = queryName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      queryName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int QUERY_LENGTH_FIELD_NUMBER = 2;
  private int queryLength_;
  /**
   * <code>int32 query_length = 2;</code>
   * @return The queryLength.
   */
  public int getQueryLength() {
    return queryLength_;
  }

  public static final int QUERY_START_FIELD_NUMBER = 3;
  private int queryStart_;
  /**
   * <code>int32 query_start = 3;</code>
   * @return The queryStart.
   */
  public int getQueryStart() {
    return queryStart_;
  }

  public static final int QUERY_END_FIELD_NUMBER = 4;
  private int queryEnd_;
  /**
   * <code>int32 query_end = 4;</code>
   * @return The queryEnd.
   */
  public int getQueryEnd() {
    return queryEnd_;
  }

  public static final int STRAND_FIELD_NUMBER = 5;
  private boolean strand_;
  /**
   * <code>bool strand = 5;</code>
   * @return The strand.
   */
  public boolean getStrand() {
    return strand_;
  }

  public static final int TARGET_NAME_FIELD_NUMBER = 6;
  private volatile java.lang.Object targetName_;
  /**
   * <code>string target_name = 6;</code>
   * @return The targetName.
   */
  public java.lang.String getTargetName() {
    java.lang.Object ref = targetName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      targetName_ = s;
      return s;
    }
  }
  /**
   * <code>string target_name = 6;</code>
   * @return The bytes for targetName.
   */
  public com.google.protobuf.ByteString
      getTargetNameBytes() {
    java.lang.Object ref = targetName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      targetName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TARGET_LENGTH_FIELD_NUMBER = 7;
  private int targetLength_;
  /**
   * <code>int32 target_length = 7;</code>
   * @return The targetLength.
   */
  public int getTargetLength() {
    return targetLength_;
  }

  public static final int TARGET_START_FIELD_NUMBER = 8;
  private int targetStart_;
  /**
   * <code>int32 target_start = 8;</code>
   * @return The targetStart.
   */
  public int getTargetStart() {
    return targetStart_;
  }

  public static final int TARGET_END_FIELD_NUMBER = 9;
  private int targetEnd_;
  /**
   * <code>int32 target_end = 9;</code>
   * @return The targetEnd.
   */
  public int getTargetEnd() {
    return targetEnd_;
  }

  public static final int QUALITY_FIELD_NUMBER = 10;
  private int quality_;
  /**
   * <code>int32 quality = 10;</code>
   * @return The quality.
   */
  public int getQuality() {
    return quality_;
  }

  public static final int SCORE_FIELD_NUMBER = 11;
  private int score_;
  /**
   * <code>int32 score = 11;</code>
   * @return The score.
   */
  public int getScore() {
    return score_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getQueryNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, queryName_);
    }
    if (queryLength_ != 0) {
      output.writeInt32(2, queryLength_);
    }
    if (queryStart_ != 0) {
      output.writeInt32(3, queryStart_);
    }
    if (queryEnd_ != 0) {
      output.writeInt32(4, queryEnd_);
    }
    if (strand_ != false) {
      output.writeBool(5, strand_);
    }
    if (!getTargetNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, targetName_);
    }
    if (targetLength_ != 0) {
      output.writeInt32(7, targetLength_);
    }
    if (targetStart_ != 0) {
      output.writeInt32(8, targetStart_);
    }
    if (targetEnd_ != 0) {
      output.writeInt32(9, targetEnd_);
    }
    if (quality_ != 0) {
      output.writeInt32(10, quality_);
    }
    if (score_ != 0) {
      output.writeInt32(11, score_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getQueryNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, queryName_);
    }
    if (queryLength_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, queryLength_);
    }
    if (queryStart_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, queryStart_);
    }
    if (queryEnd_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, queryEnd_);
    }
    if (strand_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(5, strand_);
    }
    if (!getTargetNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, targetName_);
    }
    if (targetLength_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, targetLength_);
    }
    if (targetStart_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(8, targetStart_);
    }
    if (targetEnd_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(9, targetEnd_);
    }
    if (quality_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(10, quality_);
    }
    if (score_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(11, score_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.rtassembly.npgraph.grpc.AlignmentMsg)) {
      return super.equals(obj);
    }
    org.rtassembly.npgraph.grpc.AlignmentMsg other = (org.rtassembly.npgraph.grpc.AlignmentMsg) obj;

    if (!getQueryName()
        .equals(other.getQueryName())) return false;
    if (getQueryLength()
        != other.getQueryLength()) return false;
    if (getQueryStart()
        != other.getQueryStart()) return false;
    if (getQueryEnd()
        != other.getQueryEnd()) return false;
    if (getStrand()
        != other.getStrand()) return false;
    if (!getTargetName()
        .equals(other.getTargetName())) return false;
    if (getTargetLength()
        != other.getTargetLength()) return false;
    if (getTargetStart()
        != other.getTargetStart()) return false;
    if (getTargetEnd()
        != other.getTargetEnd()) return false;
    if (getQuality()
        != other.getQuality()) return false;
    if (getScore()
        != other.getScore()) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + QUERY_NAME_FIELD_NUMBER;
    hash = (53 * hash) + getQueryName().hashCode();
    hash = (37 * hash) + QUERY_LENGTH_FIELD_NUMBER;
    hash = (53 * hash) + getQueryLength();
    hash = (37 * hash) + QUERY_START_FIELD_NUMBER;
    hash = (53 * hash) + getQueryStart();
    hash = (37 * hash) + QUERY_END_FIELD_NUMBER;
    hash = (53 * hash) + getQueryEnd();
    hash = (37 * hash) + STRAND_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getStrand());
    hash = (37 * hash) + TARGET_NAME_FIELD_NUMBER;
    hash = (53 * hash) + getTargetName().hashCode();
    hash = (37 * hash) + TARGET_LENGTH_FIELD_NUMBER;
    hash = (53 * hash) + getTargetLength();
    hash = (37 * hash) + TARGET_START_FIELD_NUMBER;
    hash = (53 * hash) + getTargetStart();
    hash = (37 * hash) + TARGET_END_FIELD_NUMBER;
    hash = (53 * hash) + getTargetEnd();
    hash = (37 * hash) + QUALITY_FIELD_NUMBER;
    hash = (53 * hash) + getQuality();
    hash = (37 * hash) + SCORE_FIELD_NUMBER;
    hash = (53 * hash) + getScore();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.rtassembly.npgraph.grpc.AlignmentMsg parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.rtassembly.npgraph.grpc.AlignmentMsg parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.rtassembly.npgraph.grpc.AlignmentMsg parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.rtassembly.npgraph.grpc.AlignmentMsg parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.rtassembly.npgraph.grpc.AlignmentMsg parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.rtassembly.npgraph.grpc.AlignmentMsg parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.rtassembly.npgraph.grpc.AlignmentMsg parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.rtassembly.npgraph.grpc.AlignmentMsg parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.rtassembly.npgraph.grpc.AlignmentMsg parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.rtassembly.npgraph.grpc.AlignmentMsg parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.rtassembly.npgraph.grpc.AlignmentMsg parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.rtassembly.npgraph.grpc.AlignmentMsg parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.rtassembly.npgraph.grpc.AlignmentMsg prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * An alignment from minimap2 sent to the server 
   * </pre>
   *
   * Protobuf type {@code assembly.AlignmentMsg}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:assembly.AlignmentMsg)
      org.rtassembly.npgraph.grpc.AlignmentMsgOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.rtassembly.npgraph.grpc.AssemblyGuideProto.internal_static_assembly_AlignmentMsg_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.rtassembly.npgraph.grpc.AssemblyGuideProto.internal_static_assembly_AlignmentMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.rtassembly.npgraph.grpc.AlignmentMsg.class, org.rtassembly.npgraph.grpc.AlignmentMsg.Builder.class);
    }

    // Construct using org.rtassembly.npgraph.grpc.AlignmentMsg.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      queryName_ = "";

      queryLength_ = 0;

      queryStart_ = 0;

      queryEnd_ = 0;

      strand_ = false;

      targetName_ = "";

      targetLength_ = 0;

      targetStart_ = 0;

      targetEnd_ = 0;

      quality_ = 0;

      score_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.rtassembly.npgraph.grpc.AssemblyGuideProto.internal_static_assembly_AlignmentMsg_descriptor;
    }

    @java.lang.Override
    public org.rtassembly.npgraph.grpc.AlignmentMsg getDefaultInstanceForType() {
      return org.rtassembly.npgraph.grpc.AlignmentMsg.getDefaultInstance();
    }

    @java.lang.Override
    public org.rtassembly.npgraph.grpc.AlignmentMsg build() {
      org.rtassembly.npgraph.grpc.AlignmentMsg result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.rtassembly.npgraph.grpc.AlignmentMsg buildPartial() {
      org.rtassembly.npgraph.grpc.AlignmentMsg result = new org.rtassembly.npgraph.grpc.AlignmentMsg(this);
      result.queryName_ = queryName_;
      result.queryLength_ = queryLength_;
      result.queryStart_ = queryStart_;
      result.queryEnd_ = queryEnd_;
      result.strand_ = strand_;
      result.targetName_ = targetName_;
      result.targetLength_ = targetLength_;
      result.targetStart_ = targetStart_;
      result.targetEnd_ = targetEnd_;
      result.quality_ = quality_;
      result.score_ = score_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.rtassembly.npgraph.grpc.AlignmentMsg) {
        return mergeFrom((org.rtassembly.npgraph.grpc.AlignmentMsg)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.rtassembly.npgraph.grpc.AlignmentMsg other) {
      if (other == org.rtassembly.npgraph.grpc.AlignmentMsg.getDefaultInstance()) return this;
      if (!other.getQueryName().isEmpty()) {
        queryName_ = other.queryName_;
        onChanged();
      }
      if (other.getQueryLength() != 0) {
        setQueryLength(other.getQueryLength());
      }
      if (other.getQueryStart() != 0) {
        setQueryStart(other.getQueryStart());
      }
      if (other.getQueryEnd() != 0) {
        setQueryEnd(other.getQueryEnd());
      }
      if (other.getStrand() != false) {
        setStrand(other.getStrand());
      }
      if (!other.getTargetName().isEmpty()) {
        targetName_ = other.targetName_;
        onChanged();
      }
      if (other.getTargetLength() != 0) {
        setTargetLength(other.getTargetLength());
      }
      if (other.getTargetStart() != 0) {
        setTargetStart(other.getTargetStart());
      }
      if (other.getTargetEnd() != 0) {
        setTargetEnd(other.getTargetEnd());
      }
      if (other.getQuality() != 0) {
        setQuality(other.getQuality());
      }
      if (other.getScore() != 0) {
        setScore(other.getScore());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      org.rtassembly.npgraph.grpc.AlignmentMsg parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.rtassembly.npgraph.grpc.AlignmentMsg) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object queryName_ = "";
    /**
     * <code>string query_name = 1;</code>
     * @return The queryName.
     */
    public java.lang.String getQueryName() {
      java.lang.Object ref = queryName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        queryName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string query_name = 1;</code>
     * @return The bytes for queryName.
     */
    public com.google.protobuf.ByteString
        getQueryNameBytes() {
      java.lang.Object ref = queryName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        queryName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string query_name = 1;</code>
     * @param value The queryName to set.
     * @return This builder for chaining.
     */
    public Builder setQueryName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      queryName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string query_name = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearQueryName() {
      
      queryName_ = getDefaultInstance().getQueryName();
      onChanged();
      return this;
    }
    /**
     * <code>string query_name = 1;</code>
     * @param value The bytes for queryName to set.
     * @return This builder for chaining.
     */
    public Builder setQueryNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      queryName_ = value;
      onChanged();
      return this;
    }

    private int queryLength_ ;
    /**
     * <code>int32 query_length = 2;</code>
     * @return The queryLength.
     */
    public int getQueryLength() {
      return queryLength_;
    }
    /**
     * <code>int32 query_length = 2;</code>
     * @param value The queryLength to set.
     * @return This builder for chaining.
     */
    public Builder setQueryLength(int value) {
      
      queryLength_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 query_length = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearQueryLength() {
      
      queryLength_ = 0;
      onChanged();
      return this;
    }

    private int queryStart_ ;
    /**
     * <code>int32 query_start = 3;</code>
     * @return The queryStart.
     */
    public int getQueryStart() {
      return queryStart_;
    }
    /**
     * <code>int32 query_start = 3;</code>
     * @param value The queryStart to set.
     * @return This builder for chaining.
     */
    public Builder setQueryStart(int value) {
      
      queryStart_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 query_start = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearQueryStart() {
      
      queryStart_ = 0;
      onChanged();
      return this;
    }

    private int queryEnd_ ;
    /**
     * <code>int32 query_end = 4;</code>
     * @return The queryEnd.
     */
    public int getQueryEnd() {
      return queryEnd_;
    }
    /**
     * <code>int32 query_end = 4;</code>
     * @param value The queryEnd to set.
     * @return This builder for chaining.
     */
    public Builder setQueryEnd(int value) {
      
      queryEnd_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 query_end = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearQueryEnd() {
      
      queryEnd_ = 0;
      onChanged();
      return this;
    }

    private boolean strand_ ;
    /**
     * <code>bool strand = 5;</code>
     * @return The strand.
     */
    public boolean getStrand() {
      return strand_;
    }
    /**
     * <code>bool strand = 5;</code>
     * @param value The strand to set.
     * @return This builder for chaining.
     */
    public Builder setStrand(boolean value) {
      
      strand_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool strand = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearStrand() {
      
      strand_ = false;
      onChanged();
      return this;
    }

    private java.lang.Object targetName_ = "";
    /**
     * <code>string target_name = 6;</code>
     * @return The targetName.
     */
    public java.lang.String getTargetName() {
      java.lang.Object ref = targetName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        targetName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string target_name = 6;</code>
     * @return The bytes for targetName.
     */
    public com.google.protobuf.ByteString
        getTargetNameBytes() {
      java.lang.Object ref = targetName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        targetName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string target_name = 6;</code>
     * @param value The targetName to set.
     * @return This builder for chaining.
     */
    public Builder setTargetName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      targetName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string target_name = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearTargetName() {
      
      targetName_ = getDefaultInstance().getTargetName();
      onChanged();
      return this;
    }
    /**
     * <code>string target_name = 6;</code>
     * @param value The bytes for targetName to set.
     * @return This builder for chaining.
     */
    public Builder setTargetNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      targetName_ = value;
      onChanged();
      return this;
    }

    private int targetLength_ ;
    /**
     * <code>int32 target_length = 7;</code>
     * @return The targetLength.
     */
    public int getTargetLength() {
      return targetLength_;
    }
    /**
     * <code>int32 target_length = 7;</code>
     * @param value The targetLength to set.
     * @return This builder for chaining.
     */
    public Builder setTargetLength(int value) {
      
      targetLength_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 target_length = 7;</code>
     * @return This builder for chaining.
     */
    public Builder clearTargetLength() {
      
      targetLength_ = 0;
      onChanged();
      return this;
    }

    private int targetStart_ ;
    /**
     * <code>int32 target_start = 8;</code>
     * @return The targetStart.
     */
    public int getTargetStart() {
      return targetStart_;
    }
    /**
     * <code>int32 target_start = 8;</code>
     * @param value The targetStart to set.
     * @return This builder for chaining.
     */
    public Builder setTargetStart(int value) {
      
      targetStart_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 target_start = 8;</code>
     * @return This builder for chaining.
     */
    public Builder clearTargetStart() {
      
      targetStart_ = 0;
      onChanged();
      return this;
    }

    private int targetEnd_ ;
    /**
     * <code>int32 target_end = 9;</code>
     * @return The targetEnd.
     */
    public int getTargetEnd() {
      return targetEnd_;
    }
    /**
     * <code>int32 target_end = 9;</code>
     * @param value The targetEnd to set.
     * @return This builder for chaining.
     */
    public Builder setTargetEnd(int value) {
      
      targetEnd_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 target_end = 9;</code>
     * @return This builder for chaining.
     */
    public Builder clearTargetEnd() {
      
      targetEnd_ = 0;
      onChanged();
      return this;
    }

    private int quality_ ;
    /**
     * <code>int32 quality = 10;</code>
     * @return The quality.
     */
    public int getQuality() {
      return quality_;
    }
    /**
     * <code>int32 quality = 10;</code>
     * @param value The quality to set.
     * @return This builder for chaining.
     */
    public Builder setQuality(int value) {
      
      quality_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 quality = 10;</code>
     * @return This builder for chaining.
     */
    public Builder clearQuality() {
      
      quality_ = 0;
      onChanged();
      return this;
    }

    private int score_ ;
    /**
     * <code>int32 score = 11;</code>
     * @return The score.
     */
    public int getScore() {
      return score_;
    }
    /**
     * <code>int32 score = 11;</code>
     * @param value The score to set.
     * @return This builder for chaining.
     */
    public Builder setScore(int value) {
      
      score_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 score = 11;</code>
     * @return This builder for chaining.
     */
    public Builder clearScore() {
      
      score_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:assembly.AlignmentMsg)
  }

  // @@protoc_insertion_point(class_scope:assembly.AlignmentMsg)
  private static final org.rtassembly.npgraph.grpc.AlignmentMsg DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.rtassembly.npgraph.grpc.AlignmentMsg();
  }

  public static org.rtassembly.npgraph.grpc.AlignmentMsg getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AlignmentMsg>
      PARSER = new com.google.protobuf.AbstractParser<AlignmentMsg>() {
    @java.lang.Override
    public AlignmentMsg parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new AlignmentMsg(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AlignmentMsg> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AlignmentMsg> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.rtassembly.npgraph.grpc.AlignmentMsg getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
