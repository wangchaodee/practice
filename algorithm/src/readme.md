包结构说明 
com.iflytek.staff.chao  
    protocol  协议层面算法  
    structure    
        base 常见数据结构   模型 包含通用方法  
        scene 按场景设计特定数据结构  解决特定问题  
    algorithm  算法    
        按不同数据结构 或者 算法场景 命名算法类，汇聚常见的解题  
    util  抽取通用的公共方法  
    conc 并发模拟   
    interview 一些特定面试记录及其相应解题  
    solution 一些特定问题的解决方式

类设计
  将公共通用方法 通用成员变量 放上面 
  按场景解题的方法原则上放相邻位置 