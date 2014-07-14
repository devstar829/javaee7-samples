package org.javaee7.websocket.encoder.programmatic;

import java.io.StringReader;
import javax.json.Json;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * @author Arun Gupta
 */
public class MyMessageDecoder implements Decoder.Text<MyMessage> {

    @Override
    public MyMessage decode(String string) throws DecodeException {
        MyClient.latch.countDown();
        MyMessage myMessage = new MyMessage(Json.createReader(new StringReader(string)).readObject());
        return myMessage;
    }

    @Override
    public boolean willDecode(String string) {
        return true;
    }
    
    @Override
    public void init(EndpointConfig ec) { }

    @Override
    public void destroy() { }
}
