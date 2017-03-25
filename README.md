# WebViewMVC
A template for making java applications with html user interface running on WebView implementing MVC 

- Tired of javaFX?
- Do you want to use electron but you'd already spent 24h trying to setup node?
- Do you like statics types?
- Do you want to add an user interfaces to your java code with 3 lines?

Learn how to do it in less time, that takes to read this shitty questions:

**Just create a view with your html code and add your hava objets:**
```java

    ViewHandler viewHandler = new ViewHandlerImpl();
    View vista = viewHandler.loadView("view/MainView.html");
    
    ObervableProperty<String> example = "example string";

    vista.addObjectOnJS(example, "example");

    viewHandler.show(vista);

```

Now use it on javaScript, you can add listerners if the Object have the property ```ObservableProperty```.
The function ```main()``` will be executed every time ```viewHandler.show(vista);```
 is called. 

```html
<p id="hola">Hello World</p>
<script src="js/Scripts.js"></script>

<script>
    function main(){
        document.getElementById("hola").innerHTML = ejemplo;
        
        ejemplo.addListener((oldValue, newValue)=>{
            document.getElementById("hola").innerHTML = newValue
        });
    }
</script>

```

And that is all you need.
