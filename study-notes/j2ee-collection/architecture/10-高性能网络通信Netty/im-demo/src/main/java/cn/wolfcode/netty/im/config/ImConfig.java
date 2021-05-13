package cn.wolfcode.netty.im.config;

import cn.wolfcode.netty.im.server.ImWebsocketServer;
import cn.wolfcode.netty.im.server.connertor.ImConnector;
import cn.wolfcode.netty.im.server.connertor.impl.ImConnectorImpl;
import cn.wolfcode.netty.im.server.proxy.MessageProxy;
import cn.wolfcode.netty.im.server.proxy.impl.MessageProxyImpl;
import cn.wolfcode.netty.im.server.session.SessionManager;
import cn.wolfcode.netty.im.server.session.impl.SessionManagerImpl;
import cn.wolfcode.netty.im.webserver.user.service.UserMessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Leon
 */
@Configuration
public class ImConfig {

    @Bean
    public ImConnector imConnector(SessionManager sessionManager, MessageProxy messageProxy) {
        ImConnectorImpl imConnector = new ImConnectorImpl();
        imConnector.setSessionManager(sessionManager);
        imConnector.setProxy(messageProxy);
        return imConnector;
    }

    @Bean
    public MessageProxy messageProxy(UserMessageService userMessageService) {
        return new MessageProxyImpl(userMessageService);
    }

    @Bean
    public SessionManager sessionManager(MessageProxy messageProxy) {
        SessionManagerImpl sessionManager = new SessionManagerImpl();
        sessionManager.setProxy(messageProxy);
        return sessionManager;
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public ImWebsocketServer imWebsocketServer(ImConnector imConnector, MessageProxy messageProxy) {
        ImWebsocketServer imWebsocketServer = new ImWebsocketServer();
        imWebsocketServer.setConnector(imConnector);
        imWebsocketServer.setProxy(messageProxy);
        imWebsocketServer.setPort(2048);
        return imWebsocketServer;
    }
}
