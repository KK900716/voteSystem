[TOC]

------



## 1. 匿名投票系统需求

- 投票人包含系统所有者（以下简称所有者）、参与者
- 所有者拥有系统的开启、关闭权限
- 投票人可以给其他人进行打分

### 1.1 注意

- 投票人不能通过任何技术手段获取打分内容（包含所有者）
  - 投票人可以通过请求IP，获取请求人的相关信息和请求内容
- 投票人最终可以看到所有匿名打分后的情况

## 2. 解决方案

### 2.1 对称加密后将密钥和内容随机分发给其他人，提交作业

- 投票人下载客户端
- 所有者下载服务器
- 投票人在客户端填写信息
  - 客户端通过某种对称加密系统对打分信息进行加密
  - 客户端向随机不同客户端分发内容和密钥
  - 根据预先设置的加密复杂程度可将机密信息再次加密重复上一步
  - 客户端接收到最终的内容和密钥后向服务器发送内容和密钥
  - 服务器通过循环遍历最终解密信息，但服务器无法通过直接ip查询到提交人信息
- 服务器通过计算得到投票结果
- 通信采用https

### 2.2 补充

#### 问题：

如果每个客户端机器也安装了网络监控的软件，所有者是不还是能追溯到每一票在网络中转发的具体情况。

#### 解决方案：

- 可以向所有客户端发送信息，但只有向某随机的客户端发送的是真实信息，设计一个在该层不加密的布尔值用来标定收到信息是否真实
- 将该层信息打包发出时，可借鉴https协议模式，加密信息，保证数据传输的机密性，即便有网络监控软件抓包，也无法获得传输文本内容

## 3. 方案调研

### 3.1 同态加密

### 3.2 Tor | the onion routing project

![img](README.assets\screen_shot_1658816376886(1).png)

- 与互联网的路由器网络分开，建立一个独立路由器网络，可以加密流量，并且只存储前一个路由器的未加密IP地址，这意味着一路上所有其他路由器地址都加密了。他们的想法是，任何观察流量的人都无法确定数据的来源或目的地。
- tor 加密每个数据包的数据、目的地和发送方IP地址。在每一跳，接收到信息被加密，然后在下一跳被解密。这样，每个包只包含关于路径上的前一跳的信息，而不包含源IP地址。

### 3.3 区块链 零知识共识

- 在有必要证明一个命题是否正确，又不需要提示与这个命题相关的任何信息时，零知识证明系统是不可或缺的。零知识证明系统包括两部分：宣称某一命题为真的示证者(prover)和确认该命题确实为真的验证者(verifier)。证明是通过这两部分之间的交互来执行的。在零知识协议的结尾，验证者只有当命题为真时才会确认。但是，如果示证者宣称一个错误的命题，那么验证者完全可能发现这个错误。这种思想源自交互式证明系统。交互式系统在计算复杂度理论方面已经获得异常独立的地位。
  设P表示掌握某些信息，并希望证实这一事实的实体，设V是证明这一事实的实体。假如某个协议向V证明P的确掌握某些信息，但V无法推断出这些信息是什么，我们称P实现了最小泄露证明。不仅如此，如果V除了知道P能够证明某一事实外，不能够得到其他任何知识，我们称P实现了零知识证明，相应的协议称作零知识协议。

## 4. 原理剖析

- 匿名分析
  - 匿名是将 数据 和 发布人 的关系分开
  - 匿名的两个重要元素
    - 数据
    - 发布人
  - 如果不能同时拿到数据和发布人的信息，就等同于匿名
  - 即阻断同时拿到两个信息的途径
- 阻断拿到数据
  - 可使用某种加密算法
  - 但计算端总要拿到数据进行计算，故很难让计算端即不知道数据是什么又完成计算，这里可以使用同态加密算法
  - 但如果计算端一定要获得加密的数据，则必须考虑阻断计算端知道数据的来源即发布人信息
- 阻断拿到发布人信息
  - 互联网OSI参考模型中的网络层规定了，端到端发送数据包，要向外暴漏自己的源IP和目的IP，通过访问网关的路由表映射（端口ip与mac地址的映射关系表），不断的将数据包转发到目的IP，这样目的端一定能通过解析该报文报头（IP报头）来得到源IP
  - 通过对OSI参考模型的分析，我们可以知道，我们很难在现有互联网上通过已有协议屏蔽源IP，除非我们拒绝采用IP协议规定，显然这是很难实现的，需要我们重写从网络层以上的所有协议
  - 但通过分析，我们可以再应用层实现一个类似IP协议规定的新的协议，代价仅仅是每个客户机需要下载相应的解析客户端即可，这样既可以使用现有的链路网络，又可以实现匿名投票，即本方案

## 5. 设计

### 5.1 客户端

#### 5.1.1 远程交互服务器link-server

- port：10003
- 获取得票人的信息
- 发送信息
  - 发送得票人信息映射在webBack
  - 发送加密密文以及密钥给服务器

#### 5.1.2 后端界面webBack

- 获取得票人信息
- 进行获取web上的投票得分的表单
- 发送得票人以及得分情况的表单给encryption

#### 5.1.3 加密模块encryption

- port：10001
- 加密表单
- 发送密文给vote-dispatch

#### 5.1.4 分发服务器dispatch

- port：10002

- 随机分发密钥和密文给其他客户端
- 接受密钥和密文发送给link-server

#### 5.1.5 其他注意

- 所有抛出异常均在webBack模块进行处理，返回给前端必要的展示
- 在项目建设阶段为简便调试起见，先在各模块service处理异常，以布尔类型判断是否调用成功，失败一律用false
- 在项目完善阶段，可进一步设计枚举类型，映射各种可能出现的异常
- 投票系统包括管理员在内，必须至少有4人在线才能实现算法

### 5.2 服务器

- port：9999