package cn.barrywangmeng.concurrent;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Description:
 * @Author: wangmeng
 * @Date: 2018/12/16-10:54
 */
public class ThreadLocalTest {

    private List<String> messages = Lists.newArrayList();

    public static final ThreadLocal<ThreadLocalTest> holder = new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return new ThreadLocalTest();
        }
    };

    public static void add(String message) {
        holder.get().messages.add(message);
    }

    public static List<String> clear() {
        List<String> messages = holder.get().messages;
        holder.remove();

        System.out.println("size: " + holder.get().messages.size());
        return messages;
    }

    public static void main(String[] args) {
        ThreadLocalTest.add("1111");
        ThreadLocalTest.add("2222");

        List<String> clear = ThreadLocalTest.clear();
        System.out.println(clear.toString());
    }
}