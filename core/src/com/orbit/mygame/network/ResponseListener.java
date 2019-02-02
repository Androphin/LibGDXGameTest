package com.orbit.mygame.network;

import com.badlogic.gdx.Net;

public class ResponseListener implements Net.HttpResponseListener {
    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        System.out.print(httpResponse.getResultAsString());
    }

    @Override
    public void failed(Throwable t) {
        System.out.print( t.getMessage() );
    }

    @Override
    public void cancelled() {
        System.out.print( "canceled" );
    }
}
