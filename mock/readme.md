#   docker起zookeeper + kafka

    > ./genSecret.sh     
    > docker-compose up -d

#   本地起 zookeeper + Kafka 

    我们需要安装 zookeeper + kafka(version:2.13-2.7.0), 不要安装kafka新版本,因为新版本不使用zookeeper

#   zookeeper

##  安装
    解压apache-zookeeper-3.8.0-bin.tar.gz   e.g.: C:\zookeeper\
    将C:\zookeeper\conf 下的zoo_sample.cfg 复制为 zoo.cfg, 修改
        dataDir=C:\\zookeeper\\data         //数据库文件
        clientPort=2182                     //我把端口改为2181->2182,以免与docker上产生冲突
        tickTime: 心跳间隔
        initLimit=10            //leader与follower初始心跳间隔，超过间隔说明他们之间失联
        syncLimit=5             //leader与follower同步心跳间隔
    删除数据: /data/ 下数据删除即可

##  启动
    > cd C:\zookeeper\bin
    > 双击zkServer.cmd                        //启动zookeeper服务器端    zkServer.cmd start/stop
    > zkServer.sh status                     //查看启动状态
    > zkCli.cmd -server 127.0.0.1:2182       //启动客户端，由于我改了端口

##  客户端命令

|          Command          | Description |             Remark             |
|:-------------------------:|:-----------:|:------------------------------:|
|           ls /            | 显示某个目录下所有节点 |        e.g.: ls /sanguo        |
|           quit            |    退出客户端    |                                |
|  create /[path] [value]   |   创建一个节点    | e.g.: create /sanguo/suguo 123 |
| create -e /[path] [value] | 创建一个短暂属性的节点 |          客户端断掉后path消失          |
| create -s /[path] [value] | 创建一个有序号的节点  |       e.g.: /sanguo00002       |
|        get /[path]        |   获取一个节点    |    e.g.: get /sanguo/suguo     |
|     get /[path] watch     |  获取一个节点带监听  |    当这个值被其他zookeeper修改后会监听到 |
|      delete /[path]       |   删除一个节点    |                                |
|       rmr /[path]         |    递归删除     |                                |
|       stat /[path]        |  查看一个节点的属性  |                                |
|    set /[path] [value]    |   修改一个节点    |                                |

##  Docker下操作zookeeper

#   Kafka

##  安装
    1.  我下载的是老版本，使用zookeeper数据： kafka_2.13-2.7.0.tgz 
    2.  配置C:\kafka\config\server.properties:
            broker.id=1                             //kafka线程名,每台服务器需要不同的broker ID
            delete.topic.enable=true                //可以删除topic
            listeners=PLAINTEXT://localhost:9093    //我改了端口 9092-> 9093, 以免和Docker冲突
            log.dirs=C:\\kafka\\data                //其实是kafka数据
            zookeeper.connect=localhost:2182        //真实情况应该配 server1.2181,server2.2181...
    3.  起kafka-> 注意必须先起zookeeper
        > cd C:\Users\I035299\applications\kafka\bin\windows
        > kafka-server-start.bat ../../config/server.properties
    4.  创建topic
        > kafka-topics.bat --create --zookeeper localhost:2182 --replication-factor 1 --partitions 1 --topic test-topic
    5.  查看topics
        > kafka-topics.bat --list --zookeeper localhost:2182
    6.  启动生产者
        > kafka-console-producer.bat --broker-list localhost:9093 --topic test-topic
        输入消息
    7.  启动消费者
        > kafka-console-consumer.bat --bootstrap-server localhost:9093 --topic test-topic --from-beginning
        接收到消息

##  Docker access Kafka

    > docker exec -it local_kafka-service_1 /bin/bash    // access docker Kafka container
    > cd opt/kafka

#   postgresql

    >docker exec -it local_postgres_1 psql -U postgres
    >\l             // show databases
    >\c postgres    // use db postgres
    >\dt            // show tables
