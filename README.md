# WebViewMVC
A template for making java applications with html user interface running on WebView implementing MVC 

- Tired of javaFX?
- Do you want to use electron but you'd already spent 24h trying to setup node?
- Do you like statics types?
- Do you want to add an user interfaces to your java code with 3 lines?

Learn how to do it in less time, that takes to read this shitty questions:

**Just create a view with your html code and add your java objects:**
```java

    ViewHandler viewHandler = new ViewHandlerImpl();
    View view = viewHandler.loadView("view/MainView.html");
    
    Obervable<String> example = new Observable<>("example string");

    view.addObjectOnJS("example", example);

    viewHandler.show(view);

```

Now use it on JavaScript, you can add listeners if the Object have the property ```Observable```.
The function ```main(){...}``` will be executed every time ```viewHandler.show(view);```
 is called. 

```html
<p id="someID">Hello World</p>

<script>
    function main(){
        document.getElementById("someID").innerHTML = example.get();
        
        example.addListener(function (oldValue, newValue){
            document.getElementById("someID").innerHTML = newValue
        });
    }
</script>

```

And that's all you need.
