package net.ludocrypt.backrooms.util;

//Stolen from minecraft code
public class Pair<A, B> {
	private A first;
	private B second;

	public Pair(A a, B b) {
		this.first = a;
		this.second = b;
	}

	public A getFirst() {
		return this.first;
	}

	public B getSecond() {
		return this.second;
	}

	public static <A, B> Pair<A, B> of(A a, B b) {
		return new Pair<A, B>(a, b);
	}
}
