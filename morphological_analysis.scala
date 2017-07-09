import org.apache.spark.{SparkConf, SparkContext}
import org.atilika.kuromoji.Tokenizer

import scala.collection.JavaConverters.asScalaBufferConverter

import org.apache.spark.sql.SQLContext

val sqlContext = new SQLContext(sc)


import org.apache.spark.sql.types.{
  StructType,
  StructField,
  StringType,
  FloatType
}

import org.apache.spark.sql.Row
 
val df = sqlContext.read.format("com.databricks.spark.csv").option("header", "true").load("./data/doc.txt")
df.show()

import java.io.Serializable
class JaTokenizer extends java.io.Serializable{
    def tokenize(text: String): java.util.List[org.atilika.kuromoji.Token] = Tokenizer.builder().build().tokenize(text)
}

val jt = new JaTokenizer()

// split by surface token
val ma = udf ( (text: String) => jt.tokenize(text).asScala.map(x => x.getSurfaceForm).mkString(" ") )

// split by base form token
//val ma = udf ( (text: String) => jt.tokenize(text).asScala.map(x => x.getBaseForm).mkString(" ") )

val df2 = df.withColumn("split text", ma(df("text")))

df2.show()
