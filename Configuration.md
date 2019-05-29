## Configuration of the Chatapp

Install docker
pull and run the following image

`docker run -d --hostname my-rabbit --name rabbitmqSTOMP -p 15672:15672 -p 5672:5672 -p 61613:61613 itzg/rabbitmq-stomp`

The default credentials are guest, password: guest
The RabbitMQ Port is 5672
The Management UI port is 15672
The STOMP Port (important for the Spring App) is 61613

Now rabbitmq is up and running at localhost:15672, you can verify by navigating to localhost:15672 in the browser, rabbitmq login mask should appear

Now you can set the properties with kdb

`kdb set user/chatapp/applicationDestPrefix`

`Kdb set user/chatapp/broker/name`

`Kdb set user/chatapp/broker/host`

`Kdb set user/chatapp/broker/port`

`Kdb set user/chatapp/broker/login`

`Kdb set user/chatapp/broker/pw`

`applicationDestPrefix specifies the app prefix in the broker it is usually, „/<any word>“ e.g. /app`

`name is the topic, where to the chat app subscribes, it has to be created in RabbitMQ interface first, or you can use the default topic „/<any word>“ e.g. /topic`

`the host is the IP address or name of where the rabbitMQ instance is running usually localhost if using docker`

`port is the stomp port of the rabbitMQ instance (not the rabbitMQ port), default with the docker image is 61613`

`login is the username for rabbitmq`

`pw is the username for rabbitmq`$

Once configured you can run the project with mvn spring-boot:run

Open two browser windows with localhost:8080 and loginto the chat app with any username you should  be able to send messages from one window to another. Also check in rabbitMQ Interface localhost:15672 the Queue Status

Try to create your own topics and queues and play around with the username and password settings, create a user in RabbitMQ and try to set it via kdb
