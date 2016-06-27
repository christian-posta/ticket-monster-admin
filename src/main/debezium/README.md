Install ZK
Install Kafka
Install connect

Port forward locally 8083


For the database to which we connect Debezium, we need to make sure it has the appropriate grants:

Example:

```
GRANT SELECT, RELOAD, SHOW DATABASES, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'replicator' IDENTIFIED BY 'replpass';
```


Create connector:

```
curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" http://localhost:8083/connectors/ -d @tmadmin-connector.json
```

Delete connection:

```
curl -i -X DELETE -H "Accept:application/json" http://localhost:8083/connectors/tmadmin-connector
```
