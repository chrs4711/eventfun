# Cart with event sourcing

Trying to implement a shopping cart with event sourcing techniques.

## Links

* [Some slides from InnoQ](https://de.slideshare.net/mploed/event-sourcing-einfuhrung-und-best-practices)
* [Microsoft Azure article](https://docs.microsoft.com/en-us/azure/architecture/patterns/event-sourcing)
* [Thoughts on event stores](https://cqrs.wordpress.com/documents/building-event-storage/)

## Modeling Cart with Domain events

* CartCreatedEvent
* CartItemAddedEvent
* CartItemRemovedEvent
* CartPayedEvent


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


