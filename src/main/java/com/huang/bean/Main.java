package com.huang.bean;

import com.alibaba.fastjson.JSONObject;
import com.huang.util.enc.HMacUtil;
import com.huang.util.enc.RandomUtil;
import com.huang.util.tool.PrintUtil;
import com.huang.util.tool.TupleUtil;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Client client=new Client();
        Server server=new Server();

        //生成do，将数据上传到服务器
        byte[] s= RandomUtil.getRandomKey(32);
        for(int i=0;i<(int)(Math.pow(2,Parameters.encodingLength));i++){
            String doId=i+"";
            byte[] ssi= HMacUtil.encrypt(s,doId);
            Do d=new Do(doId,i,ssi);
            d.getEncData();
            d.getI();
            d.getM();
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("M",d.getM());
            jsonObject.put("encData",d.getEncData());
            server.addInServer(doId,jsonObject);
        }

        System.out.println("encodingLength:"+Parameters.encodingLength);

        System.out.println("\nprefixList:");
        PrintUtil.printList(Parameters.prefix,new TupleUtil());

        System.out.println("\nas:");
        PrintUtil.printMap(server.as,new TupleUtil());

        int begin=2;
        int end=4;
        System.out.println("\nsearchRange:"+"["+begin+","+end+"]");
        List<JSONObject> searchToken = client.getSearchToken(begin,end);
        System.out.println("\nsearchToken:");
        PrintUtil.printList(searchToken,new TupleUtil());

        List<JSONObject> result=server.search(searchToken);
        System.out.println("\nresult:");
        PrintUtil.printList(result,new TupleUtil());

        List<Integer> decResult=client.decSearchResult(result,s);
        System.out.println("\ndecResult:");
        PrintUtil.printList(decResult,new TupleUtil());
    }
}
