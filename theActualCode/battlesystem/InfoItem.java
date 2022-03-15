package game.battlesystem;

public abstract interface InfoItem<T>
{
    public abstract T create(Entity creator);
}