<img src="https://github.com/Concyline/AndroidUi/blob/master/img/logo_projeto_novo.png" width="100%">

[![](https://jitpack.io/v/concyline/androidui.svg)](https://jitpack.io/#concyline/androidui)

This library has 6 modules to aid Android development, speeding up the completion of the application

 Features
------
 * [Componentes](#Componentes)
   - [Simples](#Simples)
   - [Mascara](#Mascara)
   - [Requerido](#Requerido)
   - [Só data](#Só-data)
   - [Spinner](#Spinner)
   - [Dara e Hora](#Dara-e-Hora)
   - [Icon direita](#Icon-direita)
   - [TextViewTitle](#TextViewTitle)
   - [EditTextSearch](#EditTextSearch)
   - [Multiline](#Multiline)
   - [PassWord](#PassWord)
   - [EditTexCurrency](#EditTexCurrency)
   - [RecyclerViewButton](#RecyclerViewButton)
   - [ProgressImageView](#ProgressImageView)
   - [ProgressButton](#ProgressButton)
   - [HelpButton](#HelpButton)
   - [RoundishImageView](#RoundishImageView)
   - [RoundImageView](#RoundImageView)
 * [Manipulador de texto](#Manipulador-de-texto)
 * [CustomDialog](#CustomDialog)
 * [GeometricProgress](#GeometricProgress)
 * [DotLoader](#DotLoader)
 * [ProgressIndeterminate](#ProgressIndeterminate)
 * [SwipeLayout](#SwipeLayout)
 * [Leitor Qr and CodeBar](#Leitor-Qr-and-CodeBar)
 * [ShortCut](#ShortCut)
 * [Permisions](#Permisions)
 * [Util](#Util)
   - [BackgroundTask](#BackgroundTask)
 * [CamPix](#CamPix)
 * [PhotoView](#PhotoView)
 * [Zoom Frame](#Zoom-Frame)
 * [Hawk](#Hawk)
 * [CalculatorDialog](#CalculatorDialog)
 * [KeyBoardDialog](#KeyBoardDialog)
 * [HttpAgent](#HttpAgent)

 
 Gradle Setup
------

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
	        implementation 'com.github.Concyline:Androidui:{CURRENT VERSION}'
	     }
```



## Componentes
<img src="https://github.com/Concyline/AndroidUi/blob/master/img/componentes.gif" width="20%">

### Simples

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/componentesimples.png" width="50%">

```xml
 <siac.com.componentes.EditTextTitle
            android:id="@+id/editTextTitle"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:colorTitle="@color/colorAccent"
            app:requerido="false"
            app:enabled="true"
            app:focusable="true"
            app:requestfocus="true"
            app:tag="edittext"
            app:text="texto"
            app:title="Componente simples" />
```

---
### Mascara

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/mascara.png" width="50%">

```xml

    <!-- Is free to create any mask just edit the app:mascara="" -->

    <siac.com.componentes.EditTextTitle
            android:id="@+id/editTextTitle"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:title="Mascara"
            app:mascara="  .   .   /    -  "
            app:enabled="true"
            app:focusable="true"
            app:requestfocus="false"
            app:tag="edittext"
            app:text="texto"
            app:requerido="true"
	    app:titleRequerido="Digite um CNPJ válido!"/>
```

---
### Requerido

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/legenda_requerido.png" width="50%">

```xml
    <siac.com.componentes.EditTextTitle
            android:id="@+id/editTextTitle"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:title="Enail"
            app:enabled="true"
            app:focusable="true"
            app:requestfocus="false"
            app:tag="edittext"
            app:text="texto"
            app:requerido="true"
	    app:titleRequerido="Digite um email válido!"/>
```

---
### Só data

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/sodata.png" width="50%">

```xml
    <!-- the calendar listener is already implemented within the component -->

     <siac.com.componentes.EditTextCalendar
            android:id="@+id/editTextTitle"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:title="Só data" />
```

---

### Spinner
<img src="https://github.com/Concyline/AndroidUi/blob/master/img/spinner.png" width="50%">

```xml
         <siac.com.componentes.SpinnerTitle
            android:id="@+id/spinnerTitle"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            app:entries="@array/tipo"
            app:title="Spinner" />
```
or add programaticaly adapter

````java
ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(getBaseContext(), 
	siac.com.componentes.R.layout.view_spinner_item_ui, new String[]{"aureo", "ana", "davi"});
	
spinnerTitle.setAdapter(adapter);
````

---
### Dara e Hora

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/calendarhoradata.png" width="50%">

```xml
          <siac.com.componentes.EditTextCalendar
            android:id="@+id/editTextCalendar"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            app:hora="true"
            app:inicializa="false"
            app:title="EditTextCalendar" />
```

---
### Icon direita
<img src="https://github.com/Concyline/AndroidUi/blob/master/img/iconedireita.png" width="50%">

```xml
      <siac.com.componentes.EditTextTitle
            android:id="@+id/cadastroEditTextTitle"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            app:coricon="#D6C"
            app:iconRigth="@drawable/icons_coroa_48"
            app:inputType="number"
            app:title="Nome do cliente"
            app:tamTitle="@dimen/tamLegendaEditTextUi" />
```

---
### TextViewTitle

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/textocomlegenda.png" width="50%">

```xml
    <siac.com.componentes.TextViewTitle
            android:id="@+id/textViewTitle"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_marginTop="9dp"
            app:corDescricao="@color/colorPrimaryDark"
            app:colorTitle="@color/colorAccent"
	    app:tamTitle="13sp"
            app:tamDescricao="16sp"
	    app:singleLine="false"
            app:descricao="Goiânia  adsdasdadsdasdsdasdsadsadsadasdsadadsdaszdsadasdsadasdasda"
            app:title="Cidade" />
```

---
### EditTextSearch

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/pesquisasimples.png" width="50%">

```xml
   <siac.com.componentes.EditTextSearch
            android:id="@+id/editTextSearch"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            app:hint="sua pesquisa"
            app:inputType="none"
            app:coricon="@color/colorAccent"
            app:title="EditTextSearch" />
```

````java
editTextSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ...
            }
        });
	
// Custom inputType programmatically
searchLegenda.setInputTypeSearch(Constantes.textPassword);
````
---
### Multiline

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/multiline.png" width="50%">

````xml
    <siac.com.componentes.EditTextTitle
            android:id="@+id/multilineEditTextTitle"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="8dp"
            app:coricon="@color/colorAccent"
            app:inputType="textMultiLine"
            app:text="Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
            app:title="Multiline"
            app:lines="4"
            app:tamTitle="@dimen/tamLegendaEditTextUi" />
````

---
### PassWord

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/senha.png" width="50%">

```xml
      <siac.com.componentes.EditTextTitle
            android:id="@+id/senhaEditTextTitle"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            app:coricon="@color/colorAccent"
            app:iconRigth="@drawable/round_visibility_black_48dp"
            app:inputType="textPassword"
            app:title="Senha"
            app:tamTitle="@dimen/tamLegendaEditTextUi" />
```

``` java
final EditTextTitle senhaEditTextTitle = findViewById(R.id.senhaEditTextTitle);
        senhaEditTextTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senhaEditTextTitle.mostraSenha();
            }
        });
````

---
### EditTexCurrency

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/componente_currence.png" width="50%">

```xml
         <siac.com.componentes.EditTexCurrency
            android:id="@+id/editTexCurrency"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:enabled="true"
            app:focusable="true"
            app:title="Valor"
            app:requerido="false"
            app:requestfocus="false"
            app:locale="pt_BR"
            app:showSymbol="true"
            app:tag="Valor"
            app:text="" />
```

---
### RecyclerViewButton

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/recyclerviewbutton.png" width="20%">

XML file
```xml
   <siac.com.componentes.RecyclerViewButton
        android:id="@+id/recyclerViewButton"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:horizontalDivider="true"
        app:locationButton="right"
        app:numberOfColumns="2"
        app:basic="false"
        app:refresh="true"
        app:verticalDivider="true" />
```

in OnCreate
````java
recyclerViewButton.setAdapter(this, new Adapter());

recyclerViewButton.scrollToPosition(0)

recyclerViewButton.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Async().execute();
            }
});

recyclerViewButton.addOnItemTouchListener(new RecyclerViewButton.ItemClickListener(new RecyclerViewButton.Listener() {
            @Override
            public void onItemClick(View view, int position) {
                System.out.println("aqui");
            }

            @Override
            public void onLongItemClick(View view, int position) {
                System.out.println("aqui");
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("aqui");
            }
}));
````

Adapter
````java
 public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private LayoutInflater mInflater;

        Adapter() {
            this.mInflater = LayoutInflater.from(getBaseContext());
        }

        @Override
        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.cidade_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            final ViewHolder viewHolder = (ViewHolder) holder;

            Cidade cidade = lCidade.get(position);

            viewHolder.nomeTextView.setText(cidade.getNome());
            viewHolder.ufTextView.setText(cidade.getUf());
        }

        @Override
        public int getItemCount() {
            return lCidade.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView nomeTextView;
            TextView ufTextView;

            ViewHolder(View itemView) {
                super(itemView);
                nomeTextView = itemView.findViewById(R.id.nomeTextView);
                ufTextView = itemView.findViewById(R.id.ufTextView);
            }
        }

        Cidade getItem(int id) {
            return lCidade.get(id);
        }
}
````

---
### ProgressImageView

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/progress_image.png" width="50%">

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/progress_image_click.png" width="50%">

```xml
    <siac.com.componentes.ProgressImageView
        android:id="@+id/progressImageView"
        android:layout_width="50dp"
        android:layout_height="50dp"
        app:progressSize="150dp"
        app:src="@drawable/lupa"
        app:progressColor="#2DB200" />
```
```java
 final ProgressImageView progressImageView = findViewById(R.id.progressImageView);
        progressImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressImageView.setProgres();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressImageView.removeProgres();
                    }
                }, 2000);
            }
        });
```
---
### ProgressButton

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/progres_button.png" width="50%"> 
<img src="https://github.com/Concyline/AndroidUi/blob/master/img/progres_button_pressed.png" width="50%">

```xml
    <siac.com.componentes.ProgressButton
        android:id="@+id/progressButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:progressColor="#2DB200"
        app:progressSize="100dp"
        app:text="Ok Big Guy" />
```
```java
final ProgressButton progressButtonOk = findViewById(R.id.progressButtonOk);
        progressButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressButtonOk.setProgres();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressButtonOk.removeProgres();
                    }
                }, 2000);
            }
        });
```
---
### HelpButton

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/help_buttom.png" width="20%"> 
<img src="https://github.com/Concyline/AndroidUi/blob/master/img/dialog_help_buttom.png" width="20%">

```xml
  <siac.com.componentes.HelpButton
    android:id="@+id/helpButton"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:color="@color/colorError"
    app:helpMsg="Lorem Ipsum is simply dummy text of the printing and typesetting industry."/>
```
```java
 HelpButton helpButton = findViewById(R.id.helpButton);
 helpButton.setActivity(this);
 
 // In XML file or programmatically
 helpButton.setHelpMsg("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");

```
---
### RoundishImageView

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/image_rounted.png" width="20%">

````xml
     <siac.com.componentes.RoundishImageView
            android:id="@+id/roundishImageView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="8dp"
            android:src="@drawable/cidade"
            app:cornerRadius="10dp"
            app:roundedCorners="topLeft|bottomRight" />
````

---
### RoundImageView

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/rount_image.png" width="20%">

````xml
     <siac.com.componentes.RoundImageView
        android:id="@+id/roundImageView"
        android:layout_width="150dp"
        android:layout_height="150dp"
        android:src="@drawable/cidade"
        app:borderColor="@color/colorAccent"
        app:borderWidth="5dp" />
````

--- 

## Custon Dialog

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/custon_dimness.png" width="20%"/> <img src="https://github.com/Concyline/AndroidUi/blob/master/img/error_dialog.png" width="20%"/> <img src="https://github.com/Concyline/AndroidUi/blob/master/img/circle_dialog.png" width="20%"/> <img src="https://github.com/Concyline/AndroidUi/blob/master/img/custon_background_color.png" width="20%"/>  

````java
new CDialog(ComponentesDoisActivity.this)
                        .createAlert("WARNING! custon mensagem",
                                WindowFormat.BACKGROUND_RECTANGLE,
                                getBitmapFromAsset(),
                                TypeDialog.WARNING,
                                SizeDialog.XLARGE)
                        .setAnimation(AnimateDialog.SCALE_FROM_BOTTOM_TO_TOP)
                        .setDuration(3000)  // in milliseconds
                        .setTextSize(SizeText.XLARGE)
                        .setPosition(PositionDialog.POSITION_CENTER)
                        .setBackDimness(0.9f) // less Than One
                        .setBackgroundColor(R.color.pink)
                        .show();
````
> Or Listener, when you finish the task

````java
new CDialog(ParametrosActivity.this)
                .createAlertSneckBar("Salvo com sucesso!",
                        TypeDialog.INFO,
                        SizeDialog.MEDIUM)
                .setDuration(2000)
                .show(new CDialog.CDialogListener() {
                    @Override
                    public void onDismiss() {
                	...
                    }
                });
````

> Get Image resources

````java
 public Bitmap getBitmapFromAsset() {
      InputStream imageStream = getResources().openRawResource(R.raw.lamp);
      Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
      return bitmap;
  }

  public Drawable getDrawable() {
      Drawable myDrawable = getResources().getDrawable(R.drawable.checked_1);
      return myDrawable;
  }
````

---

## SnackBar

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/sneckbar.png" width="20%"/> 

````java
 new CDialog(ComponentesDoisActivity.this)
                        .createAlertSneckBar("Info SnackBar",
                            TypeDialog.INFO,
                            SizeDialog.MEDIUM)
                        .show();
````

---

 ## Manipulador de texto
  
  <img src="https://github.com/Concyline/AndroidUi/blob/master/img/manipulatexto.gif" width="20%">
  
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
	android:requestLegacyExternalStorage="true" <!--VERY IMPORTANT API > 26-->
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

---

## CustomDialog

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/cuson_dialog.png" width="20%"/> 

in res/layout/cadastro.xml
````xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="10dp"
    android:background="#FFF">
	
	...

        <Button
            android:id="@+id/button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="OK"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/editTextTextPersonName2" />
</androidx.constraintlayout.widget.ConstraintLayout>
````

in res/menu/menu_bar.xml
````xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <item
        android:id="@+id/miCompose"
        android:icon="@drawable/round_alarm_white_48dp"
        app:showAsAction="ifRoom"
        android:title="Compose">
    </item>
    <item
        android:id="@+id/miProfile"
        android:icon="@drawable/round_backup_white_48dp"
        app:showAsAction="ifRoom|withText"
        android:title="Profile">
    </item>
</menu>
````

in onCreate
````java
   try {
            CustomDialog customDialog = new CustomDialog(MainActivity.this);
            customDialog.setContentView(R.layout.cadastro)
                    .setToolbarTitle("Log IN")
                    .setToolbarSubTitle("Enter the system")
                    .setMenuToolbar(R.menu.menu_bar)
                    .setBackgroundResource(CustomDialog.DWindow.ROUND)
                    .setHeight(CustomDialog.DLayoutParams.WRAP_CONTENT)
                    .setCancelable(true)
                    .create();

            customDialog.menu(R.id.miCompose).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    System.out.println("MENU");
                    return false;
                }
            });

            Dialog dialog = customDialog.dialog();

            Button button = dialog.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("BUTTON");
                }
            });
            
            customDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
````

---

## GeometricProgress

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/geometric_progress.gif" width="20%"/> 

````xml
 <siac.com.componentes.GeometricProgressView
    android:id="@+id/progressView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:gp_type="triangle"
    app:gp_number_of_angles="7"
    app:gp_color="@android:color/black"
    app:gp_duration="800"
    app:gp_figure_padding="3dp" />
````

````java
GeometricProgressView progressView = (GeometricProgressView) findViewById(R.id.progressView);
progressView.setType(TYPE.KITE);
progressView.setNumberOfAngles(6);
progressView.setColor(Color.parseColor("#00897b"));
progressView.setDuration(1000);
progressView.setFigurePadding(getResources().getDimensionPixelOffset(R.dimen.figure_padding));
````

---

## DotLoader

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/loading_anim.gif" width="50%"/> 

````xml
 <siac.com.componentes.DotLoader
    android:id="@+id/text_dot_loader"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:color_array="@array/dot_colors"
    app:dot_radius="4dp"
    app:number_of_dots="3"/>
````

color.xml
````xml
<array name="dot_colors">
    <item>#03A9F4</item>
    <item>#E65100</item>
    <item>#FFBB00</item>
</array>
````

````java
dotLoader.postDelayed(new Runnable() {
    @Override
    public void run() {
        dotLoader.setNumberOfDots(5);
    }
}, 3000);
````

---

## ProgressIndeterminate

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/progres_indetermined.png" width="20%"/> 

````java
ProgressIndeterminate progressDialog = new ProgressIndeterminate(ComponentesDoisActivity.this).
                        create("Atenção!")
                        .multColor(true)
                        .setTextSize(SizeText.MEDIUM)
                        .cancelable(false);

      progressDialog.show();
      
      //progressDialog.dismiss();
      //progressDialog.isShowing();
      //progressDialog.setMessage("Text");
      //progressDialog.setBackgroundColor(R.color.pink);
		
// or

ProgressIndeterminate progressDialog = ProgressIndeterminate.show(ComponentesDoisActivity.this, "OK");
````

---
## SwipeLayout

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/swipe_rigth.png" width="20%"/> 

````xml
<siac.com.componentes.SwipeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/swipe_layout"
    android:layout_width="match_parent"
    android:layout_height="90dp"
    android:layout_marginTop="3dp"
    app:draggedItem="@id/drag_item"
    app:swipeDirection="left|right"
    app:leftItem="@id/left_view"
    app:rightItem="@id/right_view">

    <ImageView
        android:id="@+id/left_view"
        android:layout_width="90dp"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:background="#ff5722"
        android:gravity="center"
        android:paddingEnd="24dp"
        android:paddingStart="24dp"
        android:src="@drawable/ic_upload"/>

    <ImageView
        android:id="@+id/right_view"
        android:layout_width="90dp"
        android:layout_height="match_parent"
        android:layout_gravity="end"
        android:background="#ff5722"
        android:gravity="center"
        android:paddingEnd="24dp"
        android:paddingStart="24dp"
        android:src="@drawable/ic_delete"/>

    <TextView
        android:id="@+id/drag_item"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#eeeeee"
        android:clickable="true"
        android:focusable="true"
        android:foreground="?selectableItemBackgroundBorderless"
        android:gravity="center_vertical"
        android:orientation="vertical"
        android:paddingEnd="10dp"
        android:paddingStart="20dp"
        android:textSize="20sp"/>

</siac.com.componentes.SwipeLayout>
````
In Holder

````java
    leftView = itemView.findViewById(R.id.left_view);
                rightView = itemView.findViewById(R.id.right_view);

                rightView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getAdapterPosition() != NO_POSITION) {
                            remove(itemView.getContext(), getAdapterPosition());
                        }
                    }
                });

                leftView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getAdapterPosition() != NO_POSITION) {
                            upload(itemView.getContext(), getAdapterPosition());
                        }
                    }
                });
````

---

For all companions
------

all components have the following methods `boolean validaPreenchido()`, `boolean validaCpfCnpj()`, `void setError()`, `void removeError()`, `void mostraSenha()`, `String getString() `, `String getStringUperCase() `, `Integer getInteger()`, `Double getDouble()`

update color icon error
````xml
<color name="colorAccent">#F79D91</color>
````

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
  
 
  ---
  ## Leitor Qr and CodeBar
  
  <img src="https://github.com/Concyline/AndroidUi/blob/master/img/leitor.gif" width="20%">
  
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
                intent.putExtra(LeitorActivity.CODE_TEST,codigo);
                startActivityForResult(intent, 123);
            }
        });

        Button lerQrButton = findViewById(R.id.lerQrButton);
        lerQrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LeitorActivity.class);
                startActivityForResult(intent, 123);
            }
        });
  }
  
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
	 if(requestCode == 123) {
            retornoEditText.setText("");
            if (data != null) {
                retornoEditText.setText(data.getStringExtra("CODIGO"));
            }
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
---
## ShortCut
<img src="https://github.com/Concyline/AndroidUi/blob/master/img/shortcut.png" width="20%">

 ### Usage
 
 #### ADD

 ````java
 ShortcutUtils shortcutUtils;
 Shortcut dynamicShortcut;
    
 shortcutUtils = new ShortcutUtils(this);

 dynamicShortcut = new Shortcut.ShortcutBuilder()
      .setShortcutIcon(R.drawable.round_device_hub_white_48dp)
      .setShortcutId("dynamicShortcutId")
      .setShortcutLongLabel("ALL Devices")
      .setShortcutShortLabel("ALL Devices")
      .setIntentAction("dynamicShortcutIntentAction")
      .setIntentStringExtraKey("dynamicShortcutKey")
      .setIntentStringExtraValue("all")
 .build();


  shortcutUtils.addDynamicShortCut(dynamicShortcut, new IReceiveStringExtra() {
       @Override
       public void onReceiveStringExtra(String stringExtraKey, String stringExtraValue) {
            String intent = getIntent().getStringExtra(stringExtraKey);
                if (intent != null) {
                    if (intent.equals("all")) {
                        System.out.println("OKOKOKOKOKOKO");
                    }
                }
            }
        });
    
 ````
 
 #### REMOVE
 
 ````java
shortcutUtils.removeDynamicShortCut(dynamicShortcut);
 ````
 ---
 ## Permisions
 
<img src="https://github.com/Concyline/AndroidUi/blob/master/img/permisions.png" width="20%">

 ### Usage
 
 ````java
String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

Permissions.check(MainActivity.this, permissions, null, null, new PermissionHandler() {
       @Override
       public void onGranted() {
	    	...
       }

       @Override
       public void onDenied(Context context, ArrayList<String> deniedPermissions) {
        	...
       }
});
 ````
 ---
 ## Util
 
 <img src="https://github.com/Concyline/AndroidUi/blob/master/img/util_dois.gif" width="20%">
 
 ### Usage
 ````java
 // In Activity
 import siac.com.util.Util;
 
 // Usage in class
 Util.setBar(UtilActivity.this, "Title", "Subtitle");
 
 Util.abaixaTeclado(getBaseContext(), view);
                                
 Util.fadeIn(getBaseContext(), view);
                                
 Util.toastLong(getBaseContext(),"Long mesage Toasta");
 
 Util.toastShort(getBaseContext(),"Short mesage Toasta");
                                
 Util.alertOk(UtilActivity.this, "Atention mesage");
                                
 Util.alertOk(UtilActivity.this, "mesage", new OnListnerOk() {
         @Override
         public void ok() {
                 ...                       
         }
 });
                                
 Util.alertSimCancelar(UtilActivity.this, "mesage", new OnListnerAlertSimCancelar() {
          @Override
          public void sim() {
                  ...                      
          }

          @Override
          public void cancelar() {
      		  ...
          }
  });
 ````
 
 ### BackgroundTask
 
 ````java
 BackgroundTask.with(this) // Activity|FragmentActivity(v4)|Fragment|Fragment(v4)
        .assign(new BackgroundTask.TaskDescription() {
            @Override
            public Object onBackground() {
                // Do what you want to do on background thread.
                // If you want to post something to MainThread,
                // just call BackgroundTask.post(YOUR_MESSAGE).

                // Return your finally result(Nullable).
                return null;
            }
        })
        .handle(new BackgroundTask.MessageListener() {
            @Override
            public void handleMessage(@NonNull Message message) {
                // Receive message in MainThread which sent from WorkerThread,
                // update your UI just in time.
            }
        })
        .finish(new BackgroundTask.FinishListener() {
            @Override
            public void onFinish(@Nullable Object result) {
                // If WorkerThread finish without Exception and lifecycle safety,
                // deal with your WorkerThread result at here.
            }
        })
        .broken(new BackgroundTask.BrokenListener() {
            @Override
            public void onBroken(@NonNull Exception e) {
                // If WorkerThread finish with Exception and lifecycle safety,
                // deal with Exception at here.
            }
        })
        .execute();
	

Notice:

MUST: .with(), .assign(), .execute().

OPTION: .handle(), .finish(), broken(). Every method just call once, otherwise the newer with replace the older.

Use BackgroundTask.post() To send message from WorkerThread to MainThread just in time.
 ````
 ---
 ## CamPix
 
 <img src="https://github.com/Concyline/AndroidUi/blob/master/img/campix.png" width="20%">
 
 ### Usage
 
 AndroidManifest.xml
 ````xml
	<?xml version="1.0" encoding="utf-8"?>
	<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    	package="...">

    		<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    		<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    		<uses-permission android:name="android.permission.CAMERA" />
    		<uses-permission android:name="android.permission.VIBRATE" />

    		<application>

			...    

        		<activity android:name="br.com.campix.Pix"/>
    		</application>

	</manifest>
 ````
 
 ````java

       findViewById(R.id.cliclButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options = Options.init()
                        .setRequestCode(requestCodePicker)
                        .setFrontfacing(false)
                        .setPath("pix/photo");
                //.setFileName("teste");

                Pix.start(CamPixActivity.this, options);
            }
        });
	
 ````
 
  ````java
   @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == requestCodePicker) {
            if (resultCode == Activity.RESULT_OK) {

                String path = data.getStringExtra(Pix.IMAGE_PATH);
                File file = (File) data.getExtras().get(Pix.IMAGE_FILE);

                glide = Glide.with(CamPixActivity.this);
                glide.load(path).into(imageView);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PermUtil.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS) {
            if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Pix.start(this, options);
            } else {
                Toast.makeText(this, "Approve permissions to open Pix ImagePicker", Toast.LENGTH_LONG).show();
            }
        }
    }
	
 ````
---
## PhotoView

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/photo_view.gif" width="20%">
 
### Usage
````xml
  <br.com.campix.photoView.PhotoView
        android:id="@+id/imagemImageView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:scaleType="fitXY"
        app:srcCompat="@drawable/button" />
````

````java
PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
photoView.setImageResource(R.drawable.image);
````

---
## Zoom Frame

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/zoom.gif" width="20%">
 
 ### Usage
 ````xml
   <siac.com.componentes.ZoomFrameImageView
        android:id="@+id/fragmentloginKenBurnsView1"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:src="@drawable/image_3" />
 ````
 
 ---
 ## Hawk
 
 <img src="https://github.com/Concyline/AndroidUi/blob/master/img/hawk.png" width="20%">
 
 ### Usage
 ````java
Initialize
Hawk.init(context).build();

Usage
Save any type (Any object, primitives, lists, sets, maps ...)

Hawk.put(key, T);
Get the original value with the original type

T value = Hawk.get(key);
Delete any entry

Hawk.delete(key);
Check if any key exists

Hawk.contains(key);
Check total entry count

Hawk.count();

Get crazy and delete everything
Hawk.deleteAll();
 ````
 
 ---
## CalculatorDialog 

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/calculator_ligth.png" width="20%"> <img src="https://github.com/Concyline/AndroidUi/blob/master/img/calculator_dark.png" width="20%">
 
 ### Usage
 ````java
 new CalculatorDialog(this) {
                @Override
                public void onResult(Double result) {
                    editText.setText(result + "");
                }
 }.setValue(Double.parseDouble(editText.getText().toString().trim())).showDIalog();
 ````
 
 ---
 ## KeyBoardDialog 
 
 <img src="https://github.com/Concyline/AndroidUi/blob/master/img/keyboard.png" width="20%">
 
 ### Usage
 ````java
 KeyBoardDialog customDialog = new KeyBoardDialog(ComponentesDoisActivity.this);
                    customDialog.setBackgroundResource(KeyBoardDialog.DWindow.ROUND)
                            .setCancelable(true)
                            .setJustNumber(false)
                            .create();

                    customDialog.show(editTextNumber.getText().toString(), new KeyBoardDialog.OnDismissListener() {
                        @Override
                        public void dismiss(String value) {
                            editTextNumber.setText(value);
                        }
                    });
 ````
 
 ---
 ## HttpAgent
 
 <img src="https://github.com/Concyline/AndroidUi/blob/master/img/httagent.png" width="20%">
 
 ### Usage
 ````java
   String content = gson.toJson(new LoginBody("12247272000170", "API", "123456"));

        new HttpAgent(MainActivity.this, "https://viacep.com.br/ws/01001000/json/", HTTP.GET)
                //new HttpAgent(MainActivity.this,"http://10.0.2.2:8080/SiacAPI/Login", HTTP.POST)
                //new HttpAgent(MainActivity.this,"http://10.0.2.2:8080/SiacAPI/Minutas", HTTP.GET)
                //.headers("Authorization", "Bearer " + token, "Content-Type", "application/json")
                .headers("Content-Type", "application/json")
                //.setTokenBearer(token)
		//.queryParams("key_1","value_1","key_2","value_2","key_N","value_N")
                //.withBody("{name:popapp ,age:27}")
                //.withBody(content)
                .goString(new StringCallback() {
                    @Override
                    protected void onDone(boolean success, String stringResults) {
                        if (success) {
                            System.out.println(stringResults);
                        } else {
                            System.out.println(getErrorMessage());
                        }
                    }
                });
		

//Get no results, Just send the request
go(new SuccessCallback() {
                        @Override
                        protected void onDone(boolean success) {
                            getErrorMessage(); //returns error message if exists.
                            getResponseCode(); // well, it's obvious...
                            getStringResults(); // returns results as as string.
                        }
                    })

//Get a string results
goString(new StringCallback() {
                        @Override
                        protected void onDone(boolean success, String results) {
                            getErrorMessage(); //returns error message if exists.
                            getResponseCode(); // well, it's obvious...
                            getStringResults(); // returns results as as string.
                        }
                    })

//Get Json results
goJson(new JsonCallback() {
                        @Override
                        protected void onDone(boolean success, JSONObject jsonObject) {
                            getErrorMessage(); //returns error message if exists.
                            getResponseCode(); // well, it's obvious...
                            getStringResults(); // returns results as as string.
                        }
                    })

//Get JsonArray results
goJsonArray(new JsonArrayCallback() {
                        @Override
                        protected void onDone(boolean success, JSONArray jsonArray) {
 			    getErrorMessage(); //returns error message if exists.
                            getResponseCode(); // well, it's obvious...
                            getStringResults(); // returns results as as string.
                        }
                    });
 ````

Resources
=========

| Anim | Description |
| --- | --- |
| shake | Balance the components |

| Drawable | Description | Image |
| --- | --- | --- |
| shadow | Composes with edges like a cardboard | <img src="https://github.com/Concyline/AndroidUi/blob/master/img/shadow_img.png" 		width="50px">  |
| shadow_selected | Composes with edges like a cardboard selected | <img src="https://github.com/Concyline/AndroidUi/blob/master/img/shadow_selected_img.png" width="50px"> |
| edit_selector | skin for custom Edittext | <img src="https://github.com/Concyline/AndroidUi/blob/master/img/estados_edittext.png" 		width="50px">  |
| spinner_selector | skin for custom Spinner | <img src="https://github.com/Concyline/AndroidUi/blob/master/img/estados_spinner.png" 		width="50px">  |

Contact
========
* concyline@hotmail.com
