# spring-kafka-producer
Sample spring boot project for producing message to confluent kafka

# sample rest api call
curl -i \
-d '{"libraryEventId":111,"book":{"bookId":1,"bookName":"Durbin","bookAuthor":"Shirshendu Mukhopadhyay"}}' \
-H "Content-Type: application/json" \
-X POST http://localhost:9000/v1/libraryevent
