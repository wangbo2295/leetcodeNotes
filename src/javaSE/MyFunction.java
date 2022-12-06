package javaSE;

import java.util.function.Function;

/**
 * 思考：函数接口在开发中有什么作用？
 * @param <T>
 */
@FunctionalInterface
public interface MyFunction <T>{

    boolean isEqual(T o1,T o2);

    /**
     * 如果函数接口只能有一个抽象方法的话
     * 这是否说明接口也是Object子类？
     * 并且ide也提示该方法是重写了Object的equals
     * 那这又有什么意义呢
     * 接口不能实例化，就算继承了Object的方法，当其被实现的时候，实现类也是Object子类，有什么意义？
     * 猜想：接口、抽象类也是Object子类，当一个类实现一个接口时，其实就是继承了这个接口
     * 而多继承的弊端在于，如果子类继承了两个父类，且两个父类有一个同名的方法但子类没有重写
     * 当子类调用父类这个方法时，就不知道调哪个方法了。
     * 接口能够多继承的原因在于：接口里面都是抽象方法，实现类必须实现，所以避免了多继承的弊端。
     * 如果实现的两个接口有同名方法，则只能实现一次，要达成不同的实现，可以使用lambda表达式
     */
    boolean equals(Object obj);

    static <T, U> MyFunction<T> equaling(
            Function<? super T, ? extends U> keyExtractor){
        return (o1,o2)->keyExtractor.apply(o1)==keyExtractor.apply(o2);
    }




}
