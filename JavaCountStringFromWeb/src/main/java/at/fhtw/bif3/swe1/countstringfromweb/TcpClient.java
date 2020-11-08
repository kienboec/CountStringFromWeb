package at.fhtw.bif3.swe1.countstringfromweb;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TcpClient implements TcpClientInterface {
    private Socket socket = null;

    @Override
    public void connect(String hostname, int port)  {
        if( socket == null )
            socket = new Socket();
        try {
            socket.connect(new InetSocketAddress( hostname, port ) );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void connectSsl(String hostname, int port) {
        try {
            if( socket == null ) {
                SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
                socket = factory.createSocket();
            }
            socket.connect(new InetSocketAddress( hostname, port ) );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public InputStream getStreamRead() {
        try {
            return (socket !=null) ? socket.getInputStream() : null;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public OutputStream getStreamWrite() {
        try {
            return (socket !=null) ? socket.getOutputStream() : null;
        } catch (IOException e) {
            return null;
        }
    }
}
