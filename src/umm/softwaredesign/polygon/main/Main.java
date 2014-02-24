package umm.softwaredesign.polygon.main;


import umm.softwaredesign.polygon.view.PolygonFrame;

public final class Main {
	
	private Main(){
		//Main should never be constructed
	}

    /**
     * @param args
     * @throws UnsupportedLookAndFeelException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws ClassNotFoundException 
     */
    public static void main(final String[] args) {
        PolygonFrame pFrame = new PolygonFrame();
        pFrame.setVisible(true);
    }

}
