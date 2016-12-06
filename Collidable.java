/**
 * Created by coxjc on 12/6/16.
 * <p>
 * A "Collidable" object is one that implements the "collide(Collidable o)"
 * method.
 */
public interface Collidable {

    /**
     * This is the only method in the interface.
     * It is used to define the functionality a Collidable object has when it
     * collides w/ another Collidable object, @Param otherObj.
     **/
    void collide(Collidable otherObj);

}
