package train;

/**
 * Created by thomas_young on 10/6/2017.
 */
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Queue;

public class CollectionAsLifoQueue {

    public static void main(String args[]) {

        Deque<String> dq = new ArrayDeque<String>(5);
        dq.add("java");
        dq.add("c");
        dq.add("c++");
        dq.add("unix");
        dq.push("perl");
        Queue<String> q = Collections.asLifoQueue(dq);
        System.out.println("returned queue is: "+q);
        q.add("last1");
        q.add("last2");
        System.out.println(q);
    }
}
