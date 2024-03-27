# UE4 配置文件

### 文件格式
使用ini文件来表示配置

![1711548923705](https://github.com/3161906110/zhk/assets/46776443/1840e946-a36d-40eb-92ac-5292c9ff2d90)

````
[/Script/Engine.DemoNetDriver]
NetConnectionClassName="/Script/Engine.DemoNetConnection"
DemoSpectatorClass=Engine.PlayerController
SpawnPrioritySeconds=60.0
!ChannelDefinitions=CLEAR_ARRAY
+ChannelDefinitions=(ChannelName=Control, ClassName=/Script/Engine.ControlChannel, StaticChannelIndex=0, bTickOnCreate=true, bServerOpen=false, bClientOpen=true, bInitialServer=false, bInitialClient=true)
+ChannelDefinitions=(ChannelName=Actor, ClassName=/Script/Engine.ActorChannel, StaticChannelIndex=-1, bTickOnCreate=false, bServerOpen=true, bClientOpen=false, bInitialServer=false, bInitialClient=false)
````
 分段[Section]
 
[/Script/Engine.RendererSettings]

/Script/Engine是模块名，RendererSettings是类名

![1711548761232](https://github.com/3161906110/zhk/assets/46776443/1098cf16-cb86-447c-9f23-1c1a7460dc4c)

键-值对

