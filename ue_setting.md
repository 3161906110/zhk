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

### 配置路径

 - \Engine\Config\
 - \Engine\Saved\Config\ (运行后生成)
 - [ProjectName]\Config\
 - [ProjectName]\Saved\Config (运行后生成)
   
打包文件

 - Development/Debug 打包目录\项目名称\Saved\Config\WindowsNoEditor
 - Shipping C:\Users\用户名\AppData\Local\项目名称\Saved\Config\WindowsNoEditor

同种类别在不同路径(项目和平台)可能有一份ini文件，如Engine类别

1. Engine/Config/Base.ini
2. Engine/Config/BaseEngine.ini
3. Engine/Config/[Platform]/[Platform]Engine.ini
4. [ProjectDirectory]/Config/DefaultEngine.ini
5. [ProjectDirectory]/Config/[Platform]/[Platform]Engine.ini
6. [ProjectDirectory]/Saved/Config/[Platform]/Engine.ini

主要根据引擎的GConfigLayers定义

![image](https://github.com/3161906110/zhk/assets/46776443/ee28fd7c-db62-405a-a7a3-8fade4b8397e)

读取某种类型配置文件信息的时候，会按照上面的路径依次读取，层级越高里面的配置信息优先级越高。

### 配置读写

通过section、key和配置类别来写入或者读取对应的值

![image](https://github.com/3161906110/zhk/assets/46776443/fbb80c7b-b7cc-4dfc-b816-06201090538d)

![image](https://github.com/3161906110/zhk/assets/46776443/c5ff4e25-9379-4217-b9a9-da98ce116195)


### 通过反射系统读写
待补充
