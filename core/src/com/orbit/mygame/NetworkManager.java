package com.orbit.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.orbit.mygame.network.Connector;
import com.orbit.mygame.network.RequestBinary;
import com.orbit.mygame.network.ResponseListener;

import java.io.OutputStream;

import javax.net.ssl.SSLContext;

public final class NetworkManager {

    final MyGame app;

    private SSLContext sslContext;

    //default HTTP response listener
    Net.HttpResponseListener listener;

    public NetworkManager(final MyGame app){
        this.app = app;
        this.listener = new ResponseListener();

        HttpRequestBuilder builder = new HttpRequestBuilder();
        builder.newRequest();
        builder.method("POST");
        builder.url("https://localhost:10123/login");
        builder.timeout(2000);
        builder.content("{\"username\"=\"player1\", \"password\"=\"test\"}");
        Net.HttpRequest request = builder.build();

        Net.HttpResponseListener listen = new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                System.out.print( httpResponse.getStatus().getStatusCode() );
                System.out.print( httpResponse.getResultAsString() );
            }

            @Override
            public void failed(Throwable t) {
                System.out.print("Error: "+t);
            }

            @Override
            public void cancelled() {
                System.out.print("canceled");
            }
        };

        Connector net = new Connector();
        net.sendHttpRequest( request, listen );
    }


    public void send(Net.HttpRequest request){
        Gdx.net.sendHttpRequest(request, this.listener);
    }
    public void send(Net.HttpRequest request, Net.HttpResponseListener listener){
        Gdx.net.sendHttpRequest(request, listener);
    }

}
