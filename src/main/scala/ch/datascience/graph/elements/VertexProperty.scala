package ch.datascience.graph.elements

/**
  * Created by johann on 27/04/17.
  */
abstract class VertexProperty[Key, Value : ValidValue, MetaKey] extends Property[Key, Value] {

  val metaProperties: Map[Key, Property[MetaKey, BoxedValue]]

}
