package org.example.input_streams

import org.apache.flink.api.scala._
import org.example.datatypes.Cat
import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.api.functions.source.SourceFunction.SourceContext
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}


object InputStreams {
  def inputStreamForCats(env: StreamExecutionEnvironment): DataStream[Cat] = {
    env.addSource((sc: SourceContext[Cat]) => {
      val names = Seq("Poochie", "Mojave", "Snagglepuss", "Marmalade", "Ms Wackpop", "Silhouette")
      val colors = Seq("black", "white", "light grey", "dark grey", "orange", "brown")
      val abilities = Seq("night vision", "barking", "stealth tactics", "cheetah speed", "super scratch", "none")
      while(true) {
        val nameInt = randomInt(0, 5)
        val colorInt = randomInt(0, 5)
        val abilityInt = randomInt(0, 5)
        val livesInt = randomInt(0,9)
        sc.collect(new Cat(names(nameInt), colors(colorInt), livesInt, abilities(abilityInt)))
        Thread.sleep(300)
      }
    })
  }
  
  def randomInt(start: Integer, end: Integer): Integer = {
    val r = new scala.util.Random
    return start + r.nextInt(( end - start) + 1);
  }
}
