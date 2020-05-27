 AndroidUi
======

[![](https://jitpack.io/v/Concyline/Androidui.svg)](https://jitpack.io/#Concyline/Androidui)

This library has 3 modules to aid Android development, speeding up the completion of the application

<img src="https://github.com/Concyline/AndroidUi/blob/master/img/componentes.gif" width="50%">

 Features
------
 * Componentes
 * ManipulaTexto
 * Leitor CodeBar and QrCode
 
 Gradle Setup
------

Version 2.6.4 = 4d6c602321
```Groovy
dependencies {
	        implementation 'com.github.Concyline:Androidui:4d6c602321'
	}
```
Usage
-----

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

all components have the following methods `boolean validaPreenchido()`, `boolean validaCpfCnpj()`, `void setError()`, `void removeError()`, `void mostraSenha()`, `String getString() `, `Integer getInteger()`, `Double getDouble()`

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
