# Retrofit with dynamic endpoint

This repository is a example on how to implemente a custom endpoint with retrofit. (http://square.github.io/retrofit/)

For example you have two subdomain with the same API :
 - http://west.example.com/api 
 - http://east.example.com/api
 
and you want to easly switch between these API during the runtime, It's possible without reinstansiate the RestAdapter.

Simply used a CustomEndPoint

```java
RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(new CustomEndPoint(this))
                .build();
```

CustomEndPoint:
```java
public class CustomEndPoint implements Endpoint {

    private String url;
    private URLInterface urlInterface;

    public CustomEndPoint(URLInterface urlInterface) {
        this.urlInterface = urlInterface;
    }

    @Override
    public String getUrl() {
        url = urlInterface.getCustomUrl();
        if (url == null) {
            throw new IllegalStateException("Illegal URL.");
        }
        return url;
    }

    @Override
    public String getName() {
        return null;
    }
}
```

Interface:

```java
public interface URLInterface {
    public String getCustomUrl();
}
```

Implementation depending on a switch button in your interface :

```java
..... implements URLInterface{

    @Override
    public String getCustomUrl() {
        if(switchButton.isChecked()){
            return "http://west.example.com";
        }else{
            return "http://east.example.com";
        }
    }

}
```
It could be also useful if you want to switch between your Qualif and Production server during the runtime, in a demo application or in test application.
