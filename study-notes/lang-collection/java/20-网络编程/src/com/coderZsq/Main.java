package com.coderZsq;

public class Main {

    public static void main(String[] args) {
        /*
         * 互联网(internet)
         *
         * 互联网的出现，彻底改变了人们的生活方式，足不出户就可以购物、聊天、看电影、买车票、上班等
         *
         * 思考:数据是如何从一个设备传递到另一个设备的?
         * */

        /*
         * 网络互连模型
         * 为了更好地促进互联网络的研究和发展，国际标准化组织 ISO 在 1985 年制定了网络互连模型
         * OSI 参考模型(Open System Interconnect Reference Model)，具有 7 层结构
         *
         * 7 应用层(Application)     4应用层 (Application)         5 应用层(Application)
         * 6 表示层(Presentation)
         * 5 会话层(Session)
         * 4 运输层(Transport)       3运输层(Transport)            4 运输层(Transport)
         * 3 网络层(Network)         2网际层(Internet)             3 网络层(Network)
         * 2 数据链路层(Data Link)   1网络接口层 (Network Access)   2 数据链路层(Data Link)
         * 1 物理层(Physical)                                     1 物理层(Physical)
         * OSI 参考模型              TCP/IP 协议                   学习研究
         * */

        /*
         * TCP/IP 协议
         *
         * TCP/IP 协议，简称 TCP/IP
         * 是一个网络通信模型，以及一整个网络传输协议家族，为网际网络的基础通信架构
         *
         * TCP/IP 不仅仅指的是 TCP 和 IP 两个协议
         * 是指一个由 FTP、SMTP、TCP、UDP、IP 等协议构成的协议家族
         * TCP、IP 协议是该协议家族中最早通过的标准，所以称为 TCP/IP
         *
         * TCP:Transmission Control Protocol，传输控制协议
         * IP:Internet Protocol，网际协议
         * */

        /*
         * 网络分层
         * 5 应用层(Application)   FTP、HTTP、SMTP DNS TFTP、DHCP 报文、用户数据
         * 4 运输层(Transport)     TCP、UDP                       段(Segments)
         * 3 网络层(Network)       IP                            包(Packet)
         * 2 数据链路层(Data Link)  MAC                           帧(Frames)
         * 1 物理层(Physical)                                    比特流(Bits)
         * */

        /*
         * HTTP 请求过程
         *
         * 客户端                路由器                 服务器
         * 5 应用层         5                    5     5 应用层
         * 4 运输层        45                   45     4 运输层
         * 3 网络层       345   3 网络层        345     3 网络层
         * 2 数据链路层   23452  2 数据链路层   23452    2 数据链路层
         * 1 物理层     123452  1 物理层      123452    1 物理层
         * */
    }
}
