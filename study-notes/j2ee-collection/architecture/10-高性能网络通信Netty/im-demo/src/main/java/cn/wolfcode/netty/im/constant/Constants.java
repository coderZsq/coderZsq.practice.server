package cn.wolfcode.netty.im.constant;

import io.netty.util.AttributeKey;

public class Constants {

    public interface WebSite {
        int SUCCESS = 0;
        int ERROR = -1;
    }

    public interface ImServerConfig {
        //连接空闲时间（秒）
        int READ_IDLE_TIME = 60 * 3;
        //发送心跳包循环时间（秒）
        int WRITE_IDLE_TIME = 40;
        //心跳响应 超时时间（秒），需要大于空闲时间
        int PING_TIME_OUT = 60 * 30;

        // 最大协议包长度(10k)
        int MAX_FRAME_LENGTH = 1024 * 10;
        // 最大聚合内容长度
        int MAX_AGGREGATED_CONTENT_LENGTH = 65536;

        // websocket 标识
        int WEBSOCKET = 1;

        //socket标识
        int SOCKET = 0;
    }

    public interface SessionConfig {
        String SESSION_KEY = "account";
        AttributeKey<String> SERVER_SESSION_ID = AttributeKey.valueOf(SESSION_KEY);
        AttributeKey<Long> SERVER_SESSION_HEARTBEAT = AttributeKey.valueOf("heartbeat");
    }

    public interface ProtoBufType {
        byte SEND = 1; //请求
        byte RECEIVE = 2; //接收
        byte NOTIFY = 3; //通知
        byte REPLY = 4; //回复
    }

    public interface CmdType {
        byte BIND = 1; //绑定
        byte HEARTBEAT = 2; //心跳
        byte ONLINE = 3; //上线
        byte OFFLINE = 4; //下线
        byte MESSAGE = 5; //消息
        byte RECON = 6; //重连
    }

}
