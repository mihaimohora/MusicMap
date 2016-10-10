package com.example.cristianbaita.MusicMap.pojo.interfaces;

public interface EasyObservable<T> {
	
	/**
	 * Adds the listener.
	 *
	 * @param listener the listener
	 */
	void addListener(OnChangeListener<T> listener);

	/**
	 * Removes the listener.
	 *
	 * @param listener the listener
	 */
	void removeListener(OnChangeListener<T> listener);
	
}
