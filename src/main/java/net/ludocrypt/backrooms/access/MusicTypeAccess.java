package net.ludocrypt.backrooms.access;

public interface MusicTypeAccess {

	public boolean shouldReplace();

	public void makeReplace(boolean replace);

	public static MusicTypeAccess get(Object in) {
		if (!(in instanceof MusicTypeAccess)) {
			throw new UnsupportedOperationException("Object " + in.toString() + " does not implement MusicTypeAccess");
		}
		return ((MusicTypeAccess) in);
	}

}
