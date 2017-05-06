package gameGUI;

public class Figure {
	private int type;
	private String iconPath;

	public void setType(int type) {
		this.type = type;
	}

	public void setIconPath(String string) {
		this.iconPath = string;
	}

	public Figure() {
		super();
	}

	public int getType() {
		return type;
	}

	public String getIconPath() {
		return iconPath;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Figure) {
			Figure figure = (Figure) obj;
			return figure.getIconPath().equals(this.getIconPath());
		}
		return false;
	}
}
