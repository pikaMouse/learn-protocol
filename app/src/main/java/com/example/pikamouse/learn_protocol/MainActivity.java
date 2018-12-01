package com.example.pikamouse.learn_protocol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pikamouse.learn_protocol.callback.RequestCallBack;
import com.example.pikamouse.learn_protocol.client.ClientRequestTask;
import com.example.pikamouse.learn_protocol.client.ConnectionClient;
import com.example.pikamouse.learn_protocol.protocol.BasicProtocol;
import com.example.pikamouse.learn_protocol.protocol.DataAckProtocol;
import com.example.pikamouse.learn_protocol.protocol.DataProtocol;

import java.net.ProtocolException;

public class MainActivity extends AppCompatActivity implements RequestCallBack {

    private ConnectionClient mClient;

    private Button mButton1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mClient = new ConnectionClient(this);

        mButton1 = findViewById(R.id.id_btn_1);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataProtocol dataProtocol = new DataProtocol();
                dataProtocol.setData("hello");
                mClient.addNewRequest(dataProtocol);
            }
        });

    }

    @Override
    public void onSuccess(BasicProtocol msg) {
        Log.i("liting",msg.toString());
        String text = "";
        if(msg.getProtocolType() == 1){
            DataAckProtocol dataAckProtocol = (DataAckProtocol) msg;
            text = dataAckProtocol.getUnused();
        }else if (msg.getProtocolType() == 3){
            text = msg.toString();
        }
        Toast.makeText(this,text ,Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onFailed(int errorCode, String msg) {
        Log.i("liting",errorCode + msg);
        Toast.makeText(this,errorCode + msg,Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
