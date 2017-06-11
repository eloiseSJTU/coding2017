package train;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import java.util.Arrays;

/**
 * Created by blind on 2017/4/14 0014.
 */
public class Main {


    /**
     * 线程同步
     */
    public static void threadSwithExample() {
        Flowable.range(1, 10)
                .subscribeOn(Schedulers.computation())
                .map(x -> {
                    System.out.println(
                            "上层" +
                                    Thread.currentThread().getId() + " "
                                    + Thread.currentThread().getName() + " "
                                    + x);
                    return x;
                })
                .observeOn(Schedulers.computation()) //这行注释掉就会退化成单线程
                .map(x -> {
                    System.out.println(
                            "下层" +
                                    Thread.currentThread().getId() + " "
                                    + Thread.currentThread().getName() + " "
                                    + x);
                    return x;
                })
                .subscribe((x) -> System.out.println(
                        Thread.currentThread().getId()
                                + " "
                                + Thread.currentThread().getName()
                                + " " + x + " main")
                        , Throwable::printStackTrace);
    }

    /**
     * 偶数
     */
    public static void filterExample() {
        Flowable.range(1, 10)
                .subscribeOn(Schedulers.newThread())
                .filter(x -> x % 2 == 0)
                .subscribe(System.out::println, Throwable::printStackTrace);

    }


    /**
     * 质数
     */
    public static void filterExample2() {
        Flowable.range(2, 20)
                .subscribeOn(Schedulers.newThread())
                .filter(x -> Flowable.range(2, x - 2)
                        .filter(y -> x % y == 0)
                        .isEmpty()
                        .blockingGet()
                )
                .subscribe(System.out::println, Throwable::printStackTrace);
    }


    /**
     * 并行计算平方
     */
    public static void parallelExample() {
        Flowable.range(1, 20)
                .parallel()
                .runOn(Schedulers.computation())
                .map(v ->{
                    Thread.sleep(300);
                    return v*v;
                })
                .sequential()
                .subscribe(System.out::println);
    }


    /**
     * 遍历一个整数数字字符串数组，把每个字符串按它的数值大小重复相应次数后，输出
     *
     * @throws InterruptedException
     */
    public static void flatMapExample() throws InterruptedException {
        String[] strs = {"1", "3", "2", "10"};
        Flowable.fromArray(strs)
                .subscribeOn(Schedulers.newThread())
                .flatMap(s -> Flowable.just(s)
                        .repeat(Integer.parseInt(s)))
                .subscribe(System.out::println, Throwable::printStackTrace);
    }

    /**
     * 把一个字符串数组中的每个字符串末尾加上句号
     *
     * @throws InterruptedException
     */
    public static void mapExample2() throws InterruptedException {
        String[] strs = {"1", "2", "3", "4"};
        Flowable.fromArray(strs)
                .subscribeOn(Schedulers.newThread())
                .map(s -> s + ".")
                .subscribe(System.out::println, Throwable::printStackTrace);
    }

    /**
     * 模拟耗时任务
     */
    public static void longTimeMissionExample() {
        Flowable.just("1", "2", "3")
                .subscribeOn(Schedulers.newThread())
                .map(x -> {
                    System.out.println("耗时任务开始……");
                    Thread.sleep(300);
                    return x;
                })
                .subscribe(x -> System.out.println("耗时任务结束……" + x));
    }

    public static void main(String[] args) throws InterruptedException {

        //longTimeMissionExample();

        //parallelExample();
        //threadSwithExample();

        //filterExample();
        filterExample2();

        //mapExample();
        //flatMapExample();

        // 模拟主线程的工作
        System.out.println("主线程去做其他事情");
        Thread.sleep(3000); // <--- wait for the flow to finish
        System.out.println("主线程结束");
    }
}
