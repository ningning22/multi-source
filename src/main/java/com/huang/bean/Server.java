package com.huang.bean;

import com.alibaba.fastjson.JSONObject;
import com.huang.util.tool.EncUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Server {
    //doId->(M,encData)
    Map<String,JSONObject> as=new HashMap<>();

    public void addInServer(String doId,JSONObject value){
        as.put(doId,value);
    }

    public List<JSONObject> search(List<JSONObject> searchToken){
        List<JSONObject> result=new ArrayList<>();
        for(String doId:as.keySet()){
            JSONObject value=as.get(doId);
            for(JSONObject jsonObject:searchToken){
                int position=jsonObject.getInteger("position");
                byte[] ri=jsonObject.getBytes("ri");
                char ch=EncUtil.getGRI(ri,doId);
                String M=value.getString("M");
                byte[] encData=value.getBytes("encData");
//                System.out.println("doId:"+doId);
//                System.out.println("position:"+position);
//                System.out.println("M:"+M);
//                System.out.println("ch:"+ch);
                if(M.charAt(position)!=ch){
                    JSONObject res=new JSONObject();
                    res.put("doId",doId);
                    //res.put("M",M);
                    res.put("encData",encData);
                    result.add(res);
                }
            }
        }
        return result;
    }
}
