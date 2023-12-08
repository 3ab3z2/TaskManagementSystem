public interface LoadSave {
    public String toString();
    public LoadSave fromString(String s) throws IllegalArgumentException;
}