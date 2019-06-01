package com.change.hippo.guava;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;

/**
 * 断言测试
 */
public class PreconditionsDemo {

    public static void main(String[] args) {
        String name = "";
        Preconditions.checkNotNull(name, "name may not be null");
        Preconditions.checkArgument(true, "name may not be null");

        Optional<Student> possibleNull = Optional.absent();
        try {
            Student jim = possibleNull.get();
        } catch (Exception e) {
            System.out.println(Throwables.getRootCause(e));
            System.out.println(Throwables.getCausalChain(e));
            System.out.println(Throwables.getStackTraceAsString(e));
            Throwables.propagateIfInstanceOf(e, NullPointerException.class);
            Throwables.propagate(e);
        }

    }
}
