# Cart with event sourcing

Trying to implement a shopping cart with event sourcing techniques.

## Links

* [Some slides from InnoQ](https://de.slideshare.net/mploed/event-sourcing-einfuhrung-und-best-practices)
* [Microsoft Azure article](https://docs.microsoft.com/en-us/azure/architecture/patterns/event-sourcing)

## Modeling Cart with Domain events

* CartCreatedEvent
* CartItemAddedEvent
* CartItemRemovedEvent
* CartPayedEvent

### CartCreatedEvent

```java
public class CartCreatedEvent {

    private String eventId;
    private Date eventTime;

    private String cartId;

    public CartCreatedEvent(String cartId) {
        this.cartId = cartId;
        this.eventTime = new Date();
    }
}

```

### CartItemAddedEvent

```java
public class CartItemAddedEvent {

    private String eventId;
    private Date eventTime;

    private String cartId;
    private String sku;
    private String price;
    private String currency;
    private String desc;

    public CartCreatedEvent(...) {
        ...
    }
}

```

### CartItemRemovedEvent

```java
public class CartItemRemovedEvent {

    private String eventId;
    private Date eventTime;

    private String cartId;
    private String sku;
}
```

## Other classes

* CartController
  * GET /cart/{cartId}
  * POST /cart
  * POST /cart/{cartId}/{item}

* CartQueryService
  * findById(cartId)

* CartQueryDao

* CartCommandService
  * createNewCart()
  * addToCart(cart, item)
  * removeFromCart(cart, item)
  * payCart(cart, payment)

* CartCommandDao


