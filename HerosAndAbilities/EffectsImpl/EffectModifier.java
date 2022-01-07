package battlesystem;

public interface EffectModifier<T>
{
    public T applyEffect(Entity creator);
    public Effects.Modifier getName();
}