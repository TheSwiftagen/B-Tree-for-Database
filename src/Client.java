import yash.App;
import yash.DataManager;

public class Client {
	public static void main(String[] args) throws Exception {
		DataManager.basedir = args[0];
		App app = new App();
		System.out.println("Application executed with following properties as default : " + app.toString());
	}
}
