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

![1711549099215](https://github.com/3161906110/zhk/assets/46776443/f62ea85f-9efb-40ca-9adc-e71d46b84529)

键-值对 key=value

SpawnPrioritySeconds=60.0

### 配置类别

拆分成不同类别，方便编辑和使用

 - Compat（兼容性）
 - DeviceProfiles（设备概述文件）
 - Editor（编辑器）
 - EditorGameAgnostic（编辑器游戏未知）
 - EditorKeyBindings（编辑器按键绑定）
 - EditorUserSettings（编辑器用户设置）
 - Engine(引擎)
 - Game(游戏)
 - Input（输入）
 - Lightmass（全局光照）
 - Scalability（可扩展性）
