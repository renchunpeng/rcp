//: HorrorShow.java
// Extending an interface with inheritance
package com.inms.action.thinking_in_java;

interface Monster {
	void menace();
}

interface DangerousMonster extends Monster {
	void destroy();
}

interface Lethal {
	void kill();
}

class DragonZilla implements DangerousMonster {
	public void menace() {
		System.out.println("mance");
	}

	public void destroy() {
		System.out.println("destory");
	}
}

interface Vampire extends DangerousMonster, Lethal {
	void drinkBlood();
}

class HorrorShow {
	static void u(Monster b) {
		b.menace();
	}

	static void v(DangerousMonster d) {
		d.menace();
		d.destroy();
	
	}

	public static void main(String[] args) {
		DragonZilla if2 = new DragonZilla();
		u(if2);
		v(if2);
	}
} // /:~