package cn.wolfcode.netty.im.server.proxy.impl;

import cn.wolfcode.netty.im.constant.Constants;
import cn.wolfcode.netty.im.server.model.MessageBodyProto;
import cn.wolfcode.netty.im.server.model.MessageProto;
import cn.wolfcode.netty.im.server.model.MessageWrapper;
import cn.wolfcode.netty.im.server.proxy.MessageProxy;
import cn.wolfcode.netty.im.webserver.user.model.UserMessageEntity;
import cn.wolfcode.netty.im.webserver.user.service.UserMessageService;
import cn.wolfcode.netty.im.webserver.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author Leon
 */
public class MessageProxyImpl implements MessageProxy {
    private final static Logger log = LoggerFactory.getLogger(MessageProxyImpl.class);

    private UserMessageService userMessageService;

    public MessageProxyImpl(UserMessageService userMessageService) {
        this.userMessageService = userMessageService;
    }

    @Override
    public MessageWrapper convertToMessageWrapper(String sessionId, MessageProto.Model message) {
        switch (message.getCmd()) {
            case Constants.CmdType.BIND:
                try {
                    return new MessageWrapper(MessageWrapper.MessageProtocol.CONNECT, message.getSender(), null, message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.CmdType.HEARTBEAT:
                try {
                    return new MessageWrapper(MessageWrapper.MessageProtocol.HEART_BEAT, sessionId, null, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.CmdType.ONLINE:
                break;
            case Constants.CmdType.OFFLINE:
                break;
            case Constants.CmdType.MESSAGE:
                try {
                    MessageProto.Model.Builder result = MessageProto.Model.newBuilder(message);
                    result.setTimeStamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                    //存入发送人sessionId
                    result.setSender(sessionId);
                    message = MessageProto.Model.parseFrom(result.build().toByteArray());
                    //判断消息是否有接收人
                    if (StringUtils.isNotEmpty(message.getReceiver())) {
                        return new MessageWrapper(MessageWrapper.MessageProtocol.REPLY, sessionId, message.getReceiver(), message);
                    } else if (StringUtils.isNotEmpty(message.getGroupId())) {
                        return new MessageWrapper(MessageWrapper.MessageProtocol.GROUP, sessionId, null, message);
                    } else {
                        return new MessageWrapper(MessageWrapper.MessageProtocol.SEND, sessionId, null, message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        return null;
    }


    @Override
    public void saveOnlineMessageToDB(MessageWrapper message) {
        try {
            UserMessageEntity userMessage = convertMessageWrapperToBean(message);
            userMessage.setIsRead(1);
            userMessageService.save(userMessage);
        } catch (Exception e) {
            log.error("MessageProxyImpl  user " + message.getSessionId() + " send msg to " + message.getReSessionId() + " error");
            throw new RuntimeException(e.getCause());
        }
    }


    @Override
    public void saveOfflineMessageToDB(MessageWrapper message) {
        try {

            UserMessageEntity userMessage = convertMessageWrapperToBean(message);
            userMessage.setIsRead(0);
            userMessageService.save(userMessage);
        } catch (Exception e) {
            log.error("MessageProxyImpl  user " + message.getSessionId() + " send msg to " + message.getReSessionId() + " error");
            throw new RuntimeException(e.getCause());
        }
    }


    private UserMessageEntity convertMessageWrapperToBean(MessageWrapper message) {
        try {
            MessageProto.Model msg = (MessageProto.Model) message.getBody();
            MessageBodyProto.MessageBody msgContent = MessageBodyProto.MessageBody.parseFrom(msg.getContent());
            UserMessageEntity userMessage = new UserMessageEntity();
            userMessage.setSendUser(message.getSessionId());
            userMessage.setReceiveUser(message.getReSessionId());
            userMessage.setContent(msgContent.getContent());
            userMessage.setGroupId(msg.getGroupId());
            userMessage.setUuid(msg.getDeviceId());
            try {
                String timeStamp = msg.getTimeStamp();
                if (timeStamp != null) {
                    userMessage.setCreateDate(DateUtils.parse(timeStamp));
                }
            } catch (Exception e) {
                log.warn("[message proxy] 解析时间格式出错：" + msg.getTimeStamp(), e);
            }
            userMessage.setIsRead(1);
            return userMessage;
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
    }

    @Override
    public MessageProto.Model getOnLineStateMsg(String sessionId) {
        MessageProto.Model.Builder result = MessageProto.Model.newBuilder();
        result.setTimeStamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        //存入发送人sessionId
        result.setSender(sessionId);
        result.setCmd(Constants.CmdType.ONLINE);
        return result.build();
    }


    @Override
    public MessageProto.Model getOffLineStateMsg(String sessionId) {
        MessageProto.Model.Builder result = MessageProto.Model.newBuilder();
        result.setTimeStamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        //存入发送人sessionId
        result.setSender(sessionId);
        result.setCmd(Constants.CmdType.OFFLINE);
        return result.build();
    }


    @Override
    public MessageWrapper getReConnectionStateMsg(String sessionId) {
        MessageProto.Model.Builder result = MessageProto.Model.newBuilder();
        result.setTimeStamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        //存入发送人sessionId
        result.setSender(sessionId);
        result.setCmd(Constants.CmdType.RECON);
        return new MessageWrapper(MessageWrapper.MessageProtocol.SEND, sessionId, null, result.build());
    }


}
