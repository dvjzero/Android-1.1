package com.example.clienteregistrocliente;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	
		private static final String METHOD_NAME = "agregarUsuarioAndroid"; 
	     private static final String NAMESPACE = "http://webService"; 
	     private static final String SOAP_ACTION = "http://webService/agregarUsuarioAndroid"; 
	     private static final String URL = "http://192.168.0.100:8080/ChileDenuncia/services/ServicioUsuario?wsdl"; 
	      
	     TextView nombre;
	 	TextView clave;
	 	TextView ciudad;
	 	TextView sector;
	 	TextView mail;
	 	TextView uno;

	 	EditText etNombre;
	 	EditText etclave;
	 	EditText etciudad;
	 	EditText etsector;
	 	EditText etmail;


	 	@Override
	 	protected void onCreate(Bundle savedInstanceState) {
	 		super.onCreate(savedInstanceState);
	 		setContentView(R.layout.activity_main);

	 		etNombre = (EditText)findViewById(R.id.editText1);
	 		etclave = (EditText)findViewById(R.id.editText2);
	 		etciudad = (EditText)findViewById(R.id.editText3);
	 		etsector = (EditText)findViewById(R.id.editText4);
	 		etmail = (EditText)findViewById(R.id.editText5);
	 		

	 		nombre = (TextView)findViewById(R.id.Nombre);
	 		clave = (TextView)findViewById(R.id.ApellidoM);
	 		ciudad = (TextView)findViewById(R.id.ApellidoP);
	 		sector = (TextView)findViewById(R.id.Celular);
	 		mail = (TextView)findViewById(R.id.Correo);
	 		uno = (TextView) findViewById(R.id.textView1);
	 		Button btn=(Button)findViewById(R.id.button1);
	 		btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					String mensaje =registroAdmin(nombre.getText().toString(), clave.getText().toString(), ciudad.getText().toString(), sector.getText().toString(), mail.getText().toString());
					    uno.setText(mensaje); 
				}});
		}


	  
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) { 
	        // Inflate the menu; this adds items to the action bar if it is present. 
	        getMenuInflater().inflate(R.menu.main, menu); 
	        return true; 
	    } 
	      
	     public String registroAdmin(String usuario, String clave,String ciudad ,String sector,String mail){ 
	           
	         SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME); 
	         //GSON 
	         UsuarioVO admin=new UsuarioVO(); 
	         admin.setNombre(usuario); 
	         admin.setClave(clave); 
	         admin.setCiudad(ciudad);
	         admin.setSector(sector);
	         admin.setMail(mail);
	         final Gson gson=new Gson(); 
	         String send=gson.toJson(admin); 
	          
	         //parametros 
	         PropertyInfo jSonPI=new PropertyInfo(); 
	         jSonPI.setName("json"); 
	         jSonPI.setValue(send); 
	         jSonPI.setType(String.class); 
	         request.addProperty(jSonPI); 
	          
	         //Envio 
	          
	          SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
	  envelope.dotNet = true; 
	  envelope.setOutputSoapObject(request); 
	  HttpTransportSE androidHttpTransport = new HttpTransportSE(URL); 
	    
	  try { 
	      androidHttpTransport.call(SOAP_ACTION, envelope); 
	      SoapPrimitive response = (SoapPrimitive) envelope.getResponse(); 
	      Log.i("Webservice Output", response.toString()); 
	  
	      return response.toString(); 
	  
	} catch (Exception e) { 
	      e.printStackTrace(); 
	      Log.i("Catchhh", " " + e); 
	} 
	  
	return null; 
	}
		


}
