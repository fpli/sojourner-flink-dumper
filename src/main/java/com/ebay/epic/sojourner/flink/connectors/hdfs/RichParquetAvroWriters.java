package com.ebay.epic.sojourner.flink.connectors.hdfs;

import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.reflect.ReflectData;
import org.apache.flink.formats.parquet.ParquetBuilder;
import org.apache.flink.formats.parquet.ParquetWriterFactory;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.io.OutputFile;

public class RichParquetAvroWriters {

    public static <T> ParquetWriterFactory<T> forAllowNullReflectRecord(Class<T> type) {
        final String schemaStr = ReflectData.AllowNull.get().getSchema(type).toString();
        final ParquetBuilder<T> builder =
                (out) -> createAvroParquetWriter(schemaStr, ReflectData.get(), out);
        return new ParquetWriterFactory<>(builder);
    }

    private static <T> ParquetWriter<T> createAvroParquetWriter(
            String schemaStr, GenericData dataModel, OutputFile out) throws IOException {
        return AvroParquetWriter.<T>builder(out)
                .withSchema(new Schema.Parser().parse(schemaStr))
                .withDataModel(dataModel)
                .withCompressionCodec(CompressionCodecName.SNAPPY).build();
    }
}
