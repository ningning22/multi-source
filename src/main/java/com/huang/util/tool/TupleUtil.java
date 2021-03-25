package com.huang.util.tool;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Quintet;
import org.javatuples.Triplet;

public class TupleUtil implements ObjectUtil {

    @Override
    public String obj2String(Object obj) {
        StringBuilder sb = new StringBuilder();
        if (obj == null) {
            return "";
        } else if (obj instanceof Pair<?, ?>) {
            Pair<?, ?> node = (Pair<?, ?>) obj;
            for (int i = 0; i < node.getSize(); i++) {
                sb.append(node.getValue(i)).append(" ");
            }
        } else if (obj instanceof Triplet<?, ?, ?>) {
            Triplet<?, ?, ?> node = (Triplet<?, ?, ?>) obj;
            for (int i = 0; i < node.getSize(); i++) {
                sb.append(node.getValue(i)).append(" ");
            }
        } else if (obj instanceof Quartet<?, ?, ?, ?>) {
            Quartet<?, ?, ?, ?> node = (Quartet<?, ?, ?, ?>) obj;
            for (int i = 0; i < node.getSize(); i++) {
                sb.append(node.getValue(i)).append(" ");
            }
        } else if (obj instanceof Quintet<?, ?, ?, ?, ?>) {
            Quintet<?, ?, ?, ?, ?> node = (Quintet<?, ?, ?, ?, ?>) obj;
            for (int i = 0; i < node.getSize(); i++) {
                sb.append(node.getValue(i)).append(" ");
            }
        } else {
            sb.append(obj.toString());
        }
        return sb.toString();
    }
}
