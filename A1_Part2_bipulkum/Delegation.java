// ASSIGNMENT 1 - PART 2


 class Delegation {
	
	public static void main(String args[]) {
		B b = new B();
		System.out.println(b.f()+b.g()-b.p(1)+b.q(2));
		
		B2 b2 = new B2();
		System.out.println(b2.f()+b2.g()-b2.p(1)+b2.q(2));
		
		D d = new D();
		System.out.println(d.f()+d.g()-d.h()+d.p(1)-d.q(2)+d.r());
		
		D2 d2 = new D2();
		System.out.println(d2.f()+d2.g()-d2.h()+d2.p(1)-d2.q(2)+d2.r());	
		
	}
}
 
 class Delegation2 {
		
	public static void main(String args[]) {
		
		E e = new E();
		System.out.println(e.f()-e.g()+e.h()-e.p(1)+e.q(2)-e.r()+e.k(100));
		
		E2 e2 = new E2();
		System.out.println(e2.f()-e2.g()+e2.h()-e2.p(1)+e2.q(2)-e2.r()+e2.k(100));

		F f = new F();
		System.out.println(f.f()+f.g()+f.h()+f.p(1)-f.q(2)-f.r()-f.j(10)+f.l(100));
		
		F2 f2 = new F2();
		System.out.println(f2.f()+f2.g()+f2.h()+f2.p(1)-f2.q(2)-f2.r()-f2.j(10)+f2.l(100));		
	}
}
 
 
abstract class A {
	int a1 = 100;
	int a2 = 200;

	public int f() {
		return a1 + p(100) + q(100);
	}

	protected abstract int p(int m);

	protected abstract int q(int m);
}

class B extends A {
	int b1 = 1000;
	int b2 = 2000;

	public int g() {
		return this.p(100) + this.q(200); 
	}

	public int p(int m) {
		return m + a1+b1;
	}

	public int q(int m) {
		return m + a2+b2;
	}
}

abstract class C extends B {
	int c1 = 10000;
	int c2 = 20000;

	public int r() {
		return f() + g() + h();
	}

	public int q(int m) {
		return m + a2 + b2 + c2;
	}

	protected abstract int h();
}

class D extends C {
	int d1 = 1000000;
	int d2 = 2000000;

	public int r() {
		return f() + g() + h();
	}

	public int p(int m) {
		return super.p(m) + d2;
	}

	public int h() {
		return a1 + b1 + c1;
	}
	
	public int j(int n) {
		return r() + super.r();
	}

}

class E extends C {
	int e1 = 1;
	int e2 = 2;

	public int q(int m) {
		return p(m) + c2;
	}

	public int h() {
		return a1 + b1 + e1;
	}
	
	public int k(int n) {
		return q(n) + super.q(n);
	}

}

class F extends D {
	int f1 = 10;
	int f2 = 20;

	public int q(int m) {
		return p(m) + c1 + d1;
	}

	public int h() {
		return c2 + f2;
	}
	
	public int l(int n) {
		return q(n) + super.q(n);
	}
}



// ===== TRANSFORMATION IN TERMS OF DELEGATION ======



// INTERFACES

interface IA {
	int a1 = 100;
	int a2 = 200;
	
	int f();
	int p(int m);
	int q(int m);
}

interface IB extends IA {
	int b1 = 1000;
	int b2 = 2000;
	int g();
}

interface IC extends IB {
	int c1 = 10000;
	int c2 = 20000;
	
	int r();
	int h();
}

interface ID extends IC {
	int d1 = 1000000;
	int d2 = 2000000;
	
	int j(int n);
	
}

interface IE extends IC {
	int e1 = 1;
	int e2 = 2;
	
	int q(int m);
	int h();
	int k(int n);

}

interface IF extends ID {
	int f1 = 10;
	int f2 = 20;
	
	int q(int m);
	int h();
	int l(int n);

}

// CLASSES 

class A2 implements IA {
	IA sub;
	public A2(IA a) {
		sub = a;
	}
	public int f() {
		return a1 + sub.p(100) + sub.q(100);
	}
	public int p(int m) {
		return sub.p(m); //m + a1+b1;
	}

	public int q(int m) {
		return sub.q(m); //m + a2+b2;
	}
}

class B2 implements IB {
	A2 a2obj;
	IB sub;
	public B2() {
		a2obj = new A2(this);
	}
	
	public B2(IB a) {
		a2obj = new A2(a);
		sub = a;
	}

	public int g() {
		return a2obj.p(100) + a2obj.q(200); 
	}
	

	public int f() {
		return a2obj.f();
	}
	
	public int p(int m) {
		return m + a1+b1; //a2obj.p(m);
	}

	public int q(int m) {
		return m + a2+b2; //a2obj.q(m);
	}


}

class C2 implements IC {
	B2 b2obj;
	IC sub;
	public C2() {
		b2obj = new B2(this);
		sub = this;
	}
	
	public C2(IC a) {
		//this.b2obj = a;
		b2obj = new B2(a);
		sub = a;
	}
	
	public int r() {
		return f() + g() + h();
	}

	public int q(int m) {
		return m + a2 + b2 + c2;
	}

	//Do we need to redefine f()
	public int f() {
		//return a1 + sub.p(100) + sub.q(100);
		return b2obj.f();
	}
	public int p(int m) {
		return b2obj.p(m); //m + a1+b1;
	}

	public int g() {
		return b2obj.g();
	}
	public int h() {
		return sub.h();
	}

}

class D2 implements ID {
	
	C2 c2obj;
	ID sub;
	public D2() {
		c2obj = new C2(this);
	}
	public D2(ID a) {
		c2obj = new C2(a);
		sub = a;
	}

	public int r() {
		return c2obj.f() + c2obj.g() + c2obj.h();
	}
	
	public int h() {
		return a1 + b1 + c1;
	}

	public int p(int m) {
		return c2obj.p(m) + d2;
	}

	
	public int j(int n) {
		return r() + c2obj.r();
	}
	
	//Should it be A2 or C2
	public int q(int m) {
		return c2obj.q(m);
	}
	
	public int f() {
		return c2obj.f();
	}

	public int g() {
		return c2obj.g();
	}

}

class E2 implements IE {
	
	C2 c2obj;
	public E2() {
		c2obj = new C2(this);
	}
	public E2(IE a) {
		c2obj = new C2(a);
	}
	public int q(int m) {
		return p(m) + c2;
	}

	public int h() {
		return a1 + b1 + e1;
	}
	
	public int k(int n) {
		return q(n) +  c2obj.q(n);
	}

	public int r() {
		return c2obj.r();
	}

	public int g() {
		return c2obj.g();
	}

	public int f() {
		return c2obj.f();
	}

	public int p(int m) {
		return c2obj.p(m);
	}
}

class F2 implements IF {
	
	D2 d2obj;
	public F2() {
		d2obj = new D2(this);
	}
	
	public int q(int m) {
		return p(m) + c1 + d1;
	}

	public int h() {
		return c2 + f2;
	}
	
	public int l(int n) {
		return q(n) + d2obj.q(n);
	}

	public int j(int n) {
		return d2obj.j(n);
	}

	public int r() {
		return d2obj.r();
	}

	public int g() {
		return d2obj.g();
	}

	public int f() {
		return d2obj.f();
	}

	public int p(int m) {
		return d2obj.p(m);
	}
}
 