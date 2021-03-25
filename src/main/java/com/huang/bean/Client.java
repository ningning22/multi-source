package com.huang.bean;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.huang.tree.Node;
import com.huang.util.enc.AESUtil;
import com.huang.util.enc.BytesUtil;
import com.huang.util.enc.HMacUtil;
import com.huang.util.tool.EncUtil;
import com.huang.util.tool.EncodingUtil;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private static final int encodingLength=Parameters.encodingLength;
    private static final List<String> prefix=Parameters.prefix;
    private static final byte[] r=Parameters.r;

    public List<JSONObject> getSearchToken(int begin, int end) {
        List<JSONObject> result=new ArrayList<>();
        Node root=Node.createTree(encodingLength,0);
        Node.flagTree(root,begin,end);
        List<String> searchPreList = Node.getFlagNode(root);
        for (String s : searchPreList) {
            int position=EncodingUtil.getPreIndex(prefix, s);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("position",position);
            byte[] ri= EncUtil.getRI(r,position);
            jsonObject.put("ri",ri);
            result.add(jsonObject);
        }
        return result;
    }

    public List<Integer> decSearchResult(List<JSONObject> searchResult,byte[] s){
        List<Integer> result=new ArrayList<>();
        for(JSONObject jsonObject:searchResult){
            String doId=jsonObject.getString("doId");
            byte[] encData=jsonObject.getBytes("encData");
            byte[] ssi= HMacUtil.encrypt(s,doId);
            byte[] decData= AESUtil.decrypt(ssi,encData,AESUtil.iv);
            String data= BytesUtil.bytesToString(decData);
            result.add(Integer.valueOf(data));
        }
        return result;
    }
}
