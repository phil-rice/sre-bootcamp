package dev.srebootcamp.entity.entity;


/**
 * This is a typeclass / flyweight interface that defines the operations that can be performed on an entity.
 */
public interface IEntity<T> {
    T applyLens(LensDefn lens, T value);

}
