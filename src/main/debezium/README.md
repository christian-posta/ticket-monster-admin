Install ZK
Install Kafka
Install connect

Port forward locally 8083


Create connector:
curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" http://localhost:8083/connectors/ -d @tmadmin-connector.json

Delete connection:

curl -i -X DELETE -H "Accept:application/json" http://localhost:8083/connectors/tmadmin-connector
