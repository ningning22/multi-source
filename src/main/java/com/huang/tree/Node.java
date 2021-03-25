package com.huang.tree;

import com.huang.bean.Parameters;
import com.huang.util.tool.EncodingUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node {
    public static int length= Parameters.encodingLength;
    private Node leftNode;
    private Node rightNode;
    private int num;
    private int deep;
    private boolean flag;

    public static Node createTree(int deep,int num){
        Node root=new Node();
        root.setNum(num);
        root.setDeep(deep);
        if(deep==0)return root;
        root.setLeftNode(createTree(deep-1, num*2));
        root.setRightNode(createTree(deep-1,num*2+1));
        return root;
    }

    public static void preOrder(Node node){
        System.out.println(node.num);
        if(node.leftNode!=null)preOrder(node.getLeftNode());
        if(node.rightNode!=null)preOrder(node.getRightNode());
    }

    public static void levelOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if(root == null) return;
        queue.add(root);
        while(!queue.isEmpty()){
            int cur = queue.size();
            for(int i=0;i<cur;i++){
                Node t= queue.poll();
                if(t==null)continue;
                System.out.print(t.toString() +"\t");
                if(t.getLeftNode() != null)
                    queue.add(t.getLeftNode());
                if(t.getRightNode() != null)
                    queue.add(t.getRightNode());
            }
            System.out.println();
        }
    }

    public static void flagTree(Node root,int searchBegin,int searchEnd){
        int deep=root.getDeep();
        int num=root.getNum();
        int begin= (int) (num*Math.pow(2,deep));
        int end=(int) (num*Math.pow(2,deep)+Math.pow(2,deep)-1);
        if(searchBegin<=begin && end<=searchEnd){
            root.setFlag(true);
        }else {
            if(root.getLeftNode()!=null)flagTree(root.getLeftNode(),searchBegin,searchEnd);
            if(root.getRightNode()!=null)flagTree(root.getRightNode(),searchBegin,searchEnd);
        }
    }

    public static List<String> getFlagNode(Node node){
        List<String> result=new ArrayList<>();
        int num= node.getNum();
        int deep=node.getDeep();
        String pre=EncodingUtil.getNumberEncoding(num,length-deep)+"*".repeat(deep);
        if(deep==length)pre="*".repeat(deep);
        if(node.isFlag())result.add(pre);
        if(node.leftNode!=null){
            result.addAll(getFlagNode(node.getLeftNode()));
        }
        if(node.rightNode!=null){
            result.addAll(getFlagNode(node.getRightNode()));
        }
        return result;
    }

    @Override
    public String toString() {
        String pre=EncodingUtil.getNumberEncoding(num,length-deep)+"*".repeat(deep);
        if(deep==length)pre="*".repeat(deep);
        return "["+num+","+flag+","+pre+"]";
    }
}
