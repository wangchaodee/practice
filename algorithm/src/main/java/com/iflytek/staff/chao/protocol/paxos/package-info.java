/**
 * @Description: 模拟paxos 算法
 * @author : wangchaodee
 * @date Date : 2023年01月05日 20:13
 */
package com.iflytek.staff.chao.protocol.paxos;

// 分布式情形下 多节点（节点可能故障） ，在不可靠的网络情形下 ， 达成一致性的算法协议

// 节点 议员   节点会有不同的角色  ， 能存数据， 能够发起消息传递 能够模拟节点故障
// 传输通道 消息传送  进行消息传递   ， 能够模拟丢失消息  重复发送
// 请求者  模拟消息的发起 可调用一个节点 认可的消息为 法案   被多数节点认可通过才会认可记录，否则会标记废弃
// 灾难制造者   随机制造节点故障  网络故障

/**
 * 达成一致性的条件
 * B1（b）：b中的每次投票都有一个独一无二的投票编号
 * B2（b）：b中的任意两次投票的法定人数的交集不为空，即：任意两次投票的法定人数至少有一个共同的牧师。
 * B3（b）：对于b中的每次投票B，如果B法定人数中的牧师在集合b早期的投票中进行了投票，那么，B的法令等于这些早期投票中最晚投票的法令。
 */


//场景
