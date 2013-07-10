package com.google.gwt.sample.healthyeatingapp.client;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.sample.healthyeatingapp.client.AuthenticationHandler;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HealthyEatingApp implements EntryPoint 
{
	//DB Connection tester code  *************************
	private DBConnectionAsync rpc;
	private Button dbConnection;
	private HorizontalPanel addPanel;
	//****************************************************
	public HealthyEatingApp()
	{
		//DB Connection tester code  *************************
		addPanel = new HorizontalPanel();
		dbConnection = new Button("Test Connection");
	    rpc = (DBConnectionAsync) GWT.create(DBConnection.class);
	 	ServiceDefTarget target = (ServiceDefTarget) rpc;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "MySQLConnection";
		target.setServiceEntryPoint(moduleRelativeURL); 
		//****************************************************
	}
	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() 
	{
	    
		Homepage homeContainer = new Homepage();
		RootLayoutPanel.get().add(homeContainer);
		//DB Connection tester code  *************************
	    addPanel.add(dbConnection);

		RootPanel.get("dbConnection").add(addPanel);
		 
		// Listen for mouse events on the button.
		dbConnection.addClickHandler(new ClickHandler() {
	      @Override
		public void onClick(ClickEvent event) {
	    	  test();
	      }
	    });
		

		//****************************************************
		 
	}
	
 
	public void test(){
		Window.alert("clicked");
		System.out.println("TRIAL");
	}
	
	//button ClickListener
	public void onClick(Widget sender) 
	{
		 if (sender.equals(dbConnection)) 
		 {
			System.out.print("Hi, reached button");
			addPanel.add(dbConnection);
			AsyncCallback<User> callback = new AuthenticationHandler<User>();
			rpc.authenticateUser("rrazdan", callback);
		 }
	}

}


 