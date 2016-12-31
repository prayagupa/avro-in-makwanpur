[avro in manchester](https://en.wikipedia.org/wiki/Avro)
-----------------------

this is an avro example to show serialisation and deserialisation, would like to use 
 avro for my client server architecture where I would be sending huge
 dataset to the server in each request, which might be a good use case for avro+http/netty

The data schema/protocol(av pr) is 

```json
{"namespace": "divinen.avro",
 "type": "record",
 "name": "Item",
 "fields": [
     {"name": "itemId", "type": "string"},
     {"name": "availableQuantity",  "type": ["int"]},
     {"name": "distributionCenterId", "type": ["string", "null"]}
 ]
}
```

from which the java/scala whatever classes can be generated, using `mvn clean compile`

And then, I want to send the items to http+avro endpoint. In following one
I am writing to a filesystem.

```scala
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
```