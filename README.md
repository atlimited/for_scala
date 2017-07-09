# for_scala

## 動作検証環境

sprak:1.6.0
scala:2.11

## 実行方法

```
$SPARK_HOME/bin/spark-shell --jars kuromoji-0.7.7.jar --packages com.databricks:spark-csv_2.11:1.5.0
scala> :load morphological_analysis.scala
Loading morphological_analysis.scala...
import org.apache.spark.{SparkConf, SparkContext}
import org.atilika.kuromoji.Tokenizer
import scala.collection.JavaConverters.asScalaBufferConverter
import org.apache.spark.sql.SQLContext
sqlContext: org.apache.spark.sql.SQLContext = org.apache.spark.sql.SQLContext@6e38e95
import org.apache.spark.sql.types.{StructType, StructField, StringType, FloatType}
import org.apache.spark.sql.Row
df: org.apache.spark.sql.DataFrame = [text: string]
+------------+
|        text|
+------------+
|すもももももももものうち|
|      外国人参政権|
| 私もあさって日曜最終日|
+------------+

import java.io.Serializable
defined class JaTokenizer
jt: JaTokenizer = $iwC$$iwC$JaTokenizer@7ee0af8c
ma: org.apache.spark.sql.UserDefinedFunction = UserDefinedFunction(<function1>,StringType,List(StringType))
df2: org.apache.spark.sql.DataFrame = [text: string, split text: string]
+------------+------------------+
|        text|        split text|
+------------+------------------+
|すもももももももものうち|すもも も もも も もも の うち|
|      外国人参政権|          外国 人参 政権|
| 私もあさって日曜最終日| 私 も あさっ て 日曜 最終 日|
+------------+------------------+
```
