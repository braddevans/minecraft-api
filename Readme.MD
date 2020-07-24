### Minecraft UUID/Name Api project
![Generic badge](https://img.shields.io/badge/spring_Version-2.3.1-red.svg?style=for-the-badge&logo=spring)
![Generic badge](https://img.shields.io/badge/project_language_level-java_11-green.svg?style=for-the-badge&logo=spring)
[![Generic badge](https://img.shields.io/badge/Spring_Share_Url-Click_Here-green.svg?style=for-the-badge&logo=spring)](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=2.3.1.RELEASE&packaging=jar&jvmVersion=11&groupId=uk.co.breadhub&artifactId=mcapi&name=mcapi&description=Minecraft%20UserName%2FUUID%20API&packageName=uk.co.breadhub.mcapi&dependencies=devtools,lombok,configuration-processor,session,web,thymeleaf,mustache,jdbc,mysql,batch,codecentric-spring-boot-admin-server)
![Generic badge](https://img.shields.io/badge/Maintainer-braddevans-blue.svg?style=for-the-badge)

### Api Endpoints: 

====================================================================== 
#####    /api/all            -   [Displays all users]
#####    /api/names/all      -   [Displays all Previous Names]
#####    /api/count          -   [Displays user count]
#####    /api/names/count    -   [Displays names count]

======================================================================
#####    /api/uuid/{uuid}         -   [Displays user by uuid]
#####    /api/name/{name}         -   [Displays user by name]

======================================================================
#####    /api/name/{name}/names   -   [Displays user's previous by name]
#####    /api/uuid/{uuid}/names   -   [Displays user's previous by uuid]

======================================================================


### Example Api Call
`localhost:8080/api/uuid/fcd17554303744f68e9206b3746285d0/names`
```json
[
   {
      "id":1,
      "name":"braddevans",
      "uuid":"fcd17554303744f68e9206b3746285d0",
      "changedToAt":0
   },
   {
      "id":2,
      "name":"Evil_King_Bradd_",
      "uuid":"fcd17554303744f68e9206b3746285d0",
      "changedToAt":1428101515000
   },
   {
      "id":3,
      "name":"xEvilAruOnline",
      "uuid":"fcd17554303744f68e9206b3746285d0",
      "changedToAt":1474590521000
   },
   {
      "id":4,
      "name":"Evil_King_Bradd_",
      "uuid":"fcd17554303744f68e9206b3746285d0",
      "changedToAt":1477944700000
   },
   {
      "id":5,
      "name":"braddevans",
      "uuid":"fcd17554303744f68e9206b3746285d0",
      "changedToAt":1482538261000
   },
   {
      "id":6,
      "name":"I_PeeBread",
      "uuid":"fcd17554303744f68e9206b3746285d0",
      "changedToAt":1492856827000
   },
   {
      "id":7,
      "name":"braddevans",
      "uuid":"fcd17554303744f68e9206b3746285d0",
      "changedToAt":1495570952000
   }
]
```
