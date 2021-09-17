package io.joyrpc.protocol.telnet.handler;

/*-
 * #%L
 * joyrpc
 * %%
 * Copyright (C) 2019 joyrpc.io
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import io.joyrpc.codec.Hex;
import io.joyrpc.codec.crypto.Encryptor;
import io.joyrpc.codec.crypto.Signature;
import io.joyrpc.transport.channel.Channel;
import io.joyrpc.transport.telnet.TelnetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static io.joyrpc.Plugin.ENCRYPTOR;
import static io.joyrpc.constants.Constants.*;
import static io.joyrpc.context.Variable.VARIABLE;
import static io.joyrpc.util.StringUtils.isEmpty;

/**
 * @date: 2019/1/22
 */
public class SudoTelnetHandler extends AbstractTelnetHandler {

    protected static final Logger logger = LoggerFactory.getLogger(SudoTelnetHandler.class);

    @Override
    public String type() {
        return "sudo";
    }

    @Override
    public String description() {
        return "Usage:\tsudo [password]" + LINE + "Open some superuser's function. Example: sudo xxxpassword" + LINE;
    }

    @Override
    public String shortDescription() {
        return "Open some superuser's function. Example: sudo xxxpassword.";
    }

    @Override
    public TelnetResponse telnet(final Channel channel, final String[] args) {
        if (args == null || args.length == 0 || isEmpty(args[0])) {
            return new TelnetResponse(help());
        } else {
            //密码
            String password = VARIABLE.getString(SETTING_SERVER_SUDO_PASSWD);
            //获取加密算法
            String encryptorType = VARIABLE.getString(SETTING_SERVER_SUDO_CRYPTO, SUDO_CRYPTO_TYPE);
            //加密算法
            Encryptor encryptor = ENCRYPTOR.get(encryptorType);
            //获取加密秘钥
            String cryptoKey = VARIABLE.getString(SETTING_SERVER_SUDO_CRYPTO_KEY);
            if (isEmpty(password)) {
                //没有设置密码
                return new TelnetResponse("Failure, token is not configured.");
            } else if (isEmpty(cryptoKey)) {
                return new TelnetResponse("Failure, cryptoKey is not configured.");
            } else if (encryptor == null) {
                return new TelnetResponse("Failure, encryptor is not found.");
            } else {
                try {
                    byte[] sources = args[0].getBytes(StandardCharsets.UTF_8);
                    byte[] keys = Hex.decode(cryptoKey);
                    byte[] signs = Hex.decode(password);
                    boolean result = encryptor instanceof Signature ? ((Signature) encryptor).verify(sources, keys, signs)
                            : Arrays.equals(encryptor.encrypt(sources, keys), signs);
                    //校验密码
                    if (result) {
                        channel.setAttribute(SUDO_ATTRIBUTE, Boolean.TRUE);
                        return new TelnetResponse("Success");
                    } else {
                        // 增加密码重试限制？
                        return new TelnetResponse("Failure, wrong password!");
                    }
                } catch (Exception e) {
                    logger.error("Error occurs while executing sudo.", e);
                    //打印异常堆栈信息
                    return new TelnetResponse("Error occurs while executing sudo. " + e.getMessage());
                }
            }

        }
    }
}
