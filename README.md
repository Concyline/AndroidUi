 AndroidUi
======

[![](https://jitpack.io/v/Concyline/Androidui.svg)](https://jitpack.io/#Concyline/Androidui)

This library has 3 modules to aid Android development, speeding up the completion of the application

 Features
------
 * Componentes
 * Manipulador de texto
 * Leitor CodeBar and QrCode
 
 Gradle Setup
------

Version 2.6.9 = e9e96e1423

```java
allprojects {
	repositories {
			...
			maven { url 'https://jitpack.io' }
		    }
	    }
````

```java
dependencies {
	        implementation 'com.github.Concyline:Androidui:e9e96e1423'
	     }
```



# * Componentes
<img src="https://github.com/Concyline/AndroidUi/blob/master/img/componentes.gif" width="50%">

### Usage

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/componentesimples.png" width="50%">

```xml
 <siac.com.componentes.EditTextLegenda
            android:id="@+id/editTextLegenda"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:corLegenda="@color/colorAccent"
            app:requerido="false"
            app:enabled="true"
            app:focusable="true"
            app:requestfocus="true"
            app:tag="edittext"
            app:text="texto"
            app:legenda="Componente simples" />
```

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/mascara.png" width="50%">

```xml

    <!-- Is free to create any mask just edit the app:mascara="" -->

    <siac.com.componentes.EditTextLegenda
            android:id="@+id/editTextLegenda2"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:legenda="Mascara"
            app:mascara="  .   .   /    -  "
            app:enabled="true"
            app:focusable="true"
            app:requestfocus="false"
            app:tag="edittext"
            app:text="texto"
            app:requerido="true" />
```

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/sodata.png" width="50%">

```xml
    <!-- the calendar listener is already implemented within the component -->

     <siac.com.componentes.EditTextCalendarLegenda
            android:id="@+id/editTextLegenda22"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:legenda="Só data" />
```

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/spinner.png" width="50%">

```xml
         <siac.com.componentes.SpinnerLegenda
            android:id="@+id/spinnerLegenda"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            app:entries="@array/tipo"
            app:legenda="Spinner" />
```
or add programaticaly adapter

````java
ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(getBaseContext(), 
	R.layout.view_spinner_item, new String[]{"aureo", "ana", "davi"});
	
spinnerLegenda.setAdapter(adapter);
````

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/calendarhoradata.png" width="50%">

```xml
          <siac.com.componentes.EditTextCalendarLegenda
            android:id="@+id/editTextCalendarLegenda"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            app:hora="true"
            app:inicializa="false"
            app:legenda="EditTextCalendarLegenda" />
```

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/iconedireita.png" width="50%">

```xml
      <siac.com.componentes.EditTextLegenda
            android:id="@+id/cadastroEditTextLegenda"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            app:coricon="#D6C"
            app:iconRigth="@drawable/icons_coroa_48"
            app:inputType="number"
            app:legenda="Nome do cliente"
            app:tamLegendaEditText="@dimen/tamLegendaEditText" />
```

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/textocomlegenda.png" width="50%">

```xml
    <siac.com.componentes.TextViewLegenda
            android:id="@+id/textViewsd"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_marginTop="9dp"
            app:corDescricao="@color/colorPrimaryDark"
            app:corLegenda="@color/colorAccent"
	    app:tamLegenda="13sp"
            app:tamDescricao="16sp"
	    app:singleLine="false"
            app:descricao="Goiânia  adsdasdadsdasdsdasdsadsadsadasdsadadsdaszdsadasdsadasdasda"
            app:legenda="Cidade" />
```

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/pesquisasimples.png" width="50%">

```xml
   <siac.com.componentes.SearchLegenda
            android:id="@+id/searchLegenda"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            app:hint="sua pesquisa"
            app:inputType="none"
            app:coricon="@color/colorAccent"
            app:legenda="SearchLegenda" />
```

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/senha.png" width="50%">

```xml
      <siac.com.componentes.EditTextLegenda
            android:id="@+id/senhaEditTextLegenda"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            app:coricon="@color/colorAccent"
            app:iconRigth="@drawable/round_visibility_black_48dp"
            app:inputType="textPassword"
            app:legenda="Senha"
            app:tamLegendaEditText="@dimen/tamLegendaEditText" />
```

``` java
final EditTextLegenda senhaEditTextLegenda = findViewById(R.id.senhaEditTextLegenda);
        senhaEditTextLegenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senhaEditTextLegenda.mostraSenha();
            }
        });
````

For all companions
------

all components have the following methods `boolean validaPreenchido()`, `boolean validaCpfCnpj()`, `void setError()`, `void removeError()`, `void mostraSenha()`, `String getString() `, `String getStringUperCase() `, `Integer getInteger()`, `Double getDouble()`

public listeners
```java
  public void setOnClickListener(OnClickListener onClickListener) {
        editText.setOnClickListener(onClickListener);
    }

    public void setOnClickListenerIconLeft(OnClickListener onClickListener) {
        iconLeftImageView.setOnClickListener(onClickListener);
    }

    public void setOnClickListenerIconRigth(OnClickListener onClickListener) {
        iconRigthImageView.setOnClickListener(onClickListener);
    }
  ````
  
  # * Manipulador de texto
  
  <img src="https://github.com/Concyline/AndroidUi/blob/master/img/manipulatexto.gif" width="50%">
  
  ### Usage
  
  ````java
  @Override
  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manipula_texto);
  
  		ManipulaTexto.init( this,"Manipula","Log.txt");
  		log = ManipulaTexto.getInstance();
  
  		// METHODS
  		//log.info("");
  		//log.erro("");
  		//log.processaException("class", Exception error);
  		//log.delete()
  		//String all = log.getAll();
  }
  ````
  
  ````xml
  <?xml version="1.0" encoding="utf-8"?>
  <manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="siac.com.androidui">

    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/icon_teste"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/icon_teste"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".ManipulaTextoActivity"></activity>
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
	   
    </application>

</manifest>
````
  
  # * Leitor Qr and CodeBar
  
  <img src="https://github.com/Concyline/AndroidUi/blob/master/img/leitor.gif" width="50%">
  
  ### Usage
  
  ````java
  @Override
  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manipula_texto);
  
  	retornoEditText = findViewById(R.id.retornoEditText);

        Button lerQrTesteButton = findViewById(R.id.lerQrTesteButton);
        lerQrTesteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LeitorActivity.class);
                String codigo = "C=7898958119652;L=50962;V=30/09/2019";
                intent.putExtra("teste",codigo);
                startActivityForResult(intent, 0);
            }
        });

        Button lerQrButton = findViewById(R.id.lerQrButton);
        lerQrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LeitorActivity.class);
                startActivityForResult(intent, 0);
            }
        });
  }
  
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        retornoEditText.setText("");
        if (data != null) {
            retornoEditText.setText(data.getStringExtra("CODIGO"));
        }
  }
  ````
  
  ````xml
  <?xml version="1.0" encoding="utf-8"?>
  <manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="siac.com.androidui">

    <uses-permission android:name="android.permission.CAMERA" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/icon_teste"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/icon_teste"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".ManipulaTextoActivity"></activity>
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
	    
        <activity android:name="siac.com.leitor.LeitorActivity" />

    </application>

</manifest>
````

Resources
=========

| Anim | Descrição |
| --- | --- |
| shake | Balance the components |

| Drawable | Descrição |
| --- | --- |
| shadow | Composes with edges like a cardboard |
| shadow_selected | Composes with edges like a cardboard selected |
| edit_selector | skin for custom Edittext |
| spinner_selector | skin for custom Spinner |

Contact
========
* concyline@hotmail.com
