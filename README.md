# 斗地主发牌模拟&概率计算

自己编写的斗地主发牌程序，通过大量的实验统计天王炸等牌型出现的频率，佐证理论计算结果。

## 环境&依赖

* jdk1.8
* poi3.17

idea中直接导入maven项目即可完成初始构建。

## Demo示例

在源代码*com.jzy.demo*包下有一些示例程序。

## 注意事项

* 根目录下的word文档为天王炸出现的频率计算推导过程。

* 概率测试实验中，给定测试次数越大，实验结果与理论计算结果越接近（实验次数足够多的时候频率越接近概率），但实验用时也越长。

* 根目录的output_example文件夹下有输出实验详细结果的示例。

  * 由于当实验次数较大时，如果输出每次发牌模拟的中间结果，**输出的txt会非常大几个G，且用一般的文本编辑器无法打开**，所以示例txt中100w的实验仅放了最终统计结果。

  * 一副牌三人斗地主天王炸概率实验结果：

    ```
    =================实验结果=================
    实验次数: 1000000
    全场天王炸（叫地主前）出现次数: 284954，出现概率: 28.495%，平均每3.51把出现一次。全场天王炸（叫地主后）出现次数: 393727，出现概率: 39.373%，平均每2.54把出现一次。
    player1天王炸（叫地主前）出现次数: 94922，出现概率: 9.492%，平均每10.53把出现一次。player1天王炸（叫地主后）出现次数: 132560，出现概率: 13.256%，平均每7.54把出现一次。
    player2天王炸（叫地主前）出现次数: 94843，出现概率: 9.484%，平均每10.54把出现一次。player2天王炸（叫地主后）出现次数: 132480，出现概率: 13.248%，平均每7.55把出现一次。
    player3天王炸（叫地主前）出现次数: 95189，出现概率: 9.519%，平均每10.51把出现一次。player3天王炸（叫地主后）出现次数: 132949，出现概率: 13.295%，平均每7.52把出现一次。
    平均每个玩家天王炸（叫地主前）概率: 9.498%，平均每10.53把出现一次。平均每个玩家天王炸（叫地主后）概率: 13.266%，平均每7.54把出现一次。
    每个玩家天王炸（叫地主前）概率理论值: 9.504%，平均每10.52把出现一次。每个玩家天王炸（叫地主后）概率理论值: 13.277%，平均每7.53把出现一次。
    ```

  * 两副牌四人斗地主天王炸概率实验结果：

    ```
    =================实验结果=================
    实验次数: 1000000
    全场天王炸（叫地主前）出现次数: 9578，出现概率: 0.958%，平均每104.41把出现一次。全场天王炸（叫地主后）出现次数: 30550，出现概率: 3.055%，平均每32.73把出现一次。
    player1天王炸（叫地主前）出现次数: 2268，出现概率: 0.227%，平均每440.92把出现一次。player1天王炸（叫地主后）出现次数: 7576，出现概率: 0.758%，平均每132.00把出现一次。
    player2天王炸（叫地主前）出现次数: 2399，出现概率: 0.240%，平均每416.84把出现一次。player2天王炸（叫地主后）出现次数: 7733，出现概率: 0.773%，平均每129.32把出现一次。
    player3天王炸（叫地主前）出现次数: 2482，出现概率: 0.248%，平均每402.90把出现一次。player3天王炸（叫地主后）出现次数: 7686，出现概率: 0.769%，平均每130.11把出现一次。
    player4天王炸（叫地主前）出现次数: 2429，出现概率: 0.248%，平均每402.90把出现一次。player4天王炸（叫地主后）出现次数: 7597，出现概率: 0.760%，平均每130.11把出现一次。
    平均每个玩家天王炸（叫地主前）概率: 0.239%，平均每417.62把出现一次。平均每个玩家天王炸（叫地主后）概率: 0.765%，平均每130.75把出现一次。
    每个玩家天王炸（叫地主前）概率理论值: 0.236%，平均每423.73把出现一次。每个玩家天王炸（叫地主后）概率理论值: 0.764%，平均每130.96把出现一次。
    ```