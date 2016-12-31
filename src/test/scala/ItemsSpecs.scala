import java.io.File

import divinen.avro.Item
import org.apache.avro.file.{DataFileReader, DataFileWriter}
import org.apache.avro.io.DatumWriter
import org.apache.avro.specific.{SpecificDatumReader, SpecificDatumWriter}
import org.scalatest.FunSuite

import scala.collection.mutable.ListBuffer

/**
  * Created by prayagupd
  * on 12/30/16.
  */

class ItemsSpecs extends FunSuite {

  test("serialises and deserialises the items") {

    //given
    val shirts = Item.newBuilder()
      .setItemId("shirts")
      .setAvailableQuantity(100)
      .setDistributionCenterId("cedar rapids")
      .build()

    val pants = Item.newBuilder()
      .setItemId("pants")
      .setAvailableQuantity(100)
      .setDistributionCenterId("cedar rapids")
      .build()

    val caps = Item.newBuilder()
      .setItemId("caps")
      .setAvailableQuantity(100)
      .setDistributionCenterId("cedar rapids")
      .build()

    //when
    val sink = new DataFileWriter[Item](new SpecificDatumWriter[Item](classOf[Item]))
    sink.create(shirts.getSchema(), new File("items.avro"))
    sink.append(shirts)
    sink.append(pants)
    sink.append(caps)
    sink.close()

    //then
    val source = new DataFileReader[Item](new File("items.avro"), new SpecificDatumReader[Item](classOf[Item]))
    val actualItems = new ListBuffer[Item]

    while (source.hasNext()) {
      val item = source.next()
      actualItems.+=(item)
      println(item)
    }

    assert(List(shirts, pants, caps).map(x => x.getItemId) == actualItems.map(x => x.getItemId.toString).toList)
    assert(List(shirts, pants, caps).map(x => x.getAvailableQuantity) == actualItems.map(x => x.getAvailableQuantity).toList)
    assert(List(shirts, pants, caps).map(x => x.getDistributionCenterId) == actualItems.map(x => x.getDistributionCenterId.toString).toList)
  }
}
