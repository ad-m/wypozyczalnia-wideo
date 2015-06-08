package core;

public interface WindowObject<T> {
	public T getObject();

	public boolean getStatus();

	public void updateObject();

	public void setStatus(boolean s);

	public void dispose();
}
