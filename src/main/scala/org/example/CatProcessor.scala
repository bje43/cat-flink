package org.example

import org.apache.flink.streaming.api.scala._
import org.example.input_streams.InputStreams
import org.example.datatypes.{Cat, CatAlert}
import org.apache.flink.cep.{PatternFlatSelectFunction, PatternFlatTimeoutFunction}
import org.apache.flink.cep.scala.{CEP, PatternStream}
import org.apache.flink.cep.scala.pattern.Pattern
import org.apache.flink.util.Collector
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator

import scala.collection.Map

object CatProcessor {
  def main(args: Array[String]) {

    // set up the execution environment
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val catStream: DataStream[Cat] = InputStreams.inputStreamForCats(env).keyBy(_.birthTime)
    val keyedCats = catStream.keyBy(_.birthTime)

    val dislikedCatPattern = Pattern
      .begin[Cat]("start")


    val dislikeStream: PatternStream[Cat] = CEP.pattern[Cat](keyedCats, dislikedCatPattern)

    val catAlerts = dislikeStream.select(x => selectStartFn(x))

    catAlerts.print()
    catStream.print()

    env.execute()
  }

  def selectStartFn(pattern: Map[String, Iterable[Cat]]) = {
    val cats = pattern("start")
    val alerts = cats.map(c => new CatAlert(c))
    alerts
  }
}
