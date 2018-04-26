package ecust.dffuture.dfmapper.rule;

import java.util.Queue;
import java.util.Stack;

/**
 * 重写列表
 * 局部的自上而下重写，全局的自下而上重写
 */
public class RewriteList {

    Queue<RewriteItem> part;

    Stack<RewriteItem> overall;
}
