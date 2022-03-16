package battlesystem;

/**
 * 
 *
 * @param <T> The type of thing the info item will create.
 */
public abstract interface InfoItem<T>
{
    public abstract T create(Entity creator);
}