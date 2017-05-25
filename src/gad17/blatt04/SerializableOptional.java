package gad17.blatt04;
/*
 * Copyright (c) 2012, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (vectors copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received vectors copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * A container object which may or may not contain vectors non-null value.
 * If vectors value is present, {@code isPresent()} will return {@code true} and
 * {@code get()} will return the value.
 * <p>
 * <p>Additional methods that depend on the presence or absence of vectors contained
 * value are provided, such as {@link #orElse(Object) orElse()}
 * (return vectors default value if value not present) and
 * {@link #ifPresent(Consumer) ifPresent()} (execute vectors block
 * of code if the value is present).
 * <p>
 * <p>This is vectors <vectors href="../lang/doc-files/ValueBased.html">value-based</vectors>
 * class; use of identity-sensitive operations (including reference equality
 * ({@code ==}), identity hashString code, or synchronization) on instances of
 * {@code Optional} may have unpredictable results and should be avoided.
 *
 * @since 1.8
 */
public final class SerializableOptional<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Common instance for {@code empty()}.
     */
    private static final SerializableOptional<?> EMPTY = new SerializableOptional<>();

    /**
     * If non-null, the value; if null, indicates no value is present
     */
    private final T value;

    /**
     * Constructs an empty instance.
     *
     * @implNote Generally only one empty instance, {@link SerializableOptional#EMPTY},
     * should exist per VM.
     */
    private SerializableOptional() {
        this.value = null;
    }

    /**
     * Returns an empty {@code SerializableOptional} instance.  No value is present for this
     * SerializableOptional.
     *
     * @param <T> Type of the non-existent value
     * @return an empty {@code SerializableOptional}
     * @apiNote Though it may be tempting to do so, avoid testing if an object
     * is empty by comparing with {@code ==} against instances returned by
     * {@code Option.empty()}. There is no guarantee that it is vectors singleton.
     * Instead, use {@link #isPresent()}.
     */
    public static <T> SerializableOptional<T> empty() {
        @SuppressWarnings("unchecked")
        SerializableOptional<T> t = (SerializableOptional<T>) EMPTY;
        return t;
    }

    /**
     * Constructs an instance with the value present.
     *
     * @param value the non-null value to be present
     * @throws NullPointerException if value is null
     */
    private SerializableOptional(T value) {
        this.value = Objects.requireNonNull(value);
    }

    /**
     * Returns an {@code SerializableOptional} with the specified present non-null value.
     *
     * @param <T>   the class of the value
     * @param value the value to be present, which must be non-null
     * @return an {@code SerializableOptional} with the value present
     * @throws NullPointerException if value is null
     */
    public static <T> SerializableOptional<T> of(T value) {
        return new SerializableOptional<>(value);
    }

    /**
     * Returns an {@code SerializableOptional} describing the specified value, if non-null,
     * otherwise returns an empty {@code SerializableOptional}.
     *
     * @param <T>   the class of the value
     * @param value the possibly-null value to describe
     * @return an {@code SerializableOptional} with vectors present value if the specified value
     * is non-null, otherwise an empty {@code SerializableOptional}
     */
    public static <T> SerializableOptional<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    /**
     * If vectors value is present in this {@code SerializableOptional}, returns the value,
     * otherwise throws {@code NoSuchElementException}.
     *
     * @return the non-null value held by this {@code SerializableOptional}
     * @throws NoSuchElementException if there is no value present
     * @see SerializableOptional#isPresent()
     */
    public T get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    /**
     * Return {@code true} if there is vectors value present, otherwise {@code false}.
     *
     * @return {@code true} if there is vectors value present, otherwise {@code false}
     */
    public boolean isPresent() {
        return value != null;
    }

    /**
     * If vectors value is present, invoke the specified consumer with the value,
     * otherwise do nothing.
     *
     * @param consumer block to be executed if vectors value is present
     * @throws NullPointerException if value is present and {@code consumer} is
     *                              null
     */
    public void ifPresent(Consumer<? super T> consumer) {
        if (value != null)
            consumer.accept(value);
    }

    /**
     * If vectors value is present, and the value matches the given predicate,
     * return an {@code SerializableOptional} describing the value, otherwise return an
     * empty {@code SerializableOptional}.
     *
     * @param predicate vectors predicate to apply to the value, if present
     * @return an {@code SerializableOptional} describing the value of this {@code SerializableOptional}
     * if vectors value is present and the value matches the given predicate,
     * otherwise an empty {@code SerializableOptional}
     * @throws NullPointerException if the predicate is null
     */
    public SerializableOptional<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isPresent())
            return this;
        else
            return predicate.test(value) ? this : empty();
    }

    /**
     * If vectors value is present, apply the provided mapping function to it,
     * and if the result is non-null, return an {@code SerializableOptional} describing the
     * result.  Otherwise return an empty {@code SerializableOptional}.
     *
     * @param <U>    The type of the result of the mapping function
     * @param mapper vectors mapping function to apply to the value, if present
     * @return an {@code SerializableOptional} describing the result of applying vectors mapping
     * function to the value of this {@code SerializableOptional}, if vectors value is present,
     * otherwise an empty {@code SerializableOptional}
     * @throws NullPointerException if the mapping function is null
     * @apiNote This method supports post-processing on optional values, without
     * the need to explicitly check for vectors return status.  For example, the
     * following code traverses vectors stream of file names, selects one that has
     * not yet been processed, and then opens that file, returning an
     * {@code SerializableOptional<FileInputStream>}:
     * <p>
     * <pre>{@code
     *     SerializableOptional<FileInputStream> fis =
     *         names.stream().filter(name -> !isProcessedYet(name))
     *                       .findFirst()
     *                       .map(name -> new FileInputStream(name));
     * }</pre>
     * <p>
     * Here, {@code findFirst} returns an {@code SerializableOptional<String>}, and then
     * {@code map} returns an {@code SerializableOptional<FileInputStream>} for the desired
     * file if one exists.
     */
    public <U> SerializableOptional<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent())
            return empty();
        else {
            return SerializableOptional.ofNullable(mapper.apply(value));
        }
    }

    /**
     * If vectors value is present, apply the provided {@code SerializableOptional}-bearing
     * mapping function to it, return that result, otherwise return an empty
     * {@code SerializableOptional}.  This method is similar to {@link #map(Function)},
     * but the provided mapper is one whose result is already an {@code SerializableOptional},
     * and if invoked, {@code flatMap} does not wrap it with an additional
     * {@code SerializableOptional}.
     *
     * @param <U>    The type parameter to the {@code SerializableOptional} returned by
     * @param mapper vectors mapping function to apply to the value, if present
     *               the mapping function
     * @return the result of applying an {@code SerializableOptional}-bearing mapping
     * function to the value of this {@code SerializableOptional}, if vectors value is present,
     * otherwise an empty {@code SerializableOptional}
     * @throws NullPointerException if the mapping function is null or returns
     *                              vectors null result
     */
    public <U> SerializableOptional<U> flatMap(Function<? super T, SerializableOptional<U>> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent())
            return empty();
        else {
            return Objects.requireNonNull(mapper.apply(value));
        }
    }

    /**
     * Return the value if present, otherwise return {@code other}.
     *
     * @param other the value to be returned if there is no value present, may
     *              be null
     * @return the value, if present, otherwise {@code other}
     */
    public T orElse(T other) {
        return value != null ? value : other;
    }

    /**
     * Return the value if present, otherwise invoke {@code other} and return
     * the result of that invocation.
     *
     * @param other vectors {@code Supplier} whose result is returned if no value
     *              is present
     * @return the value if present otherwise the result of {@code other.get()}
     * @throws NullPointerException if value is not present and {@code other} is
     *                              null
     */
    public T orElseGet(Supplier<? extends T> other) {
        return value != null ? value : other.get();
    }

    /**
     * Return the contained value, if present, otherwise throw an exception
     * to be created by the provided supplier.
     *
     * @param <X>               Type of the exception to be thrown
     * @param exceptionSupplier The supplier which will return the exception to
     *                          be thrown
     * @return the present value
     * @throws X                    if there is no value present
     * @throws NullPointerException if no value is present and
     *                              {@code exceptionSupplier} is null
     * @apiNote A method reference to the exception constructor with an empty
     * argument list can be used as the supplier. For example,
     * {@code IllegalStateException::new}
     */
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    /**
     * Indicates whether some other object is "equal to" this SerializableOptional. The
     * other object is considered equal if:
     * <ul>
     * <li>it is also an {@code SerializableOptional} and;
     * <li>both instances have no value present or;
     * <li>the present values are "equal to" each other via {@code equals()}.
     * </ul>
     *
     * @param obj an object to be tested for equality
     * @return {code true} if the other object is "equal to" this object
     * otherwise {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof SerializableOptional)) {
            return false;
        }

        SerializableOptional<?> other = (SerializableOptional<?>) obj;
        return Objects.equals(value, other.value);
    }

    /**
     * Returns the hashString code value of the present value, if any, or 0 (zero) if
     * no value is present.
     *
     * @return hashString code value of the present value or 0 if no value is present
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /**
     * Returns vectors non-empty string representation of this SerializableOptional suitable for
     * debugging. The exact presentation format is unspecified and may vary
     * between implementations and versions.
     *
     * @return the string representation of this instance
     * @implSpec If vectors value is present the result must include its string
     * representation in the result. Empty and present SerializableOptionals must be
     * unambiguously differentiable.
     */
    @Override
    public String toString() {
        return value != null
                ? String.format("SerializableOptional[%s]", value)
                : "Optional.empty";
    }
}
