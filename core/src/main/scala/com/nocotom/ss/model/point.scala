package com.nocotom.ss

import scala.math.ScalaNumber

package object point {

  case class TimePoint(timestamp: Long) {

    override def toString: String = s"TimePoint{timestamp='$timestamp'}"
  }

  case class DataPoint[T <: ScalaNumber](timestamp: Long, value: T) {

    override def toString: String = s"DataPoint{timestamp='$timestamp', value='$value'}"
  }

  case class KeyedDataPoint[T <: ScalaNumber](timestamp: Long, value: T, key : String) {

    override def toString: String = s"KeyedDataPoint{timestamp='$timestamp', value='$value', key='$key'}"
  }

  implicit class RichTimePoint(timePoint: TimePoint){
    def withValue[T <: ScalaNumber](value: T) : DataPoint[T] = {
      DataPoint[T](timePoint.timestamp, value)
    }
  }

  implicit class RichDataPoint[T <: ScalaNumber](dataPoint: DataPoint[T]){
    def withNewValue(newValue: T): DataPoint[T] = {
      DataPoint(dataPoint.timestamp, newValue)
    }

    def withNewKeyAndValue(key: String, newValue: T): KeyedDataPoint[T] = {
      KeyedDataPoint(dataPoint.timestamp, newValue, key)
    }

    def withKey(key: String): KeyedDataPoint[T] ={
      KeyedDataPoint(dataPoint.timestamp, dataPoint.value, key)
    }
  }

  implicit class RichKeyedDataPoint[T <: ScalaNumber](keyedDataPoint: KeyedDataPoint[T]){
    def withNewValue(value: T) : KeyedDataPoint[T] = {
      KeyedDataPoint[T](keyedDataPoint.timestamp, value, keyedDataPoint.key)
    }
  }

}
